package lol.cloud.lolcloud.s3.bucket.application.ports.input

import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.response.BucketResponse
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.input.rest.dto.request.BucketObjectRequest
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.input.rest.dto.response.BucketObjectResponse
import lol.cloud.lolcloud.s3.user.domain.model.User

interface FindBucketUseCase {
    fun getBuckets(): List<BucketResponse>
    fun getBucket(bucketName: String, bucketObjectRequest: BucketObjectRequest): List<BucketObjectResponse>
    fun getBucket(bucketName: String, email: String): BucketResponse

}