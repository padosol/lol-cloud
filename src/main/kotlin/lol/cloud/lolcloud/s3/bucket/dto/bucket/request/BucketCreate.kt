package lol.cloud.lolcloud.s3.bucket.dto.bucket.request

import lol.cloud.lolcloud.s3.bucket.domain.bucket.Bucket

data class BucketCreate(
    val bucketName: String
) {

    fun toEntity(): Bucket {
        return Bucket(
            bucketName = bucketName,
        )
    }

}
