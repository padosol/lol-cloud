package lol.cloud.lolcloud.s3.bucket.dto.bucket_object.request

import lol.cloud.lolcloud.s3.bucket.domain.bucket_object.ObjectType

data class BucketObjectCreate(
    val objectName: String,
    val objectType: ObjectType,
    val prefix: String,
    val objectSize: Long,
) {
}