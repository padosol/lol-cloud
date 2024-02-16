package lol.cloud.lolcloud.common.user.dto.response

import java.util.UUID

data class UserResponse(
    val uuid: UUID,
    val email: String,
)