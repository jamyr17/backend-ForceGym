package una.force_gym.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbUserPasswordRecovery")
public class PasswordResetToken {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPasswordRecovery")
    private Long idPasswordRecovery;
    
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "idUser")
    private User user;
    
    @Column(name = "recoveryToken")
    private String recoveryToken;
    
    @Column(name = "timeCreated")
    private LocalDateTime timeCreated;
    
    @Column(name = "tokenExpiry")
    private Long tokenExpiry;

    @Column(name = "expiryDate")
    private LocalDateTime expiryDate;

    public PasswordResetToken() {}
    
    public PasswordResetToken(User user, String recoveryToken, Long tokenExpiry) {
        this.user = user;
        this.recoveryToken = recoveryToken;
        this.timeCreated = LocalDateTime.now();
        this.tokenExpiry = tokenExpiry;
        this.expiryDate = calculateExpiryDate();
    }

    private LocalDateTime calculateExpiryDate() {
        return this.timeCreated.plusMinutes(tokenExpiry);
    }
    
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryDate);
    }

    public Long getIdPasswordRecovery() {
        return idPasswordRecovery;
    }

    public void setIdPasswordRecovery(Long idPasswordRecovery) {
        this.idPasswordRecovery = idPasswordRecovery;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRecoveryToken() {
        return recoveryToken;
    }

    public void setRecoveryToken(String recoveryToken) {
        this.recoveryToken = recoveryToken;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Long getTokenExpiry() {
        return tokenExpiry;
    }

    public void setTokenExpiry(Long tokenExpiry) {
        this.tokenExpiry = tokenExpiry;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

}
