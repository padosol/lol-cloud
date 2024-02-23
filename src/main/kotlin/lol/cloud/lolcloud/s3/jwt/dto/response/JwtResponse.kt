package lol.cloud.lolcloud.s3.jwt.dto.response

data class JwtResponse(
    val token: String,
    val email: String,
)