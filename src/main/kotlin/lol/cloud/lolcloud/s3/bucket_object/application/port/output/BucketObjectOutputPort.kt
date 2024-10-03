package lol.cloud.lolcloud.s3.bucket_object.application.port.output

import lol.cloud.lolcloud.s3.bucket.domain.Bucket
import lol.cloud.lolcloud.s3.bucket_object.domain.BucketObject
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.BucketEntity
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.output.persistence.entity.BucketObjectEntity
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.ObjectType

interface BucketObjectOutputPort {

    fun findBucketObjectByBucketAndObjectNameAndPrefix(
        bucketEntity: BucketEntity, objectName: String, prefix: String
    ): BucketObject?

    fun findAllByBucketAndPrefix(
        bucket: BucketEntity, prefix: String
    ): List<BucketObject>

    fun findBucketObjectByBucketAndKeyAndObjectType(
        bucketEntity: BucketEntity, key: String, objectType: ObjectType
    ): BucketObject?

    fun saveBucketObject(bucketObjectEntity: BucketObjectEntity): BucketObject

    fun findBucketById(bucketId: Long): BucketObject?

    fun findBucketObjectByObjectNameAndPrefixAndBucket(
        objectName: String,
        prefix: String,
        bucket: BucketEntity
    ): BucketObject?

}