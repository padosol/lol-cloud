package lol.cloud.lolcloud.s3.bucket.domain.bucket

import jakarta.persistence.*
import lol.cloud.lolcloud.s3.bucket.dto.bucket.response.BucketResponse
import lol.cloud.lolcloud.s3.user.domain.model.User
import java.time.LocalDateTime

@Entity
@Table(indexes = [Index(name = "idx_bucket_name", columnList = "bucket_name")])
class Bucket(

    @Id
    @Column(length = 50)
    val bucketName: String,

    val createDate: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    var user: User? = null,

    var publicAccess: Boolean = true,

    ) {

    fun toDto(): BucketResponse {
        return BucketResponse(
            bucketName = bucketName,
            publicAccess = publicAccess,
            createDate = createDate,
        )
    }

}