package lol.cloud.lolcloud.s3.user.application.ports.input

import lol.cloud.lolcloud.s3.user.domain.model.User
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.input.web.dto.response.UserResponse

interface CreateUserUseCase {
    fun signup(user: User): UserResponse
}