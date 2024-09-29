package lol.cloud.lolcloud.s3.user.application.ports.output

import lol.cloud.lolcloud.s3.user.domain.model.User
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.entity.AuthorityEntity
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.entity.UserAuthorityEntity
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.entity.UserEntity

interface UserOutputPort {
    fun saveUser(userEntity: UserEntity): User

    fun findOneWithAuthoritiesByEmail(email: String): User?

    fun findUserByEmail(email: String): User?

    fun findAuthorityByAuthName(authName: String): AuthorityEntity?

    fun saveUserAuthority(userAuthorityEntity: UserAuthorityEntity): UserAuthorityEntity

    fun saveAuthority(authorityEntity: AuthorityEntity): AuthorityEntity

}