package lol.cloud.lolcloud.s3.file.service

import jakarta.persistence.*
import lol.cloud.lolcloud.s3.bucket.domain.bucket.Bucket
import lol.cloud.lolcloud.s3.bucket.domain.bucket_object.BucketObject
import lol.cloud.lolcloud.s3.bucket.domain.bucket_object.ObjectType
import lol.cloud.lolcloud.s3.bucket.dto.bucket_object.request.BucketObjectRequest
import lol.cloud.lolcloud.s3.bucket.repository.bucket.BucketRepository
import lol.cloud.lolcloud.s3.bucket.repository.bucket_object.BucketObjectRepository
import lol.cloud.lolcloud.s3.common.error.S3ErrorException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.time.LocalDateTime

@Service
class FileServiceImpl(
    private val bucketObjectRepository: BucketObjectRepository,
    private val bucketRepository: BucketRepository,
) : FileService {

    private val log = LoggerFactory.getLogger(this.javaClass)!!
    override fun upload(bucketObjectRequest: BucketObjectRequest, multipartFile: MultipartFile): String {

        val fileName = multipartFile.originalFilename
            ?: throw S3ErrorException(HttpStatus.BAD_REQUEST, "파일명이 존재하지 않습니다.")

        val contentType = multipartFile.contentType
        val size = multipartFile.size

        // bucket 찾아서
        val bucket = bucketRepository.findBucketByBucketName(bucketObjectRequest.bucketName)
            ?: throw S3ErrorException(HttpStatus.NOT_FOUND, "존재하지 않는 버킷입니다.")

        // bucketObject 생성

        val bucketObject = BucketObject(
            objectName = fileName,
            objectType = ObjectType.FILE,
            prefix = bucketObjectRequest.prefix,
            objectSize = size,
            objectExt = contentType,
            createDate = bucketObjectRequest.uploadDate,
            key = bucketObjectRequest.prefix + fileName,
            bucket = bucket
        )

        bucketObject.createObjectUrl()
        bucketObject.addDetailObjectName()
        bucketObject.createBucketObjectKey()

        bucketObjectRepository.findBucketObjectByBucketAndKeyAndObjectType(bucket, bucketObjectRequest.prefix, ObjectType.FOLDER)
            ?. let {
                bucketObject.addParent(it)
            }

        val saveBucketObject = bucketObjectRepository.save(bucketObject)

        // bucketObject 넣기 파일이니깐 키 값도 있으면 좋을듯

        val filePath = "${bucketObjectRequest.bucketName}/${bucketObjectRequest.prefix}"

//        val file = File("/home/sang/Desktop/cloud/$filePath$fileName")
        val file = File("D://$filePath$fileName")

        multipartFile.transferTo(file)

        return "success"
    }

    override fun getFileByte(bucketName: String, fileName: String, prefix: String): ByteArray {
        TODO("Not yet implemented")
    }

    override fun removeFile(bucketObjectRequest: BucketObjectRequest): Boolean {

        val filePath = "${bucketObjectRequest.bucketName}/${bucketObjectRequest.prefix}${bucketObjectRequest.objectName}"
//        val file = File("/home/sang/Desktop/cloud/$filePath")
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