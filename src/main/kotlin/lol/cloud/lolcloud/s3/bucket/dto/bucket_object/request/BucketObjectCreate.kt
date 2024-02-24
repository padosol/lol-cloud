package lol.cloud.lolcloud.s3.bucket.dto.bucket_object.request

data class BucketObjectCreate(
    val objectName: String,
    val objectType: String
) {
}