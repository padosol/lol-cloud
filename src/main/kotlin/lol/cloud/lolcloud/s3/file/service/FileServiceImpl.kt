package lol.cloud.lolcloud.s3.file.service

import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.output.persistence.entity.BucketObjectEntity
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity.ObjectType
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.input.rest.dto.request.BucketObjectRequest
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.repository.BucketRepository
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.output.persistence.repository.BucketObjectRepository
import lol.cloud.lolcloud.s3.common.error.S3ErrorException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Service
class FileServiceImpl(
    private val bucketObjectRepository: BucketObjectRepository,
    private val bucketRepository: BucketRepository,
) : FileService {

    private val log = LoggerFactory.getLogger(this.javaClass)!!

    @Value("\${folder.rootPath}")
    lateinit var rootPath: String
    override fun upload(bucketObjectRequest: BucketObjectRequest, multipartFile: MultipartFile): String {

        val fileName = multipartFile.originalFilename
            ?: throw S3ErrorException(HttpStatus.BAD_REQUEST, "파일명이 존재하지 않습니다.")

        val contentType = multipartFile.contentType
        val size = multipartFile.size

        // bucket 찾아서
        val bucket = bucketRepository.findBucketByBucketName(bucketObjectRequest.bucketName)
            ?: throw S3ErrorException(HttpStatus.NOT_FOUND, "존재하지 않는 버킷입니다.")

        // bucketObject 생성

        val bucketObjectEntity = BucketObjectEntity(
            objectName = fileName,
            objectType = ObjectType.FILE,
            prefix = bucketObjectRequest.prefix,
            objectSize = size,
            objectExt = contentType,
            createDate = bucketObjectRequest.uploadDate,
            key = bucketObjectRequest.prefix + fileName,
            bucket = bucket
        )

        bucketObjectEntity.createObjectUrl()
        bucketObjectEntity.addDetailObjectName()
        bucketObjectEntity.createBucketObjectKey()

        bucketObjectRepository.findBucketObjectByBucketAndKeyAndObjectType(bucket, bucketObjectRequest.prefix, ObjectType.FOLDER)
            ?. let {
                bucketObjectEntity.addParent(it)
            }

        val saveBucketObject = bucketObjectRepository.save(bucketObjectEntity)

        // bucketObject 넣기 파일이니깐 키 값도 있으면 좋을듯

        val filePath = "${bucketObjectRequest.bucketName}/${bucketObjectRequest.prefix}"

//        val file = File("/home/$filePath$fileName")
        val file = File("${rootPath}$filePath$fileName")

        multipartFile.transferTo(file)

        return "success"
    }

    override fun getFileByte(bucketName: String, fileName: String, prefix: String): ByteArray {
        TODO("Not yet implemented")
    }

    override fun removeFile(bucketObjectRequest: BucketObjectRequest): Boolean {

        val filePath = "${bucketObjectRequest.bucketName}/${bucketObjectRequest.prefix}${bucketObjectRequest.objectName}"
//        val file = File("/home/$filePath")
        val file = File("D://$filePath")

        if(file.exists()) {
            if(file.delete()){
                log.debug("파일이 삭제되었습니다. PATH $filePath")

                return true
            }
        }

        log.debug("존재하지 않는 파일입니다. PATH: $filePath")

        return false
    }
}