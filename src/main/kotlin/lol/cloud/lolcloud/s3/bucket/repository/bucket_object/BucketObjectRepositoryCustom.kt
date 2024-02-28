package lol.cloud.lolcloud.s3.bucket.repository.bucket_object

import lol.cloud.lolcloud.s3.bucket.domain.bucket.Bucket
import lol.cloud.lolcloud.s3.bucket.domain.bucket_object.BucketObject

interface BucketObjectRepositoryCustom {

    fun findByBucketTest(): List<BucketObject>

    fun findAllBucketObjectByBucketAndPrefix(bucket: Bucket)
}