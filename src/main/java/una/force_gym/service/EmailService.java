package una.force_gym.service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService implements IEmailService{

    @Value("${email.account.sender}")
    private String emailSender;

    @Autowired    
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String[] toUsers, String subject, String message) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");

            // 1. Cargar la plantilla HTML
            String htmlTemplate;
            try (InputStream templateStream = getClass().getResourceAsStream("/templates/email.html")) {
                if (templateStream == null) {
                    throw new FileNotFoundException("No se encontró la plantilla email.html");
                }
                htmlTemplate = new String(templateStream.readAllBytes(), StandardCharsets.UTF_8);
            }

            // 2. Reemplazar placeholders
            String htmlContent = htmlTemplate
                .replace("${subject}", subject)
                .replace("${message}", message);

            // 3. Configurar el email
            helper.setFrom(emailSender);
            helper.setTo(toUsers);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); 
            
            // 4. Adjuntar el logo desde
            ClassPathResource logoResource = new ClassPathResource("/images/Logo.webp");
            if (!logoResource.exists()) {
                throw new FileNotFoundException("No se encontró el logo en /images/Logo.webp");
            }
            helper.addInline("Logo.webp", logoResource, "image/webp");
            
            // 5. Enviar el email
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
