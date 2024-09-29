package lol.cloud.lolcloud.s3.bucket.application.ports.input.bucket_object

import lol.cloud.lolcloud.s3.bucket.domain.BucketObject
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket_object.response.BucketObjectResponse

interface CreateBucketObjectUseCase {
    //bucketName: String, bucketObjectCreate: BucketObjectCreate
    fun createObject(bucketObject: BucketObject): BucketObjectResponse
}