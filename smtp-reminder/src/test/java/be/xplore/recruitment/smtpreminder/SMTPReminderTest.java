package be.xplore.recruitment.smtpreminder;

import be.xplore.recruitment.domain.applicant.Applicant;
import be.xplore.recruitment.domain.interview.ReminderSender;
import de.saly.javamail.mock2.MockMailbox;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.mail.Message;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(MockitoJUnitRunner.class)
public class SMTPReminderTest {
    ReminderSender sender;

    @Before
    public void setup() {
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
        sender = new SMTPReminderSender(config);
    }

    @Test
    public void sendsApplicantReminder() throws Exception {
        Applicant applicant = Applicant.builder()
                .withFirstName("Maarten")
                .withLastName("Billiet")
                .withEmail("maarten@email.mock")
                .build();
        sender.remindApplicant(applicant, "test message");
        MockMailbox mb = MockMailbox.get("maarten@email.mock");
        Message[] messages = mb.getInbox().getMessages();
        assertThat(messages.length, is(1));
        Message message = messages[0];
        assertThat(message.getContent().toString().contains("test message"), is (true));
    }
}
