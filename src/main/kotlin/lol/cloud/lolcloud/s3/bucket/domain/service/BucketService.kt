package lol.cloud.lolcloud.s3.bucket.domain.service

import jakarta.servlet.http.HttpServletRequest
import jakarta.transaction.Transactional
import lol.cloud.lolcloud.s3.bucket.application.ports.input.bucket.CreateBucketUseCase
import lol.cloud.lolcloud.s3.bucket.application.ports.input.bucket.FindBucketUseCase
import lol.cloud.lolcloud.s3.bucket.application.ports.output.bucket.BucketOutputPort
import lol.cloud.lolcloud.s3.bucket.domain.Bucket
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.BucketObjectEntity
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket.response.BucketResponse
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket_object.request.BucketObjectRequest
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket_object.response.BucketObjectResponse
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.mapper.BucketMapper
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.mapper.BucketEntityMapper
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.repository.bucket.BucketRepository
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.repository.bucket_object.BucketObjectRepository
import lol.cloud.lolcloud.s3.folder.service.FolderService
import lol.cloud.lolcloud.s3.jwt.TokenProvider
import lol.cloud.lolcloud.s3.user.application.ports.output.UserOutputPort
import lol.cloud.lolcloud.s3.user.domain.model.User
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class BucketService(
    private val bucketRepository: BucketRepository,
    private val bucketObjectRepository: BucketObjectRepository,
    private val folderService: FolderService,
    private val userOutputPort: UserOutputPort,
    private val tokenProvider: TokenProvider,
    private val bucketOutputPort: BucketOutputPort

) : CreateBucketUseCase, FindBucketUseCase {

    private val log = LoggerFactory.getLogger(this.javaClass)!!
    override fun getBuckets(): List<BucketResponse> {
        val email = SecurityContextHolder.getContext().authentication.name

        val user = userOutputPort.findUserByEmail(email)
            ?: throw UsernameNotFoundException("없는 유저 입니다.")

        val buckets = user.bucketEntityList

        return buckets.map { BucketMapper.toDto(it) }
    }

    override fun getBucket(
        bucketName: String, bucketObjectRequest: BucketObjectRequest
    ): List<BucketObjectResponse> {

        val bucket = bucketRepository.findBucketByBucketName(bucketName)
            ?: throw RuntimeException("존재하지 않는 버킷 이름입니다.")

        val bucketObjectEntities: List<BucketObjectEntity> = bucketObjectRepository.findAllByBucketAndPrefix(
            bucket,
            bucketObjectRequest.prefix?:""
        )

        return bucketObjectEntities.map { it.toDto() }.toList()
    }

    @Transactional
    override fun createBucket(request: HttpServletRequest, bucket: Bucket): BucketResponse {
        val resolveToken = tokenProvider.resolveToken(request) ?: throw RuntimeException("토큰이 존재하지 않습니다.")
        val authentication = tokenProvider.getAuthentication(resolveToken)

        val email = authentication.name

        val user: User = userOutputPort.findUserByEmail(email)
            ?: throw UsernameNotFoundException("없는 유저 입니다.")

        bucketRepository.findBucketByBucketName(bucket.bucketName)
            ?.let { throw RuntimeException("이미 존재하는 버킷 입니다.") }

        bucket.addUser(user)

        folderService.createFolder(bucket.bucketName, "", "")

        val saveBucket = bucketOutputPort.saveBucket(BucketEntityMapper.toEntity(bucket))

        return  BucketMapper.toDto(saveBucket)
    }
}