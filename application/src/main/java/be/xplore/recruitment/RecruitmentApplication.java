package be.xplore.recruitment;

import be.xplore.recruitment.smtpreminder.SMTPConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configuration
@EnableScheduling
public class RecruitmentApplication {
    @Bean
    public SMTPConfig config() {
        return SMTPConfig.builder()
                .withEmail("sender@email.mock")
                .withHost("smtp.email.mock")
                .withPassword("password")
                .withPort(123)
                .withProtocol("smtp")
                .withSsl(false)
                .withTimeout(3000)
                .withUser("user")
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(RecruitmentApplication.class, args);
    }
}
