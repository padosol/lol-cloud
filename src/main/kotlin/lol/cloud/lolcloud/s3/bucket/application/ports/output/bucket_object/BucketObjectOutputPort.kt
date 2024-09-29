package lol.cloud.lolcloud.s3.bucket.application.ports.output.bucket_object

import lol.cloud.lolcloud.s3.bucket.domain.BucketObject
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.BucketEntity
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.BucketObjectEntity
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
}