package lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class UserEntity (

    @Id
    val email: String,
    val password: String,

    var username: String,
    var createData: LocalDateTime?,

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    val authorities: MutableList<UserAuthorityEntity> = mutableListOf(),
) {

}