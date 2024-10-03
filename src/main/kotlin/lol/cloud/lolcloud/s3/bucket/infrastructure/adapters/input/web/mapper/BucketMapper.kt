package lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.mapper

import lol.cloud.lolcloud.s3.bucket.domain.Bucket
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.request.BucketCreate
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.request.BucketModify
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.response.BucketResponse
import java.time.LocalDateTime

class BucketMapper {

    companion object {
        fun toDto(bucket: Bucket): BucketResponse {
            return BucketResponse(
                bucketName = bucket.bucketName,
                publicAccess = bucket.publicAccess,
                createDate = bucket.createDate,
            )
        }
        fun toBucket(bucketCreate: BucketCreate): Bucket {
            return Bucket(
                bucketName = bucketCreate.bucketName,
            )
        }

        fun toBucket(bucketModify: BucketModify): Bucket {
            return Bucket(
                bucketName = bucketModify.bucketName,
            )
        }

    }
}