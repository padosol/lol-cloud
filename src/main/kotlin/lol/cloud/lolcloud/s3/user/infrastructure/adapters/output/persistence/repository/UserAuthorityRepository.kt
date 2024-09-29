package lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.repository

import lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.entity.UserAuthorityEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserAuthorityRepository : JpaRepository<UserAuthorityEntity, Long>{

}