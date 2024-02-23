package lol.cloud.lolcloud.s3.user.dto.request

data class LoginRequest(
    val email :String,
    val password: String,
) {
}