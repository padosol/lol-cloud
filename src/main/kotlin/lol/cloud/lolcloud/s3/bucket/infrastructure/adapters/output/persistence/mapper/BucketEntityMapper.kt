package lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.mapper

import lol.cloud.lolcloud.s3.bucket.domain.Bucket
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.BucketEntity
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.mapper.UserEntityMapper

class BucketEntityMapper {

    companion object {
        fun toEntity(bucket: Bucket): BucketEntity {
            return BucketEntity(
                bucket.bucketName,
                bucket.createDate,
                UserEntityMapper.toUserEntity(bucket.user!!),
                bucket.publicAccess
            )
        }

        fun toBucket(bucketEntity: BucketEntity): Bucket {
            return Bucket(
                bucketName = bucketEntity.bucketName,
                createDate = bucketEntity.createDate,
                user = UserEntityMapper.toUser(bucketEntity.user!!),
                publicAccess = bucketEntity.publicAccess,
                bucketObjects = mutableListOf()
            )
        }
    }
}