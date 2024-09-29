package lol.cloud.lolcloud.s3.bucket.application.ports.input.bucket_object

import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket_object.request.BucketObjectRequest
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket_object.response.BucketObjectDeleteResponse

interface DeleteBucketObjectUseCase {
    fun removeObjectAll(bucketObjectRequestList: List<BucketObjectRequest>): BucketObjectDeleteResponse
}