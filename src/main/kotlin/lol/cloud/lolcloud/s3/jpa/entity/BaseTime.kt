package lol.cloud.lolcloud.s3.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTime(
    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    val createdDate: Long,

    @Column(name = "modified_date")
    @LastModifiedDate
    var modifiedDate: Long
) {
}