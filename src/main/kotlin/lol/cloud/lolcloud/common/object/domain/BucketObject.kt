package lol.cloud.lolcloud.common.`object`.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class BucketObject(
    val key: String,
    val size: Long,
    val type: String,
    val objectUrl: String,
    val fileName: String,
    val modifyAt: LocalDateTime,
    val createAt: LocalDateTime,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {

}