package lol.cloud.lolcloud.s3.bucket.domain.bucket_object

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import lol.cloud.lolcloud.s3.bucket.domain.bucket.Bucket
import lol.cloud.lolcloud.s3.bucket.dto.bucket_object.response.BucketObjectResponse
import java.time.LocalDateTime

@Entity
class BucketObject(
    val objectName: String,
    val objectType: String,
    val prefix: String,

    val objectSize: Long,

    val createDate: LocalDateTime,
    val modifyDate: LocalDateTime,

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
            objectType = objectType,
            id = id!!,
            modifyDate = modifyDate,
            objectSize = objectSize
        )
    }

}