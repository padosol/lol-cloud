package lol.cloud.lolcloud.s3.user.service

import lol.cloud.lolcloud.s3.user.domain.model.Authority
import lol.cloud.lolcloud.s3.user.repository.AuthorityRepository
import org.springframework.stereotype.Service

@Service
class AuthorityService(
    private val authorityRepository: AuthorityRepository,
) {

   fun createAuth(authName: String){
        authorityRepository.save(Authority(authName))
   }

}
