package lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.mapper

import lol.cloud.lolcloud.s3.user.domain.model.Authority
import lol.cloud.lolcloud.s3.user.domain.model.User
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.entity.AuthorityEntity
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.entity.UserEntity
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class UserEntityMapper {

    companion object {
        fun toUserEntity(user: User): UserEntity {

            return UserEntity(
                user.email,
                user.password,
                user.username,
                LocalDateTime.now()
            )
        }

        fun toUser(userEntity: UserEntity): User {
            return User(
                userEntity.email,
                userEntity.password,
                userEntity.username,
                LocalDateTime.now(),
                userEntity.authorities.map { it -> toAuthority(it.authorityEntity) }.toMutableList()
            )
        }

        private fun toAuthority(authorityEntity: AuthorityEntity): Authority {
            return Authority(
                authorityEntity.authorityName!!
            )
        }
    }



}