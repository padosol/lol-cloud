package lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket_object.request

import java.time.LocalDateTime

data class BucketObjectRequest(
    val prefix: String = "",
    val bucketName: String = "",
    val objectName: String? = null,
    val uploadDate: LocalDateTime? = null,
    val parentId: Long? = null,
    val id: Long? = null,
)