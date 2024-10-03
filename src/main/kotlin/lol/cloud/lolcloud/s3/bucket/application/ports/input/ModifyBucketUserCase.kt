package lol.cloud.lolcloud.s3.bucket.application.ports.input

import lol.cloud.lolcloud.s3.bucket.domain.Bucket
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.response.BucketResponse

interface ModifyBucketUserCase {
    fun modifyBucket(bucket: Bucket): BucketResponse
}