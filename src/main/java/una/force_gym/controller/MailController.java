package una.force_gym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import una.force_gym.dto.EmailDTO;
import una.force_gym.service.IEmailService;
import una.force_gym.util.ApiResponse;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private IEmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<ApiResponse<String>> sendEmail(@RequestBody EmailDTO email){

        emailService.sendEmail(email.getToUsers(), email.getSubject(), email.getMessage());

        ApiResponse<String> response = new ApiResponse<>("Correos enviados", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
