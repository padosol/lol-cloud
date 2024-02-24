package lol.cloud.lolcloud.s3.common.error

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorControllerAdvice {

    @ExceptionHandler
    fun errorHandler(s3ErrorException: S3ErrorException): ResponseEntity<ErrorResponse> {

        return ResponseEntity(
            ErrorResponse(
                s3ErrorException.statusCode.value(),
                s3ErrorException.message
            ),
            HttpStatus.OK
        )
    }

}