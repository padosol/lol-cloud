package lol.cloud.lolcloud.common.user.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import java.time.LocalDateTime

@Entity
class User(

    @Id
    private val email: String,
    private val password: String,

    private val userName: String,
    private val createData: LocalDateTime,

    @OneToMany
    private var authorities: List<Authority> = emptyList()
) {
}

