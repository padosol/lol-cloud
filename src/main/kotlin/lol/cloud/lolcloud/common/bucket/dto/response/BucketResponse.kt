package lol.cloud.lolcloud.common.bucket.dto.response

import java.time.LocalDateTime

data class BucketResponse (
    val bucketName: String,
    val publicAccess: Boolean,
    val createDate: LocalDateTime,
)