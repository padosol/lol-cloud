package lol.cloud.lolcloud.s3.folder.service

interface FolderService {

    fun createFolder(bucketName: String, folderName: String, prefix: String)

    fun deleteFolder(bucketName: String, folderName: String, prefix: String)

}