package lol.cloud.lolcloud.s3.folder.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.File

@Service
class FolderServiceImpl : FolderService {

    private val log = LoggerFactory.getLogger(this.javaClass)
    override fun createFolder(bucketName: String, folderName: String, prefix: String) {

        //        val file = File("/home/sang/Desktop/cloud/$filePath")
//        val path = "D:/$bucketName/$prefix$folderName"
        val path = "/home/sang/Desktop/cloud/$bucketName/$prefix$folderName"

        val file = File(path)

        if(!file.mkdir()) {
            log.info("폴덩경로: {}", path)
            log.info("파일: {}", file)

            throw IllegalArgumentException("폴더 생성에 실패 했습니다.")
        }

        //test


    }

    override fun deleteFolder(bucketName: String, folderName: String, prefix: String) {
        TODO("Not yet implemented")
    }
}