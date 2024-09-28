package lol.cloud.lolcloud.s3.user.repository

import lol.cloud.lolcloud.s3.user.domain.model.Authority
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorityRepository : JpaRepository<Authority, String>{

    fun findAuthorityByAuthorityName(authorityName: String): Authority?
}