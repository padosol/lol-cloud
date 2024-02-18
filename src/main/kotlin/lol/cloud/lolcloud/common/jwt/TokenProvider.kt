package lol.cloud.lolcloud.common.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.Key
import java.util.Date

@Component
class TokenProvider(

    @Value("\${jwt.key}")
    val secret: String,

    @Value("\${jwt.access-token-expiration}")
    val tokenValidityInMilliseconds: Long,
) {

//    val key: Key
//
//    init{
//        val keyBytes: ByteArray = Decoders.BASE64.decode(secret)
//        key = Keys.hmacShaKeyFor(keyBytes)
//    }
    companion object {
        private const val AUTHORITIES_KEY = "auth"
    }

    // 토큰 생성
//    fun createToken(authentication: Authentication): String {
//
//        // 권한 정보
//        val authorities = authentication.authorities
//            .map { it.authority }
//            .joinToString { "," }
//
//
//        val now: Long = Date().time
//        val expirationDate: Date = Date(now + tokenValidityInMilliseconds)
//
//        return Jwts.builder()
//            .subject(authentication.name)
//            .expiration(expirationDate)
//            .claim(AUTHORITIES_KEY, authorities)
//            .signWith(key)
//            .compact()
//    }

    // validation 체크
}