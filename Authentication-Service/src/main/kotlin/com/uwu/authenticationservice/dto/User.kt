package com.uwu.authenticationservice.dto

import com.uwu.authenticationservice.entity.Role
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class User (
    val email: String,
    val authPassword: String,
    val role: Role
) : UserDetails {

    override fun getAuthorities() = mutableListOf(SimpleGrantedAuthority(role.name))
    override fun getPassword() = this.authPassword
    override fun getUsername() = this.email

}