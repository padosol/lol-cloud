package lol.cloud.lolcloud.s3.bucket.repository.bucket_object.impl

import com.querydsl.jpa.impl.JPAQueryFactory
import lol.cloud.lolcloud.s3.bucket.domain.bucket_object.BucketObject
import lol.cloud.lolcloud.s3.bucket.domain.bucket_object.QBucketObject
import lol.cloud.lolcloud.s3.bucket.repository.bucket_object.BucketObjectRepositoryCustom

class BucketObjectRepositoryCustomImpl(
    private val jpaQueryFactory: JPAQueryFactory,
) : BucketObjectRepositoryCustom {
    override fun findByBucketTest(): List<BucketObject> {
        val bucketObject = QBucketObject.bucketObject

        return jpaQueryFactory.selectFrom(bucketObject)
            .where(bucketObject.objectName.eq("test"))
            .fetch()
    }
}