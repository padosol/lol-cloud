package lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket.request

data class BucketRemove(
    val bucketName: String,
    val email: String
)