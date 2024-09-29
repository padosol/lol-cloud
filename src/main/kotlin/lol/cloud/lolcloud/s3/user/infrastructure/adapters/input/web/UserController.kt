package lol.cloud.lolcloud.s3.user.infrastructure.adapters.input.web

import jakarta.validation.Valid
import lol.cloud.lolcloud.s3.user.domain.model.User
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.input.web.dto.request.UserCreateRequest
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.input.web.dto.response.UserResponse
import lol.cloud.lolcloud.s3.user.domain.service.UserService
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.input.web.mapper.UserMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/api")
class UserController(
    private val userService: UserService,
    private val userMapper: UserMapper
) {

    @PostMapping("/signup")
    fun signup(
        @RequestBody @Valid userCreateRequest: UserCreateRequest,
    ): ResponseEntity<UserResponse> {

        val userResponse = userService.signup(userMapper.toUser(userCreateRequest))

        return ResponseEntity(userResponse, HttpStatus.OK)
    }

}