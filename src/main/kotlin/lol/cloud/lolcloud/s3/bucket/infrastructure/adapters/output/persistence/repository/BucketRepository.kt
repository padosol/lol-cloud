package lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.repository

import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.BucketEntity
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BucketRepository : JpaRepository<BucketEntity, String>{

    fun findBucketByBucketName(bucketName: String): BucketEntity?

    fun findBucketEntityByBucketNameAndUserEmail(bucketName: String, email: String): BucketEntity?

    fun findAllByUser(user: UserEntity): List<BucketEntity>

}