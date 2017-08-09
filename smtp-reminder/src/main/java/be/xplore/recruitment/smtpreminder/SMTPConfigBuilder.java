package be.xplore.recruitment.smtpreminder;

public final class SMTPConfigBuilder {
    private String protocol;
    private String email;
    private String user;
    private String password;
    private String host;
    private int port;
    private int timeout;
    private boolean ssl;

    private SMTPConfigBuilder() {
    }

    public static SMTPConfigBuilder aSMTPConfig() {
        return new SMTPConfigBuilder();
    }

    public SMTPConfigBuilder withProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public SMTPConfigBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public SMTPConfigBuilder withUser(String user) {
        this.user = user;
        return this;
    }

    public SMTPConfigBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public SMTPConfigBuilder withHost(String host) {
        this.host = host;
        return this;
    }

    public SMTPConfigBuilder withPort(int port) {
        this.port = port;
        return this;
    }

    public SMTPConfigBuilder withTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public SMTPConfigBuilder withSsl(boolean ssl) {
        this.ssl = ssl;
        return this;
    }

    public SMTPConfig build() {
        SMTPConfig sMTPConfig = new SMTPConfig();
        sMTPConfig.setProtocol(protocol);
        sMTPConfig.setEmail(email);
        sMTPConfig.setUser(user);
        sMTPConfig.setPassword(password);
        sMTPConfig.setHost(host);
        sMTPConfig.setPort(port);
        sMTPConfig.setTimeout(timeout);
        sMTPConfig.setSsl(ssl);
        return sMTPConfig;
    }
}
