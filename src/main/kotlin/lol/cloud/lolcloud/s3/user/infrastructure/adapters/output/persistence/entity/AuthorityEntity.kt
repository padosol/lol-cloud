package lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "authority")
class AuthorityEntity(

    @Id
    @Column(length = 50)
    val authorityName: String? = null

)