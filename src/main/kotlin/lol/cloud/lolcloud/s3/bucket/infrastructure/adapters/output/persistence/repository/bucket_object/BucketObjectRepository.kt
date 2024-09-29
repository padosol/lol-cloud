package lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.repository.bucket_object

import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.BucketEntity
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.BucketObjectEntity
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.ObjectType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BucketObjectRepository : JpaRepository<BucketObjectEntity, Long>, BucketObjectRepositoryCustom {

    fun findAllByBucket(bucketEntity: BucketEntity): List<BucketObjectEntity>
    fun findBucketObjectByBucketAndPrefixAndObjectType(bucketEntity: BucketEntity, prefix: String, objectType: ObjectType): BucketObjectEntity?

    fun findBucketObjectByBucketAndObjectNameAndPrefix(bucketEntity: BucketEntity, objectName: String, prefix: String): BucketObjectEntity?

    fun findAllByBucketAndPrefix(bucket: BucketEntity, prefix: String): List<BucketObjectEntity>


    fun findBucketObjectByBucketAndKeyAndObjectType(bucketEntity: BucketEntity, key: String, objectType: ObjectType): BucketObjectEntity?


}