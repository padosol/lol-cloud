package lol.cloud.lolcloud.s3.bucket.service

import lol.cloud.lolcloud.s3.bucket.domain.bucket.Bucket
import lol.cloud.lolcloud.s3.bucket.dto.bucket.response.BucketResponse
import lol.cloud.lolcloud.s3.bucket.dto.bucket_object.request.BucketObjectRequest
import lol.cloud.lolcloud.s3.bucket.dto.bucket_object.response.BucketObjectResponse

interface BucketService {
    fun createBucket(bucket: Bucket): BucketResponse
    fun getBuckets(): List<BucketResponse>
    fun getBucket(bucketName: String, bucketObjectRequest: BucketObjectRequest): List<BucketObjectResponse>

}