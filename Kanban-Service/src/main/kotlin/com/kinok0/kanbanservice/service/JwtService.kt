//package com.kinok0.kanbanservice.service
//
//import io.jsonwebtoken.Jwts
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.security.core.userdetails.UserDetails
//import org.springframework.stereotype.Service
//import java.security.Key
//import java.util.*
//import java.util.function.Function
//
//@Service
//class JwtService {
//
//    @Value("\${token.secret.key}")
//    private val jwtSigningKey = ""
//
//    fun extractUsername(token: String): String = extractClaim(token, io.jsonwebtoken.Claims::getSubject)
//
//    fun <T> extractClaim(token: String, claimsResolver: Function<io.jsonwebtoken.Claims, T>): T =
//        claimsResolver.apply(extractAllClaims(token))
//
//    fun getSingInKey(): Key =
//        io.jsonwebtoken.security.Keys.hmacShaKeyFor(io.jsonwebtoken.io.Decoders.BASE64.decode(jwtSigningKey))
//
//    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
//        val username = extractUsername(token)
//        if (isTokenExpired(token)) throw Exception("Срок действия токена истек")
//        return username == userDetails.username && !isTokenExpired(token)
//    }
//
//    private fun isTokenExpired(token: String): Boolean = extractExpiration(token).before(Date())
//
//    private fun extractExpiration(token: String): Date = extractClaim(token, io.jsonwebtoken.Claims::getExpiration)
//
//    private fun extractAllClaims(token: String): io.jsonwebtoken.Claims =
//         Jwts.parserBuilder()
//            .setSigningKey(getSingInKey())
//            .build()
//            .parseClaimsJws(token)!!.body
//
//}