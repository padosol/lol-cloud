package lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import lol.cloud.lolcloud.s3.bucket.application.ports.output.bucket_object.BucketObjectOutputPort
import lol.cloud.lolcloud.s3.bucket.domain.BucketObject
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.mapper.BucketObjectMapper
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.BucketEntity
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.BucketObjectEntity
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.ObjectType
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.mapper.BucketObjectEntityMapper
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.repository.bucket_object.BucketObjectRepository
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
        TODO("Not yet implemented")
    }

    override fun findBucketObjectByBucketAndKeyAndObjectType(
        bucketEntity: BucketEntity,
        key: String,
        objectType: ObjectType
    ): BucketObject? {
        TODO("Not yet implemented")
    }

    override fun saveBucketObject(bucketObjectEntity: BucketObjectEntity): BucketObject {
        return BucketObjectEntityMapper.toBucketObject(bucketObjectRepository.save(bucketObjectEntity))
    }


}