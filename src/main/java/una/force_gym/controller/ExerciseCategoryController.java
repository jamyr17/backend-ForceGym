package una.force_gym.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import una.force_gym.domain.ExerciseCategory;
import una.force_gym.exception.AppException;
import una.force_gym.service.ExerciseCategoryService;
import una.force_gym.util.ApiResponse;

@RestController
@RequestMapping("/exercisecategory")
public class ExerciseCategoryController {

    @Autowired
    private ExerciseCategoryService exerciseCategoryService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<ExerciseCategory>>> getExercisesCategories() {
        try {
            List<ExerciseCategory> roles = exerciseCategoryService.getExercisesCategories();
            ApiResponse<List<ExerciseCategory>> response = new ApiResponse<>("Categorias obtenidas correctamente.", roles);
            return new ResponseEntity<>(response, HttpStatus.OK); 

        } catch (RuntimeException e) {
            ApiResponse<List<ExerciseCategory>> response = new ApiResponse<>("Ocurrió un error al solicitar los datos de las categorias de ejercicios.", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
        }

    }

}
