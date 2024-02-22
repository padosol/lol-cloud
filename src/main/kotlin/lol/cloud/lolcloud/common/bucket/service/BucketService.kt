package lol.cloud.lolcloud.common.bucket.service

import jakarta.transaction.Transactional
import lol.cloud.lolcloud.common.bucket.domain.entity.Bucket
import lol.cloud.lolcloud.common.bucket.dto.response.BucketResponse
import lol.cloud.lolcloud.common.bucket.repository.BucketRepository
import lol.cloud.lolcloud.common.user.domain.User
import lol.cloud.lolcloud.common.user.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class BucketService(
    private val userRepository: UserRepository,
    private val bucketRepository: BucketRepository
) {

    @Transactional
    fun createBucket(bucket: Bucket): BucketResponse {
        val email = SecurityContextHolder.getContext().authentication.name

        val user: User = userRepository.findUserByEmail(email)
            ?: throw UsernameNotFoundException("없는 유저 입니다.")

        val findBucket = bucketRepository.findBucketByBucketName(bucket.bucketName)

        if(findBucket !=  null) throw RuntimeException("이미 존재하는 버킷 입니다.")

        user.createBucket(bucket)

        return bucketRepository.save(bucket).toDto()
    }

}