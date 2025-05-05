package una.force_gym.util;

import java.security.SecureRandom;
import java.util.Base64;

public class SecureRandomString {
    private static final SecureRandom random = new SecureRandom();
    private static final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();

    public static String generate(int length) {
        byte[] buffer = new byte[length];
        random.nextBytes(buffer);
        return encoder.encodeToString(buffer).substring(0, length);
    }
}