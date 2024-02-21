package lol.cloud.lolcloud.common.bucket.dto.request

import lol.cloud.lolcloud.common.bucket.domain.entity.Bucket

data class BucketCreate(
    val bucketName: String
) {

    fun toEntity(): Bucket {
        return Bucket(
            bucketName = bucketName,
        )
    }

}
