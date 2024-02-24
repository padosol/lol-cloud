package lol.cloud.lolcloud.s3.bucket.service

import lol.cloud.lolcloud.s3.bucket.dto.bucket_object.response.BucketObjectResponse
import lol.cloud.lolcloud.s3.bucket.repository.bucket.BucketRepository
import lol.cloud.lolcloud.s3.bucket.repository.bucket_object.BucketObjectRepository
import lol.cloud.lolcloud.s3.common.error.S3ErrorException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

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


}