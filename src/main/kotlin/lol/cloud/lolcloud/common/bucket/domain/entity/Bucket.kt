package lol.cloud.lolcloud.common.bucket.domain.entity

import jakarta.persistence.*
import lol.cloud.lolcloud.common.user.domain.User
import java.time.LocalDateTime

@Entity
class Bucket(

    @Column(length = 50)
    val bucketName: String,
    val createDate: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    val user: User,

    @Id
    val id: Long? = null
) {
}