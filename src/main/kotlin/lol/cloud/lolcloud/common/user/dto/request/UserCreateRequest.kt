package lol.cloud.lolcloud.common.user.dto.request

import jakarta.validation.constraints.NotBlank

data class UserCreateRequest(
    @NotBlank(message = "이메일을 입력해주세요.")
    val email: String,
    @NotBlank(message = "비밀번호를 입력해주세요.")
    val password: String,

    val username: String,
)