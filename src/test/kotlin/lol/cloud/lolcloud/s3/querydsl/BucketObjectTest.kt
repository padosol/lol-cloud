package lol.cloud.lolcloud.s3.querydsl

import jakarta.persistence.*
import lol.cloud.lolcloud.s3.bucket.domain.bucket.Bucket
import lol.cloud.lolcloud.s3.bucket.domain.bucket_object.BucketObject
import lol.cloud.lolcloud.s3.bucket.domain.bucket_object.ObjectType
import lol.cloud.lolcloud.s3.bucket.repository.bucket.BucketRepository
import lol.cloud.lolcloud.s3.bucket.repository.bucket_object.BucketObjectRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime

@SpringBootTest
@ActiveProfiles("test")
class BucketObjectTest @Autowired constructor(
    private val bucketObjectRepository: BucketObjectRepository,
    private val bucketRepository: BucketRepository,

) {

    @Test
    fun bucket_object_search() {

        // given
        bucketRepository.save(Bucket(
            bucketName = "bucket"
        ))
        val bucket = bucketRepository.findBucketByBucketName("bucket")
            ?: throw IllegalArgumentException("존재하지 않는 버킷 이름입니다.")

        val bucketObject1 = BucketObject(
            objectName = "test",
            objectType = ObjectType.FOLDER,
            prefix = "",
            createDate = LocalDateTime.now(),
            bucket = bucket
        )

        val bucketObject2 = BucketObject(
            objectName = "test2",
            objectType = ObjectType.FOLDER,
            prefix = "",
            createDate = LocalDateTime.now(),
            bucket = bucket
        )

        bucketObjectRepository.save(bucketObject1)
        bucketObjectRepository.save(bucketObject2)

        // when
        val findAllByBucket = bucketObjectRepository.findAllByBucket(bucket)


        // then
        Assertions.assertThat(findAllByBucket.size).isEqualTo(2)
    }






}