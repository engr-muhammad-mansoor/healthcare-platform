package com.healthcare.uman.config;

import com.healthcare.uman.security.JwtAuthenticationEntryPoint;
import com.healthcare.uman.security.JwtAuthenticationFilter;
import com.healthcare.uman.service.DatabaseUserDetailPasswordService;
import com.healthcare.uman.service.DatabaseUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.healthcare.uman.security"})
public class SecurityConfig {

    private final DatabaseUserDetailPasswordService userDetailPasswordService;
    private final DatabaseUserDetailsService userDetailsService;
    private final JwtAuthenticationEntryPoint point;
    private final JwtAuthenticationFilter filter;
    private final OAuthAuthenticationSuccessHandler handler;
    private final AuthFailureHandler authFailureHandler;

    @Autowired
    public SecurityConfig(DatabaseUserDetailsService userDetailsService, DatabaseUserDetailPasswordService userDetailPasswordService, JwtAuthenticationEntryPoint point, JwtAuthenticationFilter filter, OAuthAuthenticationSuccessHandler handler, AuthFailureHandler authFailureHandler) {
        this.userDetailsService = userDetailsService;
        this.userDetailPasswordService = userDetailPasswordService;
        this.point = point;
        this.filter = filter;
        this.handler = handler;
        this.authFailureHandler = authFailureHandler;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setUserDetailsPasswordService(userDetailPasswordService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).authenticationProvider(authProvider()).build();
    }

    /*With Spring Security Form Login and Oauth 2.0*/

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http.csrf(AbstractHttpConfigurer::disable)
//                .cors(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/users/signup", "/api/v1/users/signup/pro", "/api/v1/users/signin").permitAll().requestMatchers("/api/v1/**", "/home/**").authenticated().anyRequest().permitAll())
//                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
//
//        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//        http.formLogin(formLogin -> formLogin
//                .loginPage("/login")
//                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/home/getuser", true)
//                .failureUrl("/login?error=true")
//                .usernameParameter("username")
//                .passwordParameter("password"));
//
//        http.logout(logout -> logout
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login?logout=true"));
//
//        http.oauth2Login(oauth2 -> oauth2
//                .loginPage("/login")
//                .defaultSuccessUrl("/home/getuser", true)
//                .failureUrl("/login?error=true"));
//        return http.build();
//    }

    /*
      With jwt and Oauth2.0 with Google
     */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth ->
//                        auth.requestMatchers("/home/**").authenticated()
//                                .anyRequest().permitAll())
//                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
//                .sessionManagement(session ->
//                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT filter here
//                .oauth2Login(oauth2 ->
//                        oauth2.loginPage("/login")
//                                .defaultSuccessUrl("/home/getuser", true)
//                                .failureUrl("/login?error=true"));
//        return http.build();
//    }


    /**
     * With form login, jwt and Oauth2.0 with Google
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/users/signup", "/api/v1/users/signup/pro", "/api/v1/users/signin").permitAll().requestMatchers("/api/v1/**", "/home/**").authenticated().anyRequest().permitAll()).exceptionHandling(ex -> ex.authenticationEntryPoint(point)).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)).addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class) // Add your JWT filter here
                .oauth2Login(oauth2 -> oauth2.loginPage("/login").defaultSuccessUrl("/home/getuser", true).successHandler(handler).failureUrl("/login?error=true").failureHandler(authFailureHandler)).formLogin(formLogin -> formLogin.loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/home/getuser", true).failureUrl("/login?error=true").usernameParameter("username").passwordParameter("password")).logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout=true"));
        return http.build();
    }
}