package lol.cloud.lolcloud.s3.user.controller

import jakarta.validation.Valid
import lol.cloud.lolcloud.s3.user.domain.User
import lol.cloud.lolcloud.s3.user.dto.request.UserCreateRequest
import lol.cloud.lolcloud.s3.user.dto.response.UserResponse
import lol.cloud.lolcloud.s3.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/api")
class UserController(
    private val userService: UserService,
) {

    @PostMapping("/signup")
    fun signup(
        @RequestBody @Valid userCreateRequest: UserCreateRequest,
    ): ResponseEntity<UserResponse> {

        val userResponse = userService.signup(user = userCreateRequest.toEntity())

        return ResponseEntity(userResponse, HttpStatus.OK)
    }

    private fun UserCreateRequest.toEntity(): User {
        return User(
            email = this.email,
            password = this.password,
            username = this.username,
            createData = LocalDateTime.now()
        )
    }

    @PutMapping("/users")
    fun modifyUser() {

    }

    @DeleteMapping("/users")
    fun removeUser() {

    }



}