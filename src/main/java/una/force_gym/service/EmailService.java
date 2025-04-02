package una.force_gym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService implements IEmailService{

    @Value("${email.account.sender}")
    private String emailSender;

    @Autowired    
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String[] toUsers, String subject, String message, Boolean isHtml){
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            
            helper.setFrom(emailSender);
            helper.setTo(toUsers);
            helper.setSubject(subject);
            helper.setText(message, isHtml);
            
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo electrónico", e);
        }
    }
    
}
