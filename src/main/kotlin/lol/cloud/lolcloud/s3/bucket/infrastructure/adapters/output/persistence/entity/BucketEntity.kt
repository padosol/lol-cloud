package lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.output.persistence.entity

import jakarta.persistence.*
import lol.cloud.lolcloud.s3.user.infrastructure.adapters.output.persistence.entity.UserEntity
import java.time.LocalDateTime

@Entity
@Table(indexes = [Index(name = "idx_bucket_name", columnList = "bucket_name")])
class BucketEntity(
    @Id
    @Column(length = 50)
    val bucketName: String,

    val createDate: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    var user: UserEntity? = null,

    var publicAccess: Boolean = true,
) {

}