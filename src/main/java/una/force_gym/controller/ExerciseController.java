package una.force_gym.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import una.force_gym.domain.Exercise;
import una.force_gym.dto.ExerciseDTO;
import una.force_gym.exception.AppException;
import una.force_gym.service.ExerciseService;
import una.force_gym.util.ApiResponse;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping("/listAll")
    public ResponseEntity<ApiResponse<List<Exercise>>> getExercises() {
        try {
            List<Exercise> exercises = exerciseService.getAllExercises();
            ApiResponse<List<Exercise>> response = new ApiResponse<>("Ejercicios obtenidos correctamente.", exercises);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            ApiResponse<List<Exercise>> response = new ApiResponse<>("Ocurrió un error al solicitar los ejercicios.", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getExercises(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "1") int searchType,
            @RequestParam(defaultValue = "") String searchTerm,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "") String directionOrderBy,
            @RequestParam(defaultValue = "") String filterByStatus
    ) {
        try {
            Map<String, Object> responseData = exerciseService.getExercises(page, size, searchType, searchTerm, orderBy, directionOrderBy, filterByStatus);
            ApiResponse<Map<String, Object>> response = new ApiResponse<>("Ejercicios obtenidos correctamente.", responseData);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            ApiResponse<Map<String, Object>> response = new ApiResponse<>("Ocurrió un error al solicitar los ejercicios.", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addExercise(@RequestBody ExerciseDTO exerciseDTO) {
        int result = exerciseService.addExercise(
                exerciseDTO.getName(),
                exerciseDTO.getDescription(),
                exerciseDTO.getSets(),
                exerciseDTO.getRepetitions(),
                exerciseDTO.getParamLoggedIdUser()
        );

        switch (result) {
            case 1 -> {
                ApiResponse<String> response = new ApiResponse<>("Ejercicio agregado correctamente.", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            case 0 ->
                throw new AppException("Ocurrió un error al agregar el ejercicio.", HttpStatus.INTERNAL_SERVER_ERROR);
            case -1 ->
                throw new AppException("No se pudo agregar el ejercicio debido a que el usuario asociado no está registrado.", HttpStatus.INTERNAL_SERVER_ERROR);
            default ->
                throw new AppException("Ejercicio no agregado debido a problemas en la consulta.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
