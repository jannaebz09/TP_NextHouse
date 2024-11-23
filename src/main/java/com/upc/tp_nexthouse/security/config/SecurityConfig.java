package com.upc.tp_nexthouse.security.config;
import com.upc.tp_nexthouse.security.filters.JwtRequestFilter;
import com.upc.tp_nexthouse.security.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    //Inyectando JWT Filter por constructor
    public SecurityConfig(CustomUserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    //se define como un bean para que pueda ser utilizado en otros lugares, como en el controlador de autenticación
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    // Bean para codificar las contraseñas para ser usando en cualquier parte de la app
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Definir el SecurityFilterChain como un bean, ya no necesitamos heredar, configuramos toda la seg.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Activa CORS
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/authenticate").permitAll() // Permitir acceso a la ruta de autenticación
                        .requestMatchers("/api/Rol/**").permitAll() // Permitir acceso a todos los endpoints de RolController
                        .requestMatchers("/api/Usuario/**").permitAll() // Permitir acceso a todos los endpoints de UsuarioController
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Permitir todas las solicitudes OPTIONS
                        .anyRequest().authenticated() // Requiere autenticación para cualquier otra solicitud
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        // Añadir el filtro JWT antes del filtro de autenticación
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public GrantedAuthoritiesMapper grantedAuthoritiesMapper() {
        return new NullAuthoritiesMapper();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Permitir todos los orígenes
        configuration.setAllowedOriginPatterns(Collections.singletonList("*"));

        // Métodos permitidos
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Encabezados permitidos
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

        // Exponer encabezados específicos al cliente
        configuration.addExposedHeader("Authorization");

        // Permitir credenciales (cookies, autenticación, etc.)
        configuration.setAllowCredentials(true);

        // Aplicar la configuración a todas las rutas
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
