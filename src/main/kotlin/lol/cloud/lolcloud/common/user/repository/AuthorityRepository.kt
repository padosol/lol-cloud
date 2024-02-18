package lol.cloud.lolcloud.common.user.repository

import lol.cloud.lolcloud.common.user.domain.Authority
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorityRepository : JpaRepository<Authority, String>{

    fun findAuthorityByAuthorityName(authorityName: String): Authority?
}