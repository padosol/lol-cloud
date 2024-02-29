package lol.cloud.lolcloud.s3.folder.service

import org.springframework.stereotype.Service
import java.io.File

@Service
class FolderServiceImpl : FolderService {
    override fun createFolder(bucketName: String, folderName: String, prefix: String) {

        val path = "D:/$bucketName/$prefix$folderName"

        val file = File(path)

        if(!file.mkdir()) {
            throw IllegalArgumentException("폴더 생성에 실패 했습니다.")
        }

    }

    override fun deleteFolder(bucketName: String, folderName: String, prefix: String) {
        TODO("Not yet implemented")
    }
}