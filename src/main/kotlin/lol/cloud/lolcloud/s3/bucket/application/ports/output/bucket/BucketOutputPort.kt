package lol.cloud.lolcloud.s3.bucket.application.ports.output.bucket

import lol.cloud.lolcloud.s3.bucket.domain.Bucket
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.BucketEntity

interface BucketOutputPort {

    fun saveBucket(bucketEntity: BucketEntity): Bucket

    fun findBucketByBucketName(bucketName: String): Bucket?

}