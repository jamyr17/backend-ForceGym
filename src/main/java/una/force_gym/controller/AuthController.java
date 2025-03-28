package una.force_gym.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import una.force_gym.config.UserAuthenticationProvider;
import una.force_gym.dto.CredentialsDTO;
import una.force_gym.dto.LoginDTO;
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

}
