package com.example.medicare_projectjee.security;

import com.example.medicare_projectjee.dao.entities.AppUser;
import com.example.medicare_projectjee.dao.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private AppUserRepository appUserRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //  charger un utilisateur depuis la Base de Donnees
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                AppUser appUser = appUserRepository.findByUsername(username);
                if (appUser == null) throw new UsernameNotFoundException("Utilisateur introuvable");

                // On transforme notre AppUser en User Spring Security
                return User.builder()
                        .username(appUser.getUsername())
                        .password(appUser.getPassword())
                        .roles(appUser.getRole())
                        .build();
            }
        };
    }

    // Configuration des accès
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler((request, response, authentication) -> {
                            // ... (Ta logique de redirection Admin/Medecin/Patient reste ici) ...
                            var roles = authentication.getAuthorities();
                            String redirectUrl = "/";
                            if (roles.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                                redirectUrl = "/admin/dashboard";
                            } else if (roles.stream().anyMatch(a -> a.getAuthority().equals("ROLE_MEDECIN"))) {
                                redirectUrl = "/medecin/dashboard";
                            } else if (roles.stream().anyMatch(a -> a.getAuthority().equals("ROLE_PATIENT"))) {
                                redirectUrl = "/patient/dashboard";
                            }
                            response.sendRedirect(redirectUrl);
                        })
                        .permitAll()
                )
                // ... (Logout reste ici) ...
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/") // REVOIE VERS L'ACCUEIL APRÈS DÉCONNEXION
                        .permitAll()
                )
                .authorizeHttpRequests(auth -> auth
                        // 👇 C'EST LA LIGNE IMPORTANTE 👇
                        // On autorise la racine "/", l'index, et les ressources CSS/JS/Images
                        .requestMatchers("/", "/index", "/webjars/**", "/css/**", "/js/**", "/images/**", "/h2-console/**").permitAll()

                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/medecin/**").hasRole("MEDECIN")
                        .requestMatchers("/patient/**").hasRole("PATIENT")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex.accessDeniedPage("/403"));

        return http.build();
    }
}