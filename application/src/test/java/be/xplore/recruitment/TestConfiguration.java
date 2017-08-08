package be.xplore.recruitment;

import be.xplore.recruitment.domain.interview.ReminderSender;
import be.xplore.recruitment.smtpreminder.SMTPConfig;
import be.xplore.recruitment.smtpreminder.SMTPReminderSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {
    @Bean
    public ReminderSender reminderSender() {
        SMTPConfig config = SMTPConfig.builder()
                .withEmail("sender@email.mock")
                .withHost("smtp.email.mock")
                .withPassword("password")
                .withPort(123)
                .withProtocol("mock_smtp")
                .withSsl(false)
                .withTimeout(3000)
                .withUser("user")
                .build();
        ReminderSender sender = new SMTPReminderSender(config);
        return sender;
    }
}
