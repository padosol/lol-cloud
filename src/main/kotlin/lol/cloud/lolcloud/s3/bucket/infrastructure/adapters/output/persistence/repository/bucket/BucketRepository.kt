package lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.repository.bucket

import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.BucketEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BucketRepository : JpaRepository<BucketEntity, String>{

    fun findBucketByBucketName(bucketName: String): BucketEntity?

}