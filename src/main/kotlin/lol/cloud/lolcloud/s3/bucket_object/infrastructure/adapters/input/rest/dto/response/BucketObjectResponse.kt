package lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.input.rest.dto.response

import lol.cloud.lolcloud.s3.bucket_object.domain.BucketObject
import java.time.LocalDateTime

data class BucketObjectResponse(
    val prefix: String,
    val objectName: String,
    val objectType: String,
    val id: Long?,
    val createDate: LocalDateTime? = null,
    val modifyDate: LocalDateTime? = null,
    val objectSize: Long? = null,
    val key: String? = null,
    val objectExt: String? = null,
    val bucketName: String? = null,
    val bucketObjects: MutableList<BucketObject> = mutableListOf()
)