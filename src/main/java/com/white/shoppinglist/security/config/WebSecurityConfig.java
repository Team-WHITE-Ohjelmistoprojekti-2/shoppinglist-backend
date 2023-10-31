package com.white.shoppinglist.security.config;

import com.white.shoppinglist.appuser.AppUserService;
import com.white.shoppinglist.security.auth.AuthEntryPoint;
import com.white.shoppinglist.security.auth.AuthenticationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final AuthenticationFilter authenticationFilter;
    private final AuthEntryPoint exceptionHandler;
    private final AppUserService appUserservice;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig(AuthenticationFilter authenticationFilter, AppUserService appUserService,
            BCryptPasswordEncoder bCryptPasswordEncoder, AuthEntryPoint exceptionHandler) {
        this.authenticationFilter = authenticationFilter;
        this.exceptionHandler = exceptionHandler;
        this.appUserservice = appUserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Permit authentication endpoints (login and signup)
        RequestMatcher matcherAuth = new AntPathRequestMatcher("/api/auth/**", HttpMethod.POST.toString());
        // Endpoints not secured for now.
        RequestMatcher matcherApi = new AntPathRequestMatcher("/api/**");
        RequestMatcher matcherSwagger1 = new AntPathRequestMatcher("/swagger-ui/**");
        RequestMatcher matcherSwagger2 = new AntPathRequestMatcher("/swagger-ui.html");
        RequestMatcher matcherApiDocsConfig = new AntPathRequestMatcher("/api-docs/swagger-config");
        RequestMatcher matcherApiDocs = new AntPathRequestMatcher("/api-docs");

        http.csrf((csrf) -> csrf.disable()).cors(withDefaults())
            .sessionManagement(
                (sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                .requestMatchers(matcherAuth, matcherApi, matcherSwagger1, matcherSwagger2, matcherApiDocs, matcherApiDocsConfig)
                .permitAll().anyRequest().authenticated())
            .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling((exceptionHandling) -> exceptionHandling.authenticationEntryPoint(exceptionHandler));

		return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserservice);
        
        return provider;
    }
}
