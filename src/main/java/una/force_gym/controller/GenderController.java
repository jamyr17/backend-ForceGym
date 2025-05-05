package una.force_gym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import una.force_gym.domain.Gender;
import una.force_gym.service.GenderService;
import una.force_gym.util.ApiResponse;

@RestController
@RequestMapping("/gender")
public class GenderController {
    
    @Autowired
    private GenderService genderService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<Gender>>> getGenders() {
        try {
            List<Gender> genders = genderService.getGenders();
            ApiResponse<List<Gender>> response = new ApiResponse<>("Géneros obtenidos correctamente.", genders);
            return new ResponseEntity<>(response, HttpStatus.OK); 

        } catch (RuntimeException e) {
            ApiResponse<List<Gender>> response = new ApiResponse<>("Ocurrió un error al solicitar los datos de los géneros.", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
        }

    }

}
