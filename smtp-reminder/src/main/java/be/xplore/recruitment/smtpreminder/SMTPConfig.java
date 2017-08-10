package be.xplore.recruitment.smtpreminder;

import java.util.Properties;

public class SMTPConfig {
    private String protocol;
    private String email;
    private String user;
    private String password;
    private String host;
    private int port;
    private int timeout;
    private boolean ssl;

    public SMTPConfig() {
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    @SuppressWarnings("checkstyle:ExecutableStatementCount")
    public Properties toJavaMailProperties() {
        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.put("mail.smtp.host", getHost());
        props.put("mail.smtp.port", getPort());
        if (ssl) {
            props.put("mail.smtp.socketFactory.port", port);
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }
        props.put("mail.smtp.ssl.trust", getHost());
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.connectiontimeout", getTimeout());
        props.put("mail.smtp.timeout", getTimeout());
        return props;
    }

    public static SMTPConfigBuilder builder() {
        return SMTPConfigBuilder.aSMTPConfig();
    }
}
