package lol.cloud.lolcloud.s3.user.infrastructure.adapters.input.web.mapper

import lol.cloud.lolcloud.s3.user.domain.model.Authority
import lol.cloud.lolcloud.s3.user.domain.model.User
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.input.web.dto.request.UserCreateRequest
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.input.web.dto.response.UserResponse
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class UserMapper {

    fun toUser(userCreateRequest: UserCreateRequest): User{
        return User(
            userCreateRequest.email,
            userCreateRequest.password,
            userCreateRequest.username,
            LocalDateTime.now()
        )
    }

    fun toUserResponse(user: User): UserResponse {
        return UserResponse(
            user.email
        )
    }
}