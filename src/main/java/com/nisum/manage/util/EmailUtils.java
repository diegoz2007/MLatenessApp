package com.nisum.manage.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by dpinto on 29-04-2016.
 */
public class EmailUtils {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String from, String[] to, String title, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress(from));
            helper.setTo(to);
            helper.setText(body, true);
            helper.setSubject(title);
            mailSender.send(message);

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
    }


}
