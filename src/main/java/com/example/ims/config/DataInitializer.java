package com.example.ims.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.ims.repository.UserRepository;
import com.example.ims.repository.RoleRepository;
import com.example.ims.entity.User;
import com.example.ims.entity.Role;

@Configuration
public class DataInitializer {
    
    @Bean
    CommandLineRunner init(
        UserRepository userRepository,
        RoleRepository roleRepository,
        PasswordEncoder encoder
    ){
        return args -> {
            Role adminRole = roleRepository
                            .findByName("ROLE_ADMIN")
                            .orElseGet(()-> {
                                Role role = new Role();
                                role.setName("ROLE_ADMIN");
                                return roleRepository.save(role);
                            });

            Role userRole = roleRepository
                            .findByName("ROLE_USER")
                            .orElseGet(()-> {
                                Role role = new Role();
                                role.setName("ROLE_USER");
                                return roleRepository.save(role);
                            });

            if(!userRepository.existsByUsername("admin")){
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(
                    encoder.encode("admin123")
                );
                admin.getRoles().add(adminRole);
                userRepository.save(admin);
            }
            if(!userRepository.existsByUsername("user")){
                User user = new User();
                user.setUsername("user");
                user.setPassword(
                    encoder.encode("user123")
                );
                user.getRoles().add(userRole);
                userRepository.save(user);
            }
        };
    }
}
