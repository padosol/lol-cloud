package lol.cloud.lolcloud.s3.common.error

import org.springframework.http.HttpStatus

class S3ErrorException(
    val statusCode: HttpStatus,
    override val message: String,
) : RuntimeException() {
}