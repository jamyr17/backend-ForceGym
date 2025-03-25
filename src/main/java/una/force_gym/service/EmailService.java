package una.force_gym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService{

    @Value("${email.account.sender}")
    private String emailSender;

    @Autowired    
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String[] toUsers, String subject, String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(emailSender);
        mailMessage.setTo(toUsers);
        mailMessage.setSubject(subject); 
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }
    
}
