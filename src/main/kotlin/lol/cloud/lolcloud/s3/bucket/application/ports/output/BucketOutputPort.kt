package lol.cloud.lolcloud.s3.bucket.application.ports.output

import lol.cloud.lolcloud.s3.bucket.domain.Bucket
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.BucketEntity
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.entity.UserEntity

interface BucketOutputPort {

    fun saveBucket(bucketEntity: BucketEntity): Bucket

    fun findBucketByBucketName(bucketName: String): Bucket?

    fun findBucketByBucketNameAndEmail(bucketName: String, email: String): Bucket?

    fun findAllByUser(user: UserEntity): List<Bucket>

}