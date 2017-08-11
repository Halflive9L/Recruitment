package be.xplore.recruitment.smtpreminder;

import be.xplore.recruitment.domain.applicant.Applicant;
import be.xplore.recruitment.domain.interview.ReminderSender;
import be.xplore.recruitment.domain.interviewer.Interviewer;

import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Named
public class SMTPReminderSender implements ReminderSender {
    private final SMTPConfig config;

    public SMTPReminderSender(SMTPConfig config) {
        this.config = config;
    }

    @Override
    public void remindApplicant(Applicant applicant, String subject, String message) {
        sendMail(applicant.getEmail(), message);
    }


    @Override
    public void remindInterviewer(Interviewer interviewer, String subject, String message) {
        sendMail(interviewer.getEmail(), message);
    }

    private void sendMail(String email, String message) {
        new SMTPTemplate().withTransport(config, (session, transport) -> {
            InternetAddress[] recipient = InternetAddress.parse(email);
            Message msg = getMessage(message, session);
            transport.sendMessage(msg, recipient);
        });
    }

    private Message getMessage(String message, Session session) throws MessagingException {
        Message msg = new MimeMessage(session);
        setMessageContent(message, msg);
        return msg;
    }

    private void setMessageContent(String message, Message msg) throws MessagingException {
        msg.setFrom(new InternetAddress(config.getEmail()));
        msg.setSubject("Interview reminder");
        msg.setText(message);
    }
}
