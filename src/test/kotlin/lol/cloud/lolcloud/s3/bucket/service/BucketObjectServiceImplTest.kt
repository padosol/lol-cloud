package lol.cloud.lolcloud.s3.bucket.service

import lol.cloud.lolcloud.s3.bucket.domain.bucket_object.BucketObject
import lol.cloud.lolcloud.s3.bucket.repository.bucket_object.BucketObjectRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class BucketObjectServiceImplTest @Autowired constructor(

    private val bucketObjectRepository: BucketObjectRepository,

){

    @Test
    fun bucketObject_insert() {

    }
}