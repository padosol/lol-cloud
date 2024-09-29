package lol.cloud.lolcloud.s3.bucket.application.ports.input.bucket

import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket.response.BucketResponse
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket_object.request.BucketObjectRequest
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket_object.response.BucketObjectResponse

interface FindBucketUseCase {
    fun getBuckets(): List<BucketResponse>
    fun getBucket(bucketName: String, bucketObjectRequest: BucketObjectRequest): List<BucketObjectResponse>
}