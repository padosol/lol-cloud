package lol.cloud.lolcloud.s3.bucket.repository.bucket_object

import lol.cloud.lolcloud.s3.bucket.domain.bucket.Bucket
import lol.cloud.lolcloud.s3.bucket.domain.bucket_object.BucketObject
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BucketObjectRepository : JpaRepository<BucketObject, Long>, BucketObjectRepositoryCustom{

    fun findAllByBucket(bucket: Bucket): List<BucketObject>

    fun findBucketObjectByBucketAndObjectNameAndPrefix(bucket: Bucket, objectName: String, prefix: String): BucketObject?


    fun findAllByBucketAndPrefix(bucket: Bucket, prefix: String): List<BucketObject>

}