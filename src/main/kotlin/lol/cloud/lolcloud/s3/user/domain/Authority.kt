package lol.cloud.lolcloud.s3.user.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Authority(

    @Id
    @Column(length = 50)
    val authorityName: String? = null

)