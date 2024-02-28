package lol.cloud.lolcloud.s3.bucket.dto.bucket_object.request

data class BucketObjectRequest(
    val prefix: String = "",
    val bucketName: String? = null,
    val objectName: String? = null
) {
}