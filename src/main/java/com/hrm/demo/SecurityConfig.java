package com.hrm.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .invalidSessionUrl("/my-login?timeout")
                .maximumSessions(1).maxSessionsPreventsLogin(true).and() //requires @Bean HttpSessionEventPublisher
                //.sessionFixation().none() //NOT RECOMMENDED, only if needed
                .and()

                //Cross Site Request Forgery
                .csrf()
                .ignoringAntMatchers("/h2-console/**")
                .and()

                //Cross Origin Resource Sharing
                .cors()
                .and()

                //Clickjacking mitigation
                .headers()
                .frameOptions()
                .sameOrigin()
                .and()

                //Authentication/Authorization filtering for URL/HTTP methods
                .authorizeRequests()
                .mvcMatchers("/", "/css/**", "/js/**", "/images/**", "/h2-console/**").permitAll()
                .mvcMatchers("/books/create").hasAuthority("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/api/books").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()

                //Username/Password login via Basic auth
                .httpBasic()
                .and()

                //Username/Password login via Form-based auth
                .formLogin()
                .loginPage("/my-login")
                .defaultSuccessUrl("/")
                .failureUrl("/my-login?error")
                .permitAll()
                .and()

                //Logout configuration
                .logout()
                //.logoutUrl("/my-logout") - DEFAULT POST, best practice to use
                .logoutRequestMatcher(new AntPathRequestMatcher("/my-logout")) //use if not POST (bad practice)
                .clearAuthentication(true) //.invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/my-login?logout")
                .permitAll()
                .and()

                //Build Security Filter Chain object
                .build();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

/*
    //specific CORS configuration
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedOrigins(List.of("https://localhost:8080"));
        cors.setAllowedMethods(List.of("GET", "POST"));
        cors.setAllowedMethods(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cors);
        return source;
    }
*/

/*
    //in memory user details service (for non-production purposes only)
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user") //.password("{bcrypt}$2a$10$Tw3Qt8y.zyD1KJpcROW8BecFKRj21IDCgg51bdROgXfIbg54Yt06e")
                .authorities("USER")
                .build();
        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin") //.password("{bcrypt}$2a$10$is9lYstOx6UlUOBxUpTeh.ObBZXnC6qtwaLmFHneGBcHFvWciDrzi")
                .authorities("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }
*/

/*
    //customization for non-conventional database mapping
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        //expected output: ONE ROW OF: [username, password, enabled]
        manager.setUsersByUsernameQuery("SELECT email, password, true as enabled FROM customers WHERE email = ?");
        //expected output: SET OF ROWS OF: [username, authority]
        manager.setAuthoritiesByUsernameQuery("SELECT email, 'USER' FROM customers WHERE login = ?");
        return manager;
    }
*/

/*
    //disable password encode (for non-production use only!)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
*/

}
