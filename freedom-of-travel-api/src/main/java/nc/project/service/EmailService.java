package nc.project.service;

import nc.project.jpa.entity.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailService {

    void sendMail(User user, String siteURL) throws UnsupportedEncodingException, MessagingException;

}