package lol.cloud.lolcloud.common.`object`.dto.request

import org.springframework.web.multipart.MultipartFile

data class BucketObjectRequest(
    val fileName: String,
    val file: MultipartFile?,
    val key: String?
) {





}