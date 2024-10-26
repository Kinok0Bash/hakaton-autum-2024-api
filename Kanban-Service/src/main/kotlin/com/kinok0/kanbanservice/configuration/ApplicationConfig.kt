//package com.kinok0.kanbanservice.configuration
//
//import com.kinok0.kanbanservice.repository.UserRepository
//import com.kinok0.kanbanservice.util.convertToUserDetailsDTO
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.authentication.AuthenticationManager
//import org.springframework.security.authentication.AuthenticationProvider
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//
//@Configuration
//class ApplicationConfig (
//    private val userRepository: UserRepository
//) {
//
//    @Bean
//    fun userDetailsService(): UserDetailsService? {
//        return UserDetailsService { email: String ->
//            userRepository.getUserEntityByLogin(email).convertToUserDetailsDTO()
//        }
//    }
//
//    @Bean
//    fun passwordEncoder() = BCryptPasswordEncoder()
//
//    @Bean
//    fun authenticationProvider(): AuthenticationProvider = DaoAuthenticationProvider().apply {
//        setUserDetailsService(userDetailsService())
//        setPasswordEncoder(passwordEncoder())
//    }
//
//    @Bean
//    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager =
//        authenticationConfiguration.authenticationManager
//
//}