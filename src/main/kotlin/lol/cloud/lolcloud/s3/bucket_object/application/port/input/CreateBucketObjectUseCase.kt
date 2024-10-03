package lol.cloud.lolcloud.s3.bucket_object.application.port.input

import lol.cloud.lolcloud.s3.bucket_object.domain.BucketObject
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.input.rest.dto.response.BucketObjectResponse

interface CreateBucketObjectUseCase {
    //bucketName: String, bucketObjectCreate: BucketObjectCreate
    fun createObject(bucketObject: BucketObject): BucketObjectResponse
}