package lol.cloud.lolcloud.s3.bucket.domain

import lol.cloud.lolcloud.s3.bucket_object.domain.BucketObject
import lol.cloud.lolcloud.s3.user.domain.model.User
import java.time.LocalDateTime

class Bucket(
    val bucketName: String,
    val createDate: LocalDateTime = LocalDateTime.now(),
    var user: User? = null,
    var publicAccess: Boolean = true,
    var bucketObjects: MutableList<BucketObject> = mutableListOf(),
) {

    fun addUser(user: User) {
        this.user = user
    }

}