package tr11.theater.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tr11.theater.model.User;
import tr11.theater.security.jwt.JwtTokenFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JwtTokenFilter jwtTokenFilter;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.POST, "/users/register", "/users/login")
                            .permitAll();
                    // /actors
                    auth.requestMatchers(HttpMethod.GET, "/actors", "/actors/{id}", "/actors/by-performance/{id}")
                                    .hasAnyRole(User.Role.ADMIN.name(), User.Role.DIRECTOR.name());

                    auth.requestMatchers(HttpMethod.POST, "/actors")
                                    .hasRole(User.Role.DIRECTOR.name());

                    auth.requestMatchers(HttpMethod.PUT,"/actors/{prevId}")
                            .hasRole(User.Role.DIRECTOR.name());

                    auth.requestMatchers(HttpMethod.DELETE, "/actors/{id}")
                                    .hasRole(User.Role.DIRECTOR.name());

                    // /contracts
                    auth.requestMatchers(HttpMethod.GET, "/contracts", "/contracts/{id}", "/contracts/actor/{actorId}/performance/{perId}")
                            .hasAnyRole(User.Role.ADMIN.name(), User.Role.DIRECTOR.name());

                    auth.requestMatchers(HttpMethod.POST, "/contracts")
                            .hasRole(User.Role.DIRECTOR.name());

                    auth.requestMatchers(HttpMethod.PUT,"/contracts/{prevId}")
                            .hasRole(User.Role.DIRECTOR.name());

                    auth.requestMatchers(HttpMethod.DELETE, "/contracts/{id}")
                            .hasRole(User.Role.DIRECTOR.name());

                    // /performances
                    auth.requestMatchers(HttpMethod.GET, "/performances", "/performances/{id}", "/performances/contract/loading")
                            .hasAnyRole(User.Role.ADMIN.name(), User.Role.DIRECTOR.name());

                    auth.requestMatchers(HttpMethod.POST, "/performances")
                            .hasRole(User.Role.DIRECTOR.name());

                    auth.requestMatchers(HttpMethod.PUT,"/performances/{prevId}", "/performances/actors/{prevId}")
                            .hasRole(User.Role.DIRECTOR.name());

                    auth.requestMatchers(HttpMethod.DELETE, "/performances/{id}", "/performances/{perId}/actor/{actorId}")
                            .hasRole(User.Role.DIRECTOR.name());

                    // /prizes
                    auth.requestMatchers(HttpMethod.GET, "/prizes", "/prizes/{id}")
                            .hasAnyRole(User.Role.ADMIN.name(), User.Role.DIRECTOR.name());

                    auth.requestMatchers(HttpMethod.POST, "/prizes", "/prizes/by-actor")
                            .hasRole(User.Role.DIRECTOR.name());

                    auth.requestMatchers(HttpMethod.PUT,"/prizes/{prevId}")
                            .hasRole(User.Role.DIRECTOR.name());

                    auth.requestMatchers(HttpMethod.DELETE, "/prizes/{id}")
                            .hasRole(User.Role.DIRECTOR.name());

                    auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                })
                .authenticationProvider(authenticationProvider())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}