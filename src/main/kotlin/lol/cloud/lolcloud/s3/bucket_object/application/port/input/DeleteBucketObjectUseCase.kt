package lol.cloud.lolcloud.s3.bucket_object.application.port.input

import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.input.rest.dto.request.BucketObjectRequest
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.input.rest.dto.response.BucketObjectDeleteResponse

interface DeleteBucketObjectUseCase {
    fun removeObjectAll(bucketObjectRequestList: List<BucketObjectRequest>): BucketObjectDeleteResponse
}