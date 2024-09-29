package lol.cloud.lolcloud.s3.user.domain.model

import lol.cloud.lolcloud.s3.bucket.domain.bucket.Bucket
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime

class User(

    val email: String,
    var password: String,
    var username: String,
    var createData: LocalDateTime?,
    
    // 권한
    var authorityList: MutableList<Authority> = mutableListOf(),

    // 버킷
    var bucketList: MutableList<Bucket> = mutableListOf()

) {

    fun passwordEncode(passwordEncoder: PasswordEncoder) {
        password = passwordEncoder.encode(password)
    }

    fun addAuthority(authority: Authority) {
        authorityList.add(authority)
    }

    fun createBucket(bucket: Bucket) {
        bucketList.add(bucket)
    }

}

