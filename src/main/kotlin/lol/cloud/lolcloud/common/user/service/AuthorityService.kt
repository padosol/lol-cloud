package lol.cloud.lolcloud.common.user.service

import lol.cloud.lolcloud.common.user.domain.Authority
import lol.cloud.lolcloud.common.user.repository.AuthorityRepository
import org.springframework.stereotype.Service

@Service
class AuthorityService(
    private val authorityRepository: AuthorityRepository,
) {

   fun createAuth(authName: String){
        authorityRepository.save(Authority(authName))
   }

}
