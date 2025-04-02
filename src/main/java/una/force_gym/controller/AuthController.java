package una.force_gym.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import una.force_gym.config.UserAuthenticationProvider;
import una.force_gym.domain.User;
import una.force_gym.dto.CredentialsDTO;
import una.force_gym.dto.LoginDTO;
import una.force_gym.service.PasswordResetService;
import una.force_gym.service.ReCaptchaService;
import una.force_gym.service.UserService;
import una.force_gym.util.ApiResponse;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    @Autowired
    private ReCaptchaService reCaptchaService;

    @Autowired
    private PasswordResetService passwordResetService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid CredentialsDTO credentialsDTO) {
        if (!reCaptchaService.verifyRecaptcha(credentialsDTO.getRecaptchaToken())) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "message", "Recaptcha no válido"
            ));
        }

        LoginDTO loginDTO = userService.login(credentialsDTO);
        loginDTO.setToken(userAuthenticationProvider.createToken(loginDTO.getUsername()));

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("loggedUser", loginDTO);
        responseData.put("message", "Login exitoso");

        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
        apiResponse.setData(responseData);
        apiResponse.setMessage("Login exitoso"); 

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/recoveryPassword")
    public ResponseEntity<ApiResponse<String>> forgotPassword(@RequestParam String email) {
        User user = userService.findByEmail(email);

        if(user.getIdUser()==null){
            ApiResponse<String> responseNotFound = new ApiResponse<>("Error", null);
            return new ResponseEntity<>(responseNotFound, HttpStatus.BAD_REQUEST); 
        }
        
        passwordResetService.createPasswordResetTokenForUser(user);
        
        ApiResponse<String> response = new ApiResponse<>("Se ha enviado un link a su correo electrónico", null);
        return new ResponseEntity<>(response, HttpStatus.OK); 
    }

    @GetMapping("/validateResetToken")
    public ResponseEntity<?> validateResetToken(@RequestParam String token) {
        boolean isValid = passwordResetService.validatePasswordResetToken(token);
        
        if (isValid) {
            return ResponseEntity.ok("Token is valid");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired token");
        }
    }
    
    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(
            @RequestParam String token,
            @RequestParam String newPassword) {
        
        passwordResetService.resetPassword(token, newPassword);
        return ResponseEntity.ok("Password reset successfully");
    }

}
