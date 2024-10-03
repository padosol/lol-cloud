package lol.cloud.lolcloud.s3.bucket_object.domain

import jakarta.transaction.Transactional
import lol.cloud.lolcloud.s3.bucket_object.application.port.input.CreateBucketObjectUseCase
import lol.cloud.lolcloud.s3.bucket_object.application.port.input.DeleteBucketObjectUseCase
import lol.cloud.lolcloud.s3.bucket_object.application.port.input.GetBucketObjectUseCase
import lol.cloud.lolcloud.s3.bucket.application.ports.output.BucketOutputPort
import lol.cloud.lolcloud.s3.bucket_object.application.port.output.BucketObjectOutputPort
import lol.cloud.lolcloud.s3.bucket.domain.Bucket
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.response.BucketResponse
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.ObjectType
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.input.rest.dto.request.BucketObjectRequest
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.input.rest.dto.response.BucketObjectDeleteResponse
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.input.rest.dto.response.BucketObjectResponse
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.input.rest.mapper.BucketObjectMapper
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.mapper.BucketEntityMapper
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.output.persistence.mapper.BucketObjectEntityMapper
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.output.persistence.repository.BucketObjectRepository
import lol.cloud.lolcloud.s3.common.error.S3ErrorException
import lol.cloud.lolcloud.s3.file.service.FileService
import lol.cloud.lolcloud.s3.folder.service.FolderService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class BucketObjectService(
    private val bucketObjectRepository: BucketObjectRepository,
    private val folderService: FolderService,
    private val fileService: FileService,
    private val bucketOutputPort: BucketOutputPort,
    private val bucketObjectOutputPort: BucketObjectOutputPort
) : CreateBucketObjectUseCase, DeleteBucketObjectUseCase, GetBucketObjectUseCase {

    override fun getObject(bucketId: Long): BucketObjectResponse {

        val bucketObject: BucketObject = bucketObjectOutputPort.findBucketById(bucketId)
            ?: throw S3ErrorException(HttpStatus.BAD_REQUEST, "존재하지 않는 객체 입니다.")

        return BucketObjectMapper.toResponse(bucketObject)
    }

    override fun getObjectAll(bucketObjectRequest: BucketObjectRequest): List<BucketObjectResponse> {

        val bucketObject = bucketObjectRequest.id?.let {
            bucketObjectRepository.findById(it).get()
        }

        return bucketObject?.getAllChildren()?.map { it.toDto() } ?: emptyList()
    }

    override fun getObjectByBucketIdAndObjectId(bucketId: String, objectId: String): BucketObjectResponse {

        val bucket = bucketOutputPort.findBucketByBucketName(bucketId)
            ?: throw S3ErrorException(HttpStatus.NOT_FOUND, "존재하지 않는 버킷입니다.")

        val bucketObject =
            bucketObjectOutputPort.findBucketObjectByObjectNameAndPrefixAndBucket(
                objectId, "", BucketEntityMapper.toEntity(bucket)
            ) ?: throw S3ErrorException(HttpStatus.NOT_FOUND, "존재하지 않는 객체입니다.")

        return BucketObjectMapper.toResponse(bucketObject)
    }

    override fun findBucketObjectByBucketIdAndObjectIdAndPrefix(
        bucketId: String,
        objectId: String,
        prefix: String
    ): BucketObjectResponse {

        val bucket = bucketOutputPort.findBucketByBucketName(bucketId)
            ?: throw S3ErrorException(HttpStatus.NOT_FOUND, "존재하지 않는 버킷입니다.")

        val bucketObject =
            bucketObjectOutputPort.findBucketObjectByObjectNameAndPrefixAndBucket(
                objectId, prefix, BucketEntityMapper.toEntity(bucket)
            ) ?: throw S3ErrorException(HttpStatus.NOT_FOUND, "존재하지 않는 객체입니다.")

        return BucketObjectMapper.toResponse(bucketObject)
    }


    @Transactional
    override fun removeObjectAll(bucketObjectRequestList: List<BucketObjectRequest>): BucketObjectDeleteResponse {

        val objectDeleteResult = BucketObjectDeleteResponse(0,0,0)

        val ids = bucketObjectRequestList.map { it.id }

        bucketObjectRepository.deleteAllById(ids)

        for(bucketObjectRequest in bucketObjectRequestList) {
            if(fileService.removeFile(bucketObjectRequest)) {
                objectDeleteResult.success += 1
            }else {
                objectDeleteResult.fail += 1
            }
        }

        return objectDeleteResult
    }

    override fun createObject(bucketObject: BucketObject): BucketObjectResponse {

        val bucket: Bucket = bucketOutputPort.findBucketByBucketName(bucketObject.bucketName)
            ?: throw S3ErrorException(HttpStatus.NOT_FOUND, "존재하지 않는 버킷 이름 입니다.")

        bucketObject.createObjectUrl()
        bucketObject.addDetailObjectName()
        bucketObject.createBucketObjectKey()

        if(bucketObject.isFolder()) {
            folderService.createFolder(
                bucketObject.bucketName,
                bucketObject.objectName,
                bucketObject.prefix
            )
        }

        bucketObjectOutputPort.findBucketObjectByBucketAndKeyAndObjectType(
            BucketEntityMapper.toEntity(bucket),
            bucketObject.prefix,
            ObjectType.FOLDER
        )?. let {
            bucketObject.addParent(it)
        }

        val saveBucketObject =
            bucketObjectOutputPort.saveBucketObject(
                BucketObjectEntityMapper.toEntity(bucketObject, bucket)
            )

        return BucketObjectMapper.toResponse(saveBucketObject)
    }

}


