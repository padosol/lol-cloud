package lol.cloud.lolcloud.s3.bucket.repository.bucket

import lol.cloud.lolcloud.s3.bucket.domain.bucket.Bucket
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BucketRepository : JpaRepository<Bucket, String>{

    fun findBucketByBucketName(bucketName: String): Bucket?

}