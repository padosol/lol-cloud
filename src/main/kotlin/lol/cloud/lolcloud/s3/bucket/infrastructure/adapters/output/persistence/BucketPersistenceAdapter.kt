package lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence

import lol.cloud.lolcloud.s3.bucket.application.ports.output.BucketOutputPort
import lol.cloud.lolcloud.s3.bucket.domain.Bucket
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.BucketEntity
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.mapper.BucketEntityMapper
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.repository.BucketRepository
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.entity.UserEntity
import org.springframework.stereotype.Repository

@Repository
class BucketPersistenceAdapter(
    private val bucketRepository: BucketRepository
) : BucketOutputPort {
    override fun saveBucket(bucketEntity: BucketEntity): Bucket {
        bucketRepository.save(bucketEntity)
        return BucketEntityMapper.toBucket(bucketEntity)
    }

    override fun findBucketByBucketName(bucketName: String): Bucket? {
        return bucketRepository.findBucketByBucketName(bucketName)
            ?.let { BucketEntityMapper.toBucket(it) }
    }

    override fun findBucketByBucketNameAndEmail(bucketName: String, email: String): Bucket? {
        return bucketRepository.findBucketEntityByBucketNameAndUserEmail(bucketName, email)
            ?.let { BucketEntityMapper.toBucket(it)}
    }

    override fun findAllByUser(user: UserEntity): List<Bucket> {
        return bucketRepository.findAllByUser(user).map { BucketEntityMapper.toBucket(it) }.toList()
    }
}