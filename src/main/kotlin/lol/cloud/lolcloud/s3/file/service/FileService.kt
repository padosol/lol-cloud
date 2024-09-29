package lol.cloud.lolcloud.s3.file.service

import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket_object.request.BucketObjectRequest
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime

interface FileService {
    fun upload(bucketObjectRequest: BucketObjectRequest, multipartFile: MultipartFile): String

    fun getFileByte(bucketName: String, fileName: String, prefix: String): ByteArray

    fun removeFile(bucketObjectRequest: BucketObjectRequest): Boolean

}