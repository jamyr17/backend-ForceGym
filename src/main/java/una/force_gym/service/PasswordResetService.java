package una.force_gym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import una.force_gym.domain.PasswordResetToken;
import una.force_gym.domain.User;
import una.force_gym.repository.PasswordResetTokenRepository;
import una.force_gym.repository.UserRepository;

import java.util.Optional;

@Service
public class PasswordResetService {
    
    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    
    @Autowired
    private UserRepository userRepository;  
    
    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public void createPasswordResetTokenForUser(User user) {    
        // Validate how many recoveries this user has done the last week

        // Generate new token
        String tokenValue = tokenService.generateToken(user.getPerson().getEmail());
        PasswordResetToken token = new PasswordResetToken(user, tokenValue, Long.valueOf(60));
        tokenRepository.save(token);

        // Email to send
        String[] emails = new String[1];
        emails[0] = user.getPerson().getEmail();

        // Client URL for recovery and email content
        String resetUrl = "http://localhost:5173/resetPassword?token=" + tokenValue;
    
        // Contenido HTML para mejor formato y redirección
        String content = "<!DOCTYPE html>"
            + "<html>"
            + "<head>"
            + "<style>"
            + "  body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }"
            + "  .button {"
            + "    display: inline-block;"
            + "    padding: 10px 20px;"
            + "    background-color: #4CAF50;"
            + "    color: white !important;"
            + "    text-decoration: none;"
            + "    border-radius: 5px;"
            + "    margin: 15px 0;"
            + "  }"
            + "  .footer {"
            + "    margin-top: 20px;"
            + "    font-size: 0.9em;"
            + "    color: #777;"
            + "  }"
            + "</style>"
            + "</head>"
            + "<body>"
            + "<p>Hola <strong>" + user.getPerson().getName() + ' ' + user.getPerson().getFirstLastName() + "</strong>,</p>"
            + "<p>Hemos recibido una solicitud para restablecer tu contraseña en <strong>Force Gym</strong>.</p>"
            + "<p>Por favor haz clic en el siguiente botón para continuar:</p>"
            + "<p><a href=\"" + resetUrl + "\" class=\"button\" target=\"_blank\">Restablecer contraseña</a></p>"
            + "<p>Si el botón no funciona, copia y pega esta URL en tu navegador:</p>"
            + "<p><code>" + resetUrl + "</code></p>"
            + "<div class=\"footer\">"
            + "<p>Este enlace expirará en 24 horas.</p>"
            + "<p>Si no solicitaste este cambio, por favor ignora este mensaje.</p>"
            + "<p>Atentamente,<br>El equipo de <strong>Force Gym</strong></p>"
            + "</div>"
            + "</body>"
            + "</html>";
        
        // Send email with token
        emailService.sendEmail(emails, "Recuperación de contraseña - Force Gym", content, true);
    }
    
    @Transactional
    public boolean validatePasswordResetToken(String tokenValue) {
        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByRecoveryToken(tokenValue);
        
        if (tokenOpt.isEmpty()) {
            return false;
        }
        
        PasswordResetToken token = tokenOpt.get();
        
        if (token.isExpired()) {
            return false;
        }
        
        return tokenService.validateToken(tokenValue);
    }
    
    @Transactional
    public void resetPassword(String tokenValue, String newPassword) {
        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByRecoveryToken(tokenValue);
        
        if (tokenOpt.isPresent()) {
            PasswordResetToken token = tokenOpt.get();
            
            if (!token.isExpired() && tokenService.validateToken(tokenValue)) {
                User user = token.getUser();
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);
                
                tokenRepository.save(token);
            } else {
                throw new IllegalStateException("El token ya ha expirado, intente de nuevo");
            }
        } else {
            throw new IllegalArgumentException("Token inválido");
        }
    }
}