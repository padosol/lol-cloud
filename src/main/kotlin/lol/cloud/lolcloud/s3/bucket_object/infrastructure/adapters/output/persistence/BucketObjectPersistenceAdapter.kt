package lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.output.persistence

import com.querydsl.jpa.impl.JPAQueryFactory
import lol.cloud.lolcloud.s3.bucket.domain.Bucket
import lol.cloud.lolcloud.s3.bucket_object.application.port.output.BucketObjectOutputPort
import lol.cloud.lolcloud.s3.bucket_object.domain.BucketObject
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.BucketEntity
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.output.persistence.entity.BucketObjectEntity
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.ObjectType
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.output.persistence.mapper.BucketObjectEntityMapper
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.output.persistence.repository.BucketObjectRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class BucketObjectPersistenceAdapter(
    private val bucketObjectRepository: BucketObjectRepository,
    private val jpaQueryFactory: JPAQueryFactory,
): BucketObjectOutputPort {
    override fun findBucketObjectByBucketAndObjectNameAndPrefix(
        bucketEntity: BucketEntity,
        objectName: String,
        prefix: String
    ): BucketObject? {

        TODO("Not yet implemented")
    }

    override fun findAllByBucketAndPrefix(bucket: BucketEntity, prefix: String): List<BucketObject> {
        val bucketObjectEntities: List<BucketObjectEntity> =
            bucketObjectRepository.findAllByBucketAndPrefix(bucket, prefix)

        return bucketObjectEntities.map { BucketObjectEntityMapper.toBucketObject(it) }.toList()
    }

    override fun findBucketObjectByBucketAndKeyAndObjectType(
        bucketEntity: BucketEntity,
        key: String,
        objectType: ObjectType
    ): BucketObject? {
        return bucketObjectRepository.findBucketObjectByBucketAndPrefixAndObjectType(
            bucketEntity, key, objectType
        ) ?.let {BucketObjectEntityMapper.toBucketObject(it)}
    }

    override fun saveBucketObject(bucketObjectEntity: BucketObjectEntity): BucketObject {
        return BucketObjectEntityMapper.toBucketObject(bucketObjectRepository.save(bucketObjectEntity))
    }

    override fun findBucketById(bucketId: Long): BucketObject? {
        return bucketObjectRepository.findByIdOrNull(bucketId)
            ?.let { BucketObjectEntityMapper.toBucketObject(it) }
    }

    override fun findBucketObjectByObjectNameAndPrefixAndBucket(
        objectName: String,
        prefix: String,
        bucket: BucketEntity
    ): BucketObject? {
        return bucketObjectRepository.findBucketObjectByBucketAndObjectNameAndPrefix(
            bucket, prefix, objectName
        ) ?.let { BucketObjectEntityMapper.toBucketObject(it) }
    }

}