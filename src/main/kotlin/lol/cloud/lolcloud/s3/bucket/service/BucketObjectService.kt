package lol.cloud.lolcloud.s3.bucket.service

import lol.cloud.lolcloud.s3.bucket.dto.bucket_object.request.BucketObjectCreate
import lol.cloud.lolcloud.s3.bucket.dto.bucket_object.request.BucketObjectRequest
import lol.cloud.lolcloud.s3.bucket.dto.bucket_object.response.BucketObjectResponse

interface BucketObjectService {
    fun getObject(bucketObjectRequest: BucketObjectRequest): BucketObjectResponse

    fun createObject(bucketName: String, bucketObjectCreate: BucketObjectCreate): BucketObjectResponse

    fun getObjectAll(bucketObjectRequest: BucketObjectRequest): List<BucketObjectResponse>

    fun removeObjectAll(bucketObjectRequestList: List<BucketObjectRequest>): Int


}
