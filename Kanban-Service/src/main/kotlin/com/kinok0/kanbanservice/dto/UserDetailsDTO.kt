//package com.kinok0.kanbanservice.dto
//
//import com.kinok0.kanbanservice.entity.Role
//import org.springframework.security.core.authority.SimpleGrantedAuthority
//import org.springframework.security.core.userdetails.UserDetails
//
//data class UserDetailsDTO(
//    val login: String,
//    val authPassword: String,
//    val role: Role
//) : UserDetails {
//
//    override fun getAuthorities() = mutableListOf(SimpleGrantedAuthority(role.name))
//    override fun getPassword() = this.authPassword
//    override fun getUsername() = this.login
//
//}