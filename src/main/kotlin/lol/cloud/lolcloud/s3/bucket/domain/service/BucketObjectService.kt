package lol.cloud.lolcloud.s3.bucket.domain.service

import lol.cloud.lolcloud.s3.bucket.application.ports.input.bucket_object.CreateBucketObjectUseCase
import lol.cloud.lolcloud.s3.bucket.application.ports.input.bucket_object.DeleteBucketObjectUseCase
import lol.cloud.lolcloud.s3.bucket.application.ports.input.bucket_object.GetBucketObjectUseCase
import lol.cloud.lolcloud.s3.bucket.application.ports.output.bucket.BucketOutputPort
import lol.cloud.lolcloud.s3.bucket.application.ports.output.bucket_object.BucketObjectOutputPort
import lol.cloud.lolcloud.s3.bucket.domain.Bucket
import lol.cloud.lolcloud.s3.bucket.domain.BucketObject
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.BucketObjectEntity
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.ObjectType
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket_object.request.BucketObjectCreate
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket_object.request.BucketObjectRequest
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket_object.response.BucketObjectDeleteResponse
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket_object.response.BucketObjectResponse
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.mapper.BucketEntityMapper
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.mapper.BucketObjectEntityMapper
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.repository.bucket.BucketRepository
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.repository.bucket_object.BucketObjectRepository
import lol.cloud.lolcloud.s3.common.error.S3ErrorException
import lol.cloud.lolcloud.s3.file.service.FileService
import lol.cloud.lolcloud.s3.folder.service.FolderService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class BucketObjectService(
    private val bucketObjectRepository: BucketObjectRepository,
    private val folderService: FolderService,
    private val fileService: FileService,
    private val bucketOutputPort: BucketOutputPort,
    private val bucketObjectOutputPort: BucketObjectOutputPort
) : CreateBucketObjectUseCase, DeleteBucketObjectUseCase, GetBucketObjectUseCase {
    override fun getObject(bucketObjectRequest: BucketObjectRequest): BucketObjectResponse {

        val bucket = bucketOutputPort.findBucketByBucketName(bucketObjectRequest.bucketName?:"'")
            ?: throw S3ErrorException(HttpStatus.BAD_REQUEST, "존재하지 않는 버킷 이름입니다.")

        val bucketObject = bucketObjectRepository.findBucketObjectByBucketAndObjectNameAndPrefix(
            BucketEntityMapper.toEntity(bucket),
            bucketObjectRequest.objectName?:"",
            bucketObjectRequest.prefix?:""
        ) ?: throw S3ErrorException(HttpStatus.NOT_FOUND, "존재하지 않는 객체 이름입니다.")

        return bucketObject.toDto()
    }

//    override fun createObject(bucketName: String, bucketObjectCreate: BucketObjectCreate): BucketObjectResponse {
//
//        val bucket: Bucket = bucketOutputPort.findBucketByBucketName(bucketName)
//            ?: throw S3ErrorException(HttpStatus.NOT_FOUND, "존재하지 않는 버킷 이름 입니다.")
//
//        val bucketObjectEntity = BucketObjectEntity(
//            objectName = bucketObjectCreate.objectName,
//            objectType = bucketObjectCreate.objectType,
//            prefix = bucketObjectCreate.prefix,
//            objectSize = bucketObjectCreate.objectSize,
//            createDate = LocalDateTime.now(),
//            bucket = BucketEntityMapper.toEntity(bucket),
//        )
//
//        bucketObjectEntity.createObjectUrl()
//        bucketObjectEntity.addDetailObjectName()
//        bucketObjectEntity.createBucketObjectKey()
//
//        if(bucketObjectEntity.objectType == ObjectType.FOLDER) {
//            folderService.createFolder(bucketName, bucketObjectCreate.objectName, bucketObjectCreate.prefix)
//        }
//
//        bucketObjectRepository.findBucketObjectByBucketAndKeyAndObjectType(
//            BucketEntityMapper.toEntity(bucket),
//            bucketObjectCreate.prefix,
//            ObjectType.FOLDER
//        )
//            ?. let {
//                bucketObjectEntity.addParent(it)
//            }
//
//        return bucketObjectRepository.save(bucketObjectEntity).toDto()
//    }

    override fun getObjectAll(bucketObjectRequest: BucketObjectRequest): List<BucketObjectResponse> {

        val bucketObject = bucketObjectRequest.id?.let {
            bucketObjectRepository.findById(it).get()
        }

        return bucketObject?.getAllChildren()?.map { it.toDto() } ?: emptyList()
    }


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

        val bucket: Bucket = bucketOutputPort.findBucketByBucketName(bucketObject.getBucketName())
            ?: throw S3ErrorException(HttpStatus.NOT_FOUND, "존재하지 않는 버킷 이름 입니다.")

        bucketObject.createObjectUrl()
        bucketObject.addDetailObjectName()
        bucketObject.createBucketObjectKey()

//        val bucketObjectEntity = BucketObjectEntity(
//            objectName = bucketObjectCreate.objectName,
//            objectType = bucketObjectCreate.objectType,
//            prefix = bucketObjectCreate.prefix,
//            objectSize = bucketObjectCreate.objectSize,
//            createDate = LocalDateTime.now(),
//            bucket = BucketEntityMapper.toEntity(bucket),
//        )
//
//        bucketObjectEntity.createObjectUrl()
//        bucketObjectEntity.addDetailObjectName()
//        bucketObjectEntity.createBucketObjectKey()

        if(bucketObject.getObjectType() == ObjectType.FOLDER) {
            folderService.createFolder(
                bucketObject.getBucketName(),
                bucketObject.getObjectName(),
                bucketObject.getPrefix()
            )
        }

        bucketObjectOutputPort.findBucketObjectByBucketAndKeyAndObjectType(
            BucketEntityMapper.toEntity(bucket),
            bucketObject.getPrefix(),
            ObjectType.FOLDER
        )?. let {
            bucketObject.addParent(it)
        }

//        bucketObjectOutputPort.saveBucketObject(bucket)

//        bucketObjectRepository.findBucketObjectByBucketAndKeyAndObjectType(
//            BucketEntityMapper.toEntity(bucket),
//            bucketObjectCreate.prefix,
//            ObjectType.FOLDER
//        )?. let {
//                bucketObjectEntity.addParent(it)
//            }
//        bucketObjectRepository.save(bucketObjectEntity).toDto()
        return bucketObjectRepository.save(BucketObjectEntityMapper.toEntity(bucketObject, bucket)).toDto()
    }

}


