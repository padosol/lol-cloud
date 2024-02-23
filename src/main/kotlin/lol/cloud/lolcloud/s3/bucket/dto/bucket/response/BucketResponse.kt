package lol.cloud.lolcloud.s3.bucket.dto.bucket.response

import java.time.LocalDateTime

data class BucketResponse (
    val bucketName: String,
    val publicAccess: Boolean,
    val createDate: LocalDateTime,
)