package lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.output.persistence.repository

import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.BucketEntity
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.output.persistence.entity.BucketObjectEntity

interface BucketObjectRepositoryCustom {

    fun findByBucketTest(): List<BucketObjectEntity>

    fun findAllBucketObjectTypeFolderByBucketAndObjectNameAndPrefixForRecursive(
        bucketEntity: BucketEntity, objectName: String, prefix: String
    )
}