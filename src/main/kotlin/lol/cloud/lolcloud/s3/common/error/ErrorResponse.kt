package lol.cloud.lolcloud.s3.common.error

import org.springframework.http.HttpStatus

data class ErrorResponse(
    val statusCode: Int,
    val message: String,
) {
}