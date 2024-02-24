package lol.cloud.lolcloud.s3.bucket.service

import lol.cloud.lolcloud.s3.bucket.domain.bucket_object.BucketObject
import lol.cloud.lolcloud.s3.bucket.dto.bucket_object.response.BucketObjectResponse

interface BucketObjectService {
    fun getObject(bucketName: String, objectName: String): BucketObjectResponse
}