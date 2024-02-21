package lol.cloud.lolcloud.common.bucket.domain.entity

import jakarta.persistence.*
import lol.cloud.lolcloud.common.bucket.dto.response.BucketResponse
import lol.cloud.lolcloud.common.user.domain.User
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(indexes = [Index(name = "idx_bucket_name", columnList = "bucket_name")])
class Bucket(

    @Column(length = 50)
    val bucketName: String,
    val createDate: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    var user: User? = null,

    var publicAccess: Boolean = true,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bucket_id")
    val id: Long? = null
) {

    fun toDto(): BucketResponse {
        return BucketResponse(
            bucketName = bucketName,
            publicAccess = publicAccess,
            createDate = createDate,
            id = id!!
        )
    }

}