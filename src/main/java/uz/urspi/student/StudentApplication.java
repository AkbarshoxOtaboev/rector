package uz.urspi.student;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.urspi.student.storage.StorageService;
import uz.urspi.student.user.Role;
import uz.urspi.student.user.User;
import uz.urspi.student.user.UserService;

@SpringBootApplication
@RequiredArgsConstructor
public class StudentApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }
    @Bean
    CommandLineRunner runner(UserService userService, PasswordEncoder passwordEncoder) {
        if(!userService.checkUser("admin")){
            return args -> {
                var admin = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("urspi2022"))
                        .fullName("Administrator")
                        .role(Role.ADMIN)
                        .status(1)
                        .build();
                userService.createUser(admin);
            };
        }else {
            return args -> {};
        }

    }
    @Bean
    CommandLineRunner init(StorageService storageService){
        return args -> storageService.init();
    }
}
