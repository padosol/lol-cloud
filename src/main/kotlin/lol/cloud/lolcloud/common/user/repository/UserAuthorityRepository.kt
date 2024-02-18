package lol.cloud.lolcloud.common.user.repository

import lol.cloud.lolcloud.common.user.domain.UserAuthority
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserAuthorityRepository : JpaRepository<UserAuthority, Long>{

}