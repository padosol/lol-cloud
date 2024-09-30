package lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import lol.cloud.lolcloud.s3.user.domain.model.User

@Entity
@Table(name = "authority_users")
class UserAuthorityEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "email")
    val user: UserEntity,

    @ManyToOne
    @JoinColumn(name = "authorityName")
    val authorityEntity: AuthorityEntity,
)