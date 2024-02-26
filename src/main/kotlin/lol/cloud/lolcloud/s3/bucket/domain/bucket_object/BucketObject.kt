package lol.cloud.lolcloud.s3.bucket.domain.bucket_object

import jakarta.persistence.*
import lol.cloud.lolcloud.s3.bucket.domain.bucket.Bucket
import lol.cloud.lolcloud.s3.bucket.dto.bucket_object.response.BucketObjectResponse
import java.time.LocalDateTime

@Entity
class BucketObject(
    val objectName: String,

    @Enumerated(EnumType.STRING)
    val objectType: ObjectType,
    var prefix: String,

    var objectSize: Long? = null,

    val createDate: LocalDateTime,
    val modifyDate: LocalDateTime? = null,

    @ManyToOne
    @JoinColumn(name = "bucket_id")
    val bucket: Bucket,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bucket_object_id")
    val id: Long? = null,
) {

    fun toDto(): BucketObjectResponse {
        return BucketObjectResponse(
            prefix = prefix,
            objectName = objectName,
            objectType = objectType.name,
            id = id!!,
            modifyDate = modifyDate,
            objectSize = objectSize
        )
    }

}