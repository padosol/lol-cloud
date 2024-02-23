package lol.cloud.lolcloud.s3.user.repository

import lol.cloud.lolcloud.s3.user.domain.UserAuthority
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserAuthorityRepository : JpaRepository<UserAuthority, Long>{

}