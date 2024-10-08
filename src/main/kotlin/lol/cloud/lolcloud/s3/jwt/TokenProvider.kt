package lol.cloud.lolcloud.s3.jwt

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SecurityException
import jakarta.servlet.http.HttpServletRequest
import lol.cloud.lolcloud.s3.jwt.filter.JwtFilter
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.util.*
import javax.crypto.SecretKey

import lol.cloud.lolcloud.s3.user.domain.model.User as UserInfo

@Component
@Slf4j
class TokenProvider(

    @Value("\${jwt.key}")
    val secret: String,

    @Value("\${jwt.access-token-expiration}")
    val tokenValidityInMilliseconds: Long,

) {

    val key: SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret))

    companion object {
        private const val AUTHORITIES_KEY = "auth"
    }

    /**
     * 토큰 생성
     * 유저이름, 만료시간, 권한 정보를 담은 토큰을 생성함
     */
    fun createToken(authentication: Authentication): String {

        // 권한 정보
        val authorities = authentication.authorities
            .map { it.authority }
            .joinToString(",")

        val now: Long = Date().time
        val expirationDate: Date = Date(now + tokenValidityInMilliseconds)

        return Jwts.builder()
            .subject(authentication.name)
            .expiration(expirationDate)
            .claim(AUTHORITIES_KEY, authorities)
            .signWith(key)
            .compact()
    }

    // 권한 추출
    fun getAuthentication(token: String): Authentication {

        val claims = Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .payload

        val authorities: Collection<GrantedAuthority> = claims[AUTHORITIES_KEY].toString().split(",")
            .map { SimpleGrantedAuthority(it) }
            .toList()

        val principal = User(claims.subject, "", authorities)

        return UsernamePasswordAuthenticationToken(principal, token, authorities)
    }

    // validation 체크
    fun validateToken(token: String?): Boolean {
        try {

            Jwts.parser().verifyWith(key).build().parseSignedClaims(token)

            return true
        } catch (e: SecurityException) {
            println("잘못된 JWT 서명입니다.")
        } catch (e: MalformedJwtException) {
            println("잘못된 JWT 서명입니다.")
        } catch (e: ExpiredJwtException) {
            println("만료된 JWT 토큰입니다.")
        } catch (e: UnsupportedJwtException) {
            println("지원되지 않는 JWT 토큰입니다.")
        } catch (e: IllegalArgumentException) {
            println("JWT 토큰이 잘못되었습니다.")
        }

        return false
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(JwtFilter.AUTHORIZATION_HEADER)
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else null
    }

    fun getUserInfo(request: HttpServletRequest): UserInfo? {
        val resolveToken: String = resolveToken(request)!!

        val authentication = getAuthentication(resolveToken)
        return null
    }

}