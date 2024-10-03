package lol.cloud.lolcloud.s3.bucket_object.application.port.input

import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.response.BucketResponse
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.input.rest.dto.request.BucketObjectRequest
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.input.rest.dto.response.BucketObjectResponse

interface GetBucketObjectUseCase {
//    fun getObject(bucketObjectRequest: BucketObjectRequest): BucketObjectResponse

    fun getObject(bucketId: Long): BucketObjectResponse
    fun getObjectAll(bucketObjectRequest: BucketObjectRequest): List<BucketObjectResponse>

    fun getObjectByBucketIdAndObjectId(bucketId: String, objectId: String): BucketObjectResponse

    fun findBucketObjectByBucketIdAndObjectIdAndPrefix(
        bucketId: String, objectId: String, prefix: String
    ): BucketObjectResponse
}