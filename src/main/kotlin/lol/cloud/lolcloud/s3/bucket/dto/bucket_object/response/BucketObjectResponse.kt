package lol.cloud.lolcloud.s3.bucket.dto.bucket_object.response

import java.time.LocalDateTime

data class BucketObjectResponse(
    val prefix: String,
    val objectName: String,
    val objectType: String,
    val id: Long,
    val modifyDate: LocalDateTime,
    val objectSize: Long,
)