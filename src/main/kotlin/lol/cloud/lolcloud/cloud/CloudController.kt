package lol.cloud.lolcloud.cloud

import jakarta.servlet.http.HttpServletRequest
import jdk.jfr.ContentType
import lol.cloud.lolcloud.s3.common.error.S3ErrorException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.io.File

@RestController
class CloudController {

    @GetMapping("/static/{bucketName}/**")
    fun getObject(
        @PathVariable bucketName: String,
        httpServletRequest: HttpServletRequest
    ) : ResponseEntity<ByteArray>? {

        val filePath = httpServletRequest.requestURI.split(bucketName)[1]

//        val file = File("D://$bucketName$filePath")
        val file = File("/home/$bucketName$filePath")

        if(file.exists() && file.isFile) {

            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(file.readBytes())

        }

        throw S3ErrorException(HttpStatus.NOT_FOUND, "존재하지 않는 파일입니다.")
    }

}