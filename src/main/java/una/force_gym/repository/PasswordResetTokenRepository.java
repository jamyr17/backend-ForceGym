package una.force_gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import una.force_gym.domain.PasswordResetToken;
import una.force_gym.domain.User;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByRecoveryToken(String recoveryToken);
    Optional<PasswordResetToken> findByUser(User user);
}
