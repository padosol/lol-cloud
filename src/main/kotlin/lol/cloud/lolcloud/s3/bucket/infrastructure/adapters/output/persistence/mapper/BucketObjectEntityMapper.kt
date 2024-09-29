package lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.mapper

import jakarta.persistence.*
import lol.cloud.lolcloud.s3.bucket.domain.Bucket
import lol.cloud.lolcloud.s3.bucket.domain.BucketObject
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.BucketEntity
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.BucketObjectEntity
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.ObjectType
import java.time.LocalDateTime

class BucketObjectEntityMapper {

    companion object {
        fun toEntity(bucketObject: BucketObject, bucket: Bucket): BucketObjectEntity {

            return BucketObjectEntity(
                objectName = bucketObject.getObjectName(),
                objectType = bucketObject.getObjectType(),
                prefix = bucketObject.getPrefix(),
                objectSize = null,
                createDate = LocalDateTime.now(),
                modifyDate = null,
                objectExt = null,
                key = null,
                objectUrl = null,
                parent = null,
                children = mutableListOf(),
                bucket = BucketEntityMapper.toEntity(bucket),
                id = null,
            )
        }
    }
}