package una.force_gym.service;

public interface IEmailService {

    void sendEmail(String[] toUsers, String subject, String message);
    
}
