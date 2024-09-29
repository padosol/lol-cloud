package lol.cloud.lolcloud.s3.user.infrastructure.adapters.input.web.dto.request

data class LoginRequest(
    val email :String,
    val password: String,
) {
}