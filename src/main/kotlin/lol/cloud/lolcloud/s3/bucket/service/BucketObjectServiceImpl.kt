package lol.cloud.lolcloud.s3.bucket.service

import jakarta.persistence.*
import lol.cloud.lolcloud.s3.bucket.domain.bucket.Bucket
import lol.cloud.lolcloud.s3.bucket.domain.bucket_object.BucketObject
import lol.cloud.lolcloud.s3.bucket.domain.bucket_object.ObjectType
import lol.cloud.lolcloud.s3.bucket.dto.bucket_object.request.BucketObjectCreate
import lol.cloud.lolcloud.s3.bucket.dto.bucket_object.response.BucketObjectResponse
import lol.cloud.lolcloud.s3.bucket.repository.bucket.BucketRepository
import lol.cloud.lolcloud.s3.bucket.repository.bucket_object.BucketObjectRepository
import lol.cloud.lolcloud.s3.common.error.S3ErrorException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class BucketObjectServiceImpl(
    private val bucketObjectRepository: BucketObjectRepository,
    private val bucketRepository: BucketRepository,
) : BucketObjectService{
    override fun getObject(bucketName: String, objectName: String): BucketObjectResponse {

        val bucket = bucketRepository.findBucketByBucketName(bucketName)
            ?: throw S3ErrorException(HttpStatus.BAD_REQUEST, "존재하지 않는 버킷 이름입니다.")

        val bucketObject = bucketObjectRepository.findBucketObjectByBucketAndObjectName(bucket, objectName)
            ?: throw S3ErrorException(HttpStatus.NOT_FOUND, "존재하지 않는 객체 이름입니다.")

        return bucketObject.toDto()
    }

    override fun createObject(bucketName: String, bucketObjectCreate: BucketObjectCreate): BucketObjectResponse {

        val bucket = bucketRepository.findBucketByBucketName(bucketName)
            ?: throw S3ErrorException(HttpStatus.NOT_FOUND, "존재하지 않는 버킷 이름 입니다.")

        val objectName = when(bucketObjectCreate.objectType) {
                                ObjectType.FOLDER -> "${bucketObjectCreate.objectName}/"
                                else -> bucketObjectCreate.objectName
                            }

        val bucketObject = BucketObject(
            objectName = objectName,
            objectType = bucketObjectCreate.objectType,
            prefix = bucketObjectCreate.prefix,
            objectSize = bucketObjectCreate.objectSize,
            createDate = LocalDateTime.now(),
            null,
            bucket,
            null
        )

        return bucketObjectRepository.save(bucketObject).toDto()
    }


}