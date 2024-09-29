package lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket_object.request

import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.ObjectType

data class BucketObjectCreate(
    val objectName: String,
    val objectType: ObjectType,
    val prefix: String = "",
    val objectSize: Long = 0L,
    val parentId: Long? = null,
) {
}