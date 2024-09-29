package lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence

import lol.cloud.lolcloud.s3.user.application.ports.output.UserOutputPort
import lol.cloud.lolcloud.s3.user.domain.model.User
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.entity.AuthorityEntity
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.entity.UserAuthorityEntity
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.entity.UserEntity
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.mapper.UserEntityMapper
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.repository.AuthorityRepository
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.repository.UserAuthorityRepository
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.repository.UserEntityRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.lang.RuntimeException

@Repository
class UserPersistenceAdapter(
    private val userEntityRepository: UserEntityRepository,
    private val authorityRepository: AuthorityRepository,
    private val userAuthorityRepository: UserAuthorityRepository,
) : UserOutputPort{
    override fun saveUser(userEntity: UserEntity): User {

        val saveUserEntity = userEntityRepository.save(userEntity)

        return UserEntityMapper.toUser(saveUserEntity)
    }

    override fun findOneWithAuthoritiesByEmail(email: String): User? {
        return userEntityRepository.findByIdOrNull(email)
            ?.let { UserEntityMapper.toUser(it) }
    }

    override fun findUserByEmail(email: String): User? {
        return userEntityRepository.findByIdOrNull(email)
            ?.let { UserEntityMapper.toUser(it) }
    }

    override fun findAuthorityByAuthName(authName: String): AuthorityEntity? {
        return authorityRepository.findAuthorityByAuthorityName(authName)
    }

    override fun saveUserAuthority(userAuthorityEntity: UserAuthorityEntity): UserAuthorityEntity {
        return userAuthorityRepository.save(userAuthorityEntity)
    }

    override fun saveAuthority(authorityEntity: AuthorityEntity): AuthorityEntity {
        return authorityRepository.save(authorityEntity)
    }

}