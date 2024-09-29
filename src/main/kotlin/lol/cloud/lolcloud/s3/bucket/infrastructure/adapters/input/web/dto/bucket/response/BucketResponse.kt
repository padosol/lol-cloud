package lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket.response

import java.time.LocalDateTime

data class BucketResponse (
    val bucketName: String,
    val publicAccess: Boolean,
    val createDate: LocalDateTime,
)