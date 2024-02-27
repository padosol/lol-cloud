package lol.cloud.lolcloud.s3.bucket.repository.bucket_object

import lol.cloud.lolcloud.s3.bucket.domain.bucket.Bucket
import lol.cloud.lolcloud.s3.bucket.domain.bucket_object.BucketObject
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BucketObjectRepository : JpaRepository<BucketObject, Long>, BucketObjectRepositoryCustom{

    fun findAllByBucket(bucket: Bucket): List<BucketObject>

    fun findBucketObjectByBucketAndObjectName(bucket: Bucket, objectName: String): BucketObject?




}