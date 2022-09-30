//package com.team10.preproject.global.helper.email;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.util.Properties;
//
//@Configuration
//@PropertySource("classpath:/mail.properties")
//public class EmailConfiguration {
//
//    @Value("${spring.mail.transport.protocol}")
//    private String protocol;
//
//    @Value("${spring.mail.properties.mail.smtp.auth}")
//    private boolean auth;
//
//    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
//    private boolean starttls;
//
//    @Value("${spring.mail.debug}")
//    private boolean debug;
//
//    @Value("${spring.mail.host}")
//    private String host;
//
//    @Value("${spring.mail.port}")
//    private int port;
//
//    @Value("${spring.mail.username}")
//    private String username;
//
//    @Value("${spring.mail.password}")
//    private String password;
//
//    @Value("${spring.mail.default.encoding}")
//    private String encoding;
//
//    @Bean
//    public JavaMailSender getJavaMailSender() {
//
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        Properties properties = new Properties();
//        properties.put("mail.transport.protocol", protocol);
//        properties.put("mail.smtp.auth", auth);
//        properties.put("mail.smtp.starttls.enable", starttls);
//        properties.put("mail.smtp.debug", debug);
//
//        mailSender.setHost(host);
//        mailSender.setUsername(username);
//        mailSender.setPassword(password);
//        mailSender.setPort(port);
//        mailSender.setJavaMailProperties(properties);
//        mailSender.setDefaultEncoding(encoding);
//
//        return mailSender;
//    }
//
//    @Bean
//    public EmailSendable emailSendable() { return new MockEmailSendable(); }
//
//    @Primary
//    @Bean
//    public EmailSendable simpleEmailSendable() { return new SimpleEmailSendable(); }
//}
