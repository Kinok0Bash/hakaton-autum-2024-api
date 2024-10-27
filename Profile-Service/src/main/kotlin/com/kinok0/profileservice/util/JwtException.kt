package com.kinok0.profileservice.util

class JwtException : Exception() {

    override val message: String
        get() = "Token is not valid!"

}