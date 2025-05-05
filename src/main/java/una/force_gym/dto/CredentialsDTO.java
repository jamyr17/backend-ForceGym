package una.force_gym.dto;

public class CredentialsDTO {
    
    private String username;
    private String recaptchaToken;
    private char[] password;

    public CredentialsDTO(){}

    public CredentialsDTO(String username, String recaptchaToken, char[] password) {
        this.username = username;
        this.recaptchaToken = recaptchaToken;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRecaptchaToken() {
        return recaptchaToken;
    }

    public void setRecaptchaToken(String recaptchaToken) {
        this.recaptchaToken = recaptchaToken;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

}
