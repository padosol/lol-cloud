package lol.cloud.lolcloud.s3.bucket.dto.bucket_object.response

data class BucketObjectDeleteResponse(
    var success: Int,
    var totalSize: Long,
    var fail: Int
) {
}