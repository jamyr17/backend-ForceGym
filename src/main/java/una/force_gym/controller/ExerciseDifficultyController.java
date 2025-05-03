gpackage una.force_gym.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import una.force_gym.domain.ExerciseDifficulty;
import una.force_gym.exception.AppException;
import una.force_gym.service.ExerciseDifficultyService;
import una.force_gym.util.ApiResponse;

@RestController
@RequestMapping("/exercisedifficulty")
public class ExerciseDifficultyController {

    @Autowired
    private ExerciseDifficultyService exerciseDifficultyService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<ExerciseDifficulty>>> getExercisesDifficulties() {
        try {
            List<ExerciseDifficulty> roles = exerciseDifficultyService.getExercisesDifficulties();
            ApiResponse<List<ExerciseDifficulty>> response = new ApiResponse<>("Dificultades obtenidas correctamente.", roles);
            return new ResponseEntity<>(response, HttpStatus.OK); 

        } catch (RuntimeException e) {
            ApiResponse<List<ExerciseDifficulty>> response = new ApiResponse<>("Ocurrió un error al solicitar los datos de las categorias de las dificultades.", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
        }

    }

}
