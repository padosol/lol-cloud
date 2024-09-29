package lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.repository

import lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.entity.AuthorityEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorityRepository : JpaRepository<AuthorityEntity, String>{

    fun findAuthorityByAuthorityName(authorityName: String): AuthorityEntity?
}