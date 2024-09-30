package lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.mapper

import lol.cloud.lolcloud.s3.bucket.domain.BucketObject
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket_object.request.BucketObjectCreate
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket_object.response.BucketObjectResponse
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.ObjectType
import java.time.LocalDateTime

class BucketObjectMapper {

    companion object {
        fun toResponse(bucketObject: BucketObject): BucketObjectResponse {
            return bucketObject.let {
                BucketObjectResponse(
                    prefix = it.prefix,
                    objectName = it.objectName,
                    objectType = it.objectType.name,
                    id = it.parentId!!,
                    createDate = null,
                    modifyDate = null,
                    objectSize = null,
                    key = null,
                    objectExt = null,
                    bucketName = null,
                )
            }
        }

        fun toBucketObject(bucketName: String, bucketObjectCreate: BucketObjectCreate): BucketObject {
            return BucketObject(
                bucketName = bucketName,
                objectName = bucketObjectCreate.objectName,
                objectType = bucketObjectCreate.objectType,
                prefix = bucketObjectCreate.prefix,
                objectSize = bucketObjectCreate.objectSize,
                parentId = null,
                objectUrl = "",
                bucketObject = null,
                key = ""
            )
        }

    }
}