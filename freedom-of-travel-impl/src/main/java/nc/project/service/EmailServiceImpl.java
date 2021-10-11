package nc.project.service;

import lombok.RequiredArgsConstructor;
import nc.project.jpa.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${freedomOfTravel.app.mail.verification.url}")
    private String verificationEmailURL;

    @Value("${freedomOfTravel.app.mail.from}")
    private String from;

    private final String SUBJECT = "Please verify your registration on Freedom of Travel";
    private String content = "Dear {{user-name}},<br>"
            + "Please click the link below to verify your registration:<br>"
            + "<h3><a href=\"{{URL}}\" target=\"_self\">VERIFY</a></h3>"
            + "Best regards,<br>"
            + "Freedom of Travel.";

    public void sendMail(User user, String siteURL) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(from);
        helper.setTo(user.getEmail());
        helper.setSubject(SUBJECT);

        String verificationLink = siteURL + verificationEmailURL.replace("{{verification-code}}", user.getVerificationCode())
                .replace("{{email}}", user.getEmail());

        content = content.replace("{{user-name}}", user.getUsername())
                .replace("{{URL}}", verificationLink);
        helper.setText(content, true);

        javaMailSender.send(message);
    }
}