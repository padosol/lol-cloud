package lol.cloud.lolcloud.common.`object`.service

import lol.cloud.lolcloud.common.`object`.dto.request.BucketObjectRequest
import org.springframework.stereotype.Service

@Service
class ObjectService {

    fun getFile(filePath: String) {

        val filePaths = filePath.split("/")

    }


    fun createObject(bucketObjectRequest: BucketObjectRequest) {

    }

    fun modifyObject() {

    }

    fun removeObject() {

    }


}