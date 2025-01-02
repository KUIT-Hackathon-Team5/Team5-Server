package team5.team5server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Team5ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Team5ServerApplication.class, args);
    }

}
