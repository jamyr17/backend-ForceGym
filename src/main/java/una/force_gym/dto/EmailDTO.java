package una.force_gym.dto;

public class EmailDTO {

    private String[] toUsers;
    private String subject;
    private String message;

    public EmailDTO() {}

    public EmailDTO(String[] toUsers, String subject, String message) {
        this.toUsers = toUsers;
        this.subject = subject;
        this.message = message;
    }

    public String[] getToUsers() {
        return toUsers;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

}
