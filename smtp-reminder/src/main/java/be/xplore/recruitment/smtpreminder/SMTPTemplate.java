package be.xplore.recruitment.smtpreminder;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import java.util.logging.Logger;

public class SMTPTemplate {
    private static final Logger LOGGER = Logger.getGlobal();

    public SMTPTemplate() {
    }

    public interface SessionCallback {
        void exec(Session session) throws MessagingException;
    }

    public void withSession(SMTPConfig config, SessionCallback callback) {
        Session session;
        try {
            session = Session.getInstance(config.toJavaMailProperties(), new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(config.getUser(), config.getPassword());
                }
            });
            callback.exec(session);
        } catch (Exception ex) {
            LOGGER.warning("Error in SMTP session");
            LOGGER.warning(ex.toString());
        }
    }

    public interface TransportCallback {
        void exec(Session session, Transport transport) throws MessagingException;
    }

    public void withTransport(SMTPConfig config, TransportCallback callback) {
        withSession(config, session -> {
            Transport transport = null;
            try {
                transport = session.getTransport(config.getProtocol());
                transport.connect();
                callback.exec(session, transport);
            } catch (MessagingException ex) {
                LOGGER.warning("Error in SMTP session");
                LOGGER.warning(ex.toString());
            } finally {
                closeTransport(transport);
            }
        });
    }

    private void closeTransport(Transport transport) {
        try {
            if (transport != null) {
                transport.close();
            }
        } catch (MessagingException ex) {
            LOGGER.warning("Error closing SMTP session");
            LOGGER.warning(ex.toString());
        }
    }
}
