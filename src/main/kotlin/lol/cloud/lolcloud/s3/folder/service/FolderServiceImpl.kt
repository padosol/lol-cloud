package lol.cloud.lolcloud.s3.folder.service

import lol.cloud.lolcloud.s3.common.error.S3ErrorException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.io.File

@Service
class FolderServiceImpl(

) : FolderService {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Value("\${folder.rootPath}")
    lateinit var rootPath: String

    override fun createFolder(bucketName: String, folderName: String, prefix: String) {

        val path = "${rootPath}$bucketName/$prefix$folderName"

        val file = File(path)

        if(!file.mkdir()) {
            log.info("폴덩경로: {}", path)
            log.info("파일: {}", file)

            throw S3ErrorException(HttpStatus.BAD_REQUEST, "이미 존재하는 폴더명 입니다.")
        }

    }

    override fun deleteFolder(bucketName: String, folderName: String, prefix: String) {
        TODO("Not yet implemented")
    }
}