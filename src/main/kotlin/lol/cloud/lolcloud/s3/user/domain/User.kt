package lol.cloud.lolcloud.s3.user.domain

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import lol.cloud.lolcloud.s3.bucket.domain.bucket.Bucket
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class User(

    @Id
    val email: String,
    val password: String,

    var username: String,
    var createData: LocalDateTime?,

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    val authorities: MutableList<UserAuthority> = mutableListOf(),

) {

    @OneToMany(mappedBy = "user")
    val bucket: List<Bucket> = emptyList()
    fun updateName(username: String) {
        this.username = username
    }

    fun addAuthority(userAuthority: UserAuthority) {
        this.authorities.add(userAuthority)
    }

    fun createBucket(bucket: Bucket) {
        bucket.user = this
    }

}

