package lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.output.persistence.repository.impl

import com.querydsl.jpa.impl.JPAQueryFactory
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.BucketEntity
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.output.persistence.entity.BucketObjectEntity
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.output.persistence.entity.QBucketObjectEntity
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.output.persistence.repository.BucketObjectRepositoryCustom
import org.springframework.stereotype.Repository

@Repository
class BucketObjectRepositoryCustomImpl(
    private val jpaQueryFactory: JPAQueryFactory,
) : BucketObjectRepositoryCustom {
    override fun findByBucketTest(): List<BucketObjectEntity> {
        val bucketObject = QBucketObjectEntity.bucketObjectEntity

        return jpaQueryFactory.selectFrom(bucketObject)
            .where(bucketObject.objectName.eq("test"))
            .fetch()
    }

    override fun findAllBucketObjectTypeFolderByBucketAndObjectNameAndPrefixForRecursive(
        bucketEntity: BucketEntity,
        objectName: String,
        prefix: String
    ) {


    }

}