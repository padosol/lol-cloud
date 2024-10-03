package lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.request

import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.BucketEntity

data class BucketCreate(
    val bucketName: String
) {
    fun toEntity(): BucketEntity {
        return BucketEntity(
            bucketName = bucketName,
        )
    }

}
