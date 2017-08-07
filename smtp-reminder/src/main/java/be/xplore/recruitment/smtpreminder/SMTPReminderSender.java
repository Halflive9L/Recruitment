package be.xplore.recruitment.smtpreminder;

import be.xplore.recruitment.domain.applicant.Applicant;
import be.xplore.recruitment.domain.interview.ReminderSender;
import be.xplore.recruitment.domain.interviewer.Interviewer;

import javax.inject.Named;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Named
public class SMTPReminderSender implements ReminderSender {
    private final SMTPConfig config;

    public SMTPReminderSender(SMTPConfig config) {
        this.config = config;
    }

    @Override
    public void remindApplicant(Applicant applicant, String message) {
        new SMTPTemplate().withTransport(config, (session, transport) -> {
            InternetAddress[] recipient = InternetAddress.parse(applicant.getEmail());
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(config.getEmail()));
            msg.setSubject("Interview reminder");
            msg.setText(message);
            transport.sendMessage(msg, recipient);
        });
    }

    @Override
    public void remindInterviewer(Interviewer interviewer, String message) {
        // TODO: implement
    }
}
