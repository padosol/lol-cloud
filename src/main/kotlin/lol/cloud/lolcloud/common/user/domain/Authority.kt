package lol.cloud.lolcloud.common.user.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Authority(

    @Id
    @Column(length = 50)
    private val authorityName: String
)