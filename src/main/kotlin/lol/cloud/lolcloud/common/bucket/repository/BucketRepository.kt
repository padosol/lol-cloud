package lol.cloud.lolcloud.common.bucket.repository

import lol.cloud.lolcloud.common.bucket.domain.entity.Bucket
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BucketRepository : JpaRepository<Bucket, String>{

    fun findBucketByBucketName(bucketName: String): Bucket?

}