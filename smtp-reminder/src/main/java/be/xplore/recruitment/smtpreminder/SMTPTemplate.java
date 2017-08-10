package be.xplore.recruitment.smtpreminder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

public class SMTPTemplate {
    private static final Logger LOGGER = LoggerFactory.getLogger(SMTPTemplate.class);

    public SMTPTemplate() {
    }

    public interface SessionCallback {
        void exec(Session session) throws MessagingException;
    }

    public void withSession(SMTPConfig config, SessionCallback callback) {
        try {
            callback.exec(getSession(config));
        } catch (Exception ex) {
            LOGGER.warn("Error in SMTP session\n{}", ex.toString());
        }
    }

    private Session getSession(SMTPConfig config) {
        Session session;
        session = Session.getInstance(config.toJavaMailProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(config.getUser(), config.getPassword());
            }
        });
        return session;
    }

    public interface TransportCallback {
        void exec(Session session, Transport transport) throws MessagingException;
    }

    public void withTransport(SMTPConfig config, TransportCallback callback) {
        withSession(config, session -> {
            transportExec(config, callback, session);
        });
    }

    @SuppressWarnings("checkstyle:ExecutableStatementCount")
    private void transportExec(SMTPConfig config, TransportCallback callback, Session session) {
        Transport transport = null;
        try {
            transport = session.getTransport(config.getProtocol());
            executeTransportCallback(callback, session, transport);
        } catch (MessagingException ex) {
            LOGGER.warn("Error in SMTP session: {}", ex.toString());
        } finally {
            closeTransport(transport);
        }
    }

    private void executeTransportCallback(TransportCallback callback, Session session, Transport transport)
            throws MessagingException {
        transport.connect();
        callback.exec(session, transport);
    }

    private void closeTransport(Transport transport) {
        try {
            if (transport != null) {
                transport.close();
            }
        } catch (MessagingException ex) {
            LOGGER.warn("Error closing SMTP session: {}", ex.toString());
        }
    }
}
