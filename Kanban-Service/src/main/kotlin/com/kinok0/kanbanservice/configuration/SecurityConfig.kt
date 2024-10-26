//package com.kinok0.kanbanservice.configuration
//
//import com.kinok0.kanbanservice.entity.Role
//import com.kinok0.kanbanservice.service.JwtService
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.http.HttpMethod
//import org.springframework.security.config.Customizer
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
//import org.springframework.security.config.http.SessionCreationPolicy
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.security.web.SecurityFilterChain
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
//
//@Configuration
//@EnableWebSecurity
//class SecurityConfig(
//    private val userDetailsService: UserDetailsService,
//    private val jwtService: JwtService
//) {
//
//    @Bean
//    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
//        http.csrf { obj: AbstractHttpConfigurer<*, *> -> obj.disable() }
//            .addFilterBefore(JwtAuthenticationFilter(userDetailsService, jwtService), UsernamePasswordAuthenticationFilter::class.java)
//            .authorizeHttpRequests { authorizationManagerRequestMatcherRegistry ->
//                authorizationManagerRequestMatcherRegistry
//                    .requestMatchers(HttpMethod.GET,
//                        "/api/kanban",
//                        "/api/kanban/**"
//                    )
//                    .authenticated()
//                    .requestMatchers(
//                        "/api/kanban",
//                        "/api/kanban/**"
//                    )
//                    .hasAnyRole(Role.USER.toString(), Role.ADMIN.toString())
//                    .anyRequest()
//                    .authenticated()
//            }
//            .httpBasic(Customizer.withDefaults())
//            .sessionManagement { httpSecuritySessionManagementConfigurer ->
//                httpSecuritySessionManagementConfigurer.sessionCreationPolicy(
//                    SessionCreationPolicy.STATELESS
//                )
//            }
//        return http.build()
//    }
//
//}