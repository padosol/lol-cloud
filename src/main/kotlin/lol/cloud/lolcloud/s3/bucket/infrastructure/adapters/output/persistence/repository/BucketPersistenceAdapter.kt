package lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.repository

import lol.cloud.lolcloud.s3.bucket.application.ports.output.bucket.BucketOutputPort
import lol.cloud.lolcloud.s3.bucket.domain.Bucket
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.BucketEntity
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.mapper.BucketEntityMapper
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.repository.bucket.BucketRepository
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
}