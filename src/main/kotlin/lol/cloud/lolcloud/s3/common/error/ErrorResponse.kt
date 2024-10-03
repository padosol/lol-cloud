package lol.cloud.lolcloud.s3.common.error


data class ErrorResponse(
    val statusCode: Int,
    val message: String,
) {
}