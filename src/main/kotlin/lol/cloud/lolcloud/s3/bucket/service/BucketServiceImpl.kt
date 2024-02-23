package lol.cloud.lolcloud.s3.bucket.service

import jakarta.transaction.Transactional
import lol.cloud.lolcloud.s3.bucket.domain.bucket.Bucket
import lol.cloud.lolcloud.s3.bucket.dto.bucket.response.BucketResponse
import lol.cloud.lolcloud.s3.bucket.dto.bucket_object.request.BucketObjectRequest
import lol.cloud.lolcloud.s3.bucket.dto.bucket_object.response.BucketObjectResponse
import lol.cloud.lolcloud.s3.bucket.repository.bucket.BucketRepository
import lol.cloud.lolcloud.s3.bucket.repository.bucket_object.BucketObjectRepository
import lol.cloud.lolcloud.s3.user.domain.User
import lol.cloud.lolcloud.s3.user.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class BucketServiceImpl(
    private val userRepository: UserRepository,
    private val bucketRepository: BucketRepository,
    private val bucketObjectRepository: BucketObjectRepository,
) : BucketService{

    @Transactional
    override fun createBucket(bucket: Bucket): BucketResponse {
        val email = SecurityContextHolder.getContext().authentication.name

        val user: User = userRepository.findUserByEmail(email)
            ?: throw UsernameNotFoundException("없는 유저 입니다.")

        val findBucket = bucketRepository.findBucketByBucketName(bucket.bucketName)

        if(findBucket !=  null) throw RuntimeException("이미 존재하는 버킷 입니다.")

        user.createBucket(bucket)

        return bucketRepository.save(bucket).toDto()
    }

    @Transactional
    override fun getBuckets(): List<BucketResponse> {

        val email = SecurityContextHolder.getContext().authentication.name

        val user = userRepository.findUserByEmail(email)
            ?: throw UsernameNotFoundException("없는 유저 입니다.")

        val buckets = user.bucket

        return buckets.map { it.toDto() }
    }

    override fun getBucket(
        bucketName: String, bucketObjectRequest: BucketObjectRequest
    ): List<BucketObjectResponse> {

        val bucket = bucketRepository.findBucketByBucketName(bucketName)
            ?: throw RuntimeException("존재하지 않는 버킷 이름입니다.")

        val bucketObjects = bucketObjectRepository.findAllByBucket(bucket)

        TODO("Not yet implemented")
    }

}