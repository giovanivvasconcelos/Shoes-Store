package br.com.shoestore.config;

import br.com.shoestore.model.RoleEntity;
import br.com.shoestore.model.UserEntity;
import br.com.shoestore.repository.RoleRepository;
import br.com.shoestore.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner init(RoleRepository roleRepository,
                                  UserRepository userRepository,
                                  PasswordEncoder passwordEncoder) {
        return args -> {

            // Criando as roles se não existirem
            if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
                roleRepository.save(new RoleEntity(null, "ROLE_ADMIN"));
            }

            if (roleRepository.findByName("ROLE_USER").isEmpty()) {
                roleRepository.save(new RoleEntity(null, "ROLE_USER"));
            }

            // Criando usuário admin se não existir
            if (userRepository.findByUsername("admin").isEmpty()) {

                RoleEntity adminRole = roleRepository.findByName("ROLE_ADMIN").orElseThrow();
                RoleEntity userRole = roleRepository.findByName("ROLE_USER").orElseThrow();

                UserEntity admin = new UserEntity();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setRoles(Set.of(adminRole, userRole));

                userRepository.save(admin);
            }

            // Criando usuário comum se não existir
            if (userRepository.findByUsername("user").isEmpty()) {

                RoleEntity userRole = roleRepository.findByName("ROLE_USER").orElseThrow();

                UserEntity user = new UserEntity();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user"));
                user.setRoles(Set.of(userRole));

                userRepository.save(user);
            }

            System.out.println(">>> Dados iniciais carregados com sucesso!");
        };
    }
}