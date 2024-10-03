package lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.input.rest.dto.request

data class BucketObjectCondition(
    val prefix: String,
    val bucketId: String
)