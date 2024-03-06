package lol.cloud.lolcloud.s3.bucket.service

import jakarta.persistence.*
import lol.cloud.lolcloud.s3.bucket.domain.bucket.Bucket
import lol.cloud.lolcloud.s3.bucket.domain.bucket_object.BucketObject
import lol.cloud.lolcloud.s3.bucket.domain.bucket_object.ObjectType
import lol.cloud.lolcloud.s3.bucket.dto.bucket_object.request.BucketObjectCreate
import lol.cloud.lolcloud.s3.bucket.dto.bucket_object.request.BucketObjectRequest
import lol.cloud.lolcloud.s3.bucket.dto.bucket_object.response.BucketObjectResponse
import lol.cloud.lolcloud.s3.bucket.repository.bucket.BucketRepository
import lol.cloud.lolcloud.s3.bucket.repository.bucket_object.BucketObjectRepository
import lol.cloud.lolcloud.s3.common.error.S3ErrorException
import lol.cloud.lolcloud.s3.folder.service.FolderService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class BucketObjectServiceImpl(
    private val bucketObjectRepository: BucketObjectRepository,
    private val bucketRepository: BucketRepository,
    private val folderService: FolderService,
) : BucketObjectService{
    override fun getObject(bucketObjectRequest: BucketObjectRequest): BucketObjectResponse {

        val bucket = bucketRepository.findBucketByBucketName(bucketObjectRequest.bucketName?:"'")
            ?: throw S3ErrorException(HttpStatus.BAD_REQUEST, "존재하지 않는 버킷 이름입니다.")

        val bucketObject = bucketObjectRepository.findBucketObjectByBucketAndObjectNameAndPrefix(bucket, bucketObjectRequest.objectName?:"", bucketObjectRequest.prefix?:"")
            ?: throw S3ErrorException(HttpStatus.NOT_FOUND, "존재하지 않는 객체 이름입니다.")

        return bucketObject.toDto()
    }

    override fun createObject(bucketName: String, bucketObjectCreate: BucketObjectCreate): BucketObjectResponse {

        val bucket = bucketRepository.findBucketByBucketName(bucketName)
            ?: throw S3ErrorException(HttpStatus.NOT_FOUND, "존재하지 않는 버킷 이름 입니다.")

        val bucketObject = BucketObject(
            objectName = bucketObjectCreate.objectName,
            objectType = bucketObjectCreate.objectType,
            prefix = bucketObjectCreate.prefix,
            objectSize = bucketObjectCreate.objectSize,
            createDate = LocalDateTime.now(),
            bucket = bucket,
        )

        bucketObject.createObjectUrl()
        bucketObject.addDetailObjectName()

        if(bucketObject.objectType == ObjectType.FOLDER) {
            folderService.createFolder(bucketName, bucketObjectCreate.objectName, bucketObjectCreate.prefix)
        }

        bucketObjectCreate.parentId?.let {
            val parentBucketObject = bucketObjectRepository.findById(it).orElseThrow { throw S3ErrorException(HttpStatus.BAD_REQUEST, "존재하지 않는 객체입니다.") }
            bucketObject.addParent(parentBucketObject)
        }

        return bucketObjectRepository.save(bucketObject).toDto()
    }

    override fun getObjectAll(bucketObjectRequest: BucketObjectRequest): List<BucketObjectResponse> {


        TODO("Not yet implemented")
    }


}