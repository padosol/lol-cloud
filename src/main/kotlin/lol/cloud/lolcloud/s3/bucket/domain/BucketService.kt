package lol.cloud.lolcloud.s3.bucket.domain

import jakarta.servlet.http.HttpServletRequest
import jakarta.transaction.Transactional
import lol.cloud.lolcloud.s3.bucket.application.ports.input.CreateBucketUseCase
import lol.cloud.lolcloud.s3.bucket.application.ports.input.FindBucketUseCase
import lol.cloud.lolcloud.s3.bucket.application.ports.input.ModifyBucketUserCase
import lol.cloud.lolcloud.s3.bucket.application.ports.output.BucketOutputPort
import lol.cloud.lolcloud.s3.bucket_object.application.port.output.BucketObjectOutputPort
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.response.BucketResponse
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.input.rest.dto.request.BucketObjectRequest
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.input.rest.dto.response.BucketObjectResponse
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.mapper.BucketMapper
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.input.rest.mapper.BucketObjectMapper
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.mapper.BucketEntityMapper
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.repository.BucketRepository
import lol.cloud.lolcloud.s3.common.error.S3ErrorException
import lol.cloud.lolcloud.s3.folder.service.FolderService
import lol.cloud.lolcloud.s3.jwt.TokenProvider
import lol.cloud.lolcloud.s3.user.application.ports.output.UserOutputPort
import lol.cloud.lolcloud.s3.user.domain.model.User
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.mapper.UserEntityMapper
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class BucketService(
    private val bucketRepository: BucketRepository,
    private val folderService: FolderService,
    private val userOutputPort: UserOutputPort,
    private val tokenProvider: TokenProvider,
    private val bucketOutputPort: BucketOutputPort,
    private val bucketObjectOutputPort: BucketObjectOutputPort

) : CreateBucketUseCase, FindBucketUseCase, ModifyBucketUserCase {

    private val log = LoggerFactory.getLogger(this.javaClass)!!
    override fun getBuckets(): List<BucketResponse> {
        val email = SecurityContextHolder.getContext().authentication.name

        val user = userOutputPort.findUserByEmail(email)
            ?: throw UsernameNotFoundException("없는 유저 입니다.")


        val buckets = bucketOutputPort.findAllByUser(UserEntityMapper.toUserEntity(user))

        return buckets.map { BucketMapper.toDto(it) }
    }

    override fun getBucket(
        bucketName: String, bucketObjectRequest: BucketObjectRequest
    ): List<BucketObjectResponse> {

        val bucket = bucketRepository.findBucketByBucketName(bucketName)
            ?: throw RuntimeException("존재하지 않는 버킷 이름입니다.")

        val bucketObjects = bucketObjectOutputPort.findAllByBucketAndPrefix(
            bucket,
            bucketObjectRequest.prefix ?: ""
        )

        return bucketObjects.map { BucketObjectMapper.toResponse(it) }.toList()
    }

    override fun getBucket(bucketName: String, email: String): BucketResponse {
        val findBucket =
            bucketOutputPort.findBucketByBucketNameAndEmail(bucketName, email)
                ?: throw S3ErrorException(HttpStatus.BAD_REQUEST, "존재하지 않는 유저 입니다.")

        return BucketMapper.toDto(findBucket)
    }

    @Transactional
    override fun createBucket(request: HttpServletRequest, bucket: Bucket): BucketResponse {
        val resolveToken = tokenProvider.resolveToken(request) ?: throw RuntimeException("토큰이 존재하지 않습니다.")
        val authentication = tokenProvider.getAuthentication(resolveToken)

        val email = authentication.name

        val user: User = userOutputPort.findUserByEmail(email)
            ?: throw UsernameNotFoundException("없는 유저 입니다.")

        bucketRepository.findBucketByBucketName(bucket.bucketName)
            ?.let { throw S3ErrorException(HttpStatus.BAD_REQUEST, "이미 존재하는 버킷입니다.") }

        bucket.addUser(user)

        folderService.createFolder(bucket.bucketName, "", "")

        val saveBucket = bucketOutputPort.saveBucket(BucketEntityMapper.toEntity(bucket))

        return  BucketMapper.toDto(saveBucket)
    }

    override fun modifyBucket(bucket: Bucket): BucketResponse {



        TODO("Not yet implemented")
    }
}