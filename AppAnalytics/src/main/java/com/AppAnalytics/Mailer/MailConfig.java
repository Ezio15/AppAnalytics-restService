package com.AppAnalytics.Mailer;

import java.util.Properties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.AppAnalytics.utils.PropertiesReader;

public class MailConfig {
    private static final com.AppAnalytics.utils.PropertiesReader props = PropertiesReader.getInstance();

    //Sendmail properties
    public JavaMailSender getMailProperties(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        
        mailSender.setHost( props.getMessage("mail.host"));
        mailSender.setPort( Integer.parseInt(props.getMessage("mail.port")));
        mailSender.setUsername(props.getMessage("mail.username"));
        mailSender.setPassword( props.getMessage("mail.password"));
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable",  props.getMessage("mail.smtp.starttls.enable"));
        javaMailProperties.put("mail.smtp.auth",  props.getMessage("mail.smtp.auth"));
        javaMailProperties.put("mail.transport.protocol",  props.getMessage("mail.transport.protocol"));
        javaMailProperties.put("mail.debug", props.getMessage("mail.debug"));
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }
    
   
}
