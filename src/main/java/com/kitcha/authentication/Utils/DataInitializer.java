package com.kitcha.authentication.Utils;

import com.kitcha.authentication.entity.UserEntity;
import com.kitcha.authentication.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            UserEntity user = new UserEntity();
            user.setNickname("testUser");
            user.setEmail("test@test.com");
            user.setPassword("1234");
            user.setRole("USER");
            user.setInterest("경제");
            UserEntity admin = new UserEntity();
            admin.setNickname("admin");
            admin.setEmail("admin@kitcha.shop");
            admin.setPassword("admin");
            admin.setRole("ADMIN");
            userRepository.save(user);
            userRepository.save(admin);
        }
    }
}
