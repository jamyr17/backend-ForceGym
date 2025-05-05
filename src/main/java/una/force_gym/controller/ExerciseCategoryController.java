package una.force_gym.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import una.force_gym.domain.ExerciseCategory;
import una.force_gym.dto.ExerciseCategoryDTO;
import una.force_gym.dto.ParamLoggedIdUserDTO;
import una.force_gym.exception.AppException;
import una.force_gym.service.ExerciseCategoryService;
import una.force_gym.util.ApiResponse;

@RestController
@RequestMapping("/exerciseCategory")
public class ExerciseCategoryController {

    @Autowired
    private ExerciseCategoryService exerciseCategoryService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getExercisesCategoriesList( 
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String filterByStatus){ 
        try {
            Map<String, Object> responseData = exerciseCategoryService.getExercisesCategoriesList(page, size, filterByStatus);
            ApiResponse<Map<String, Object>> response = new ApiResponse<>("Categorías de ejercicios obtenidas correctamente.", responseData);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (RuntimeException e) {
            ApiResponse<Map<String, Object>> response = new ApiResponse<>("Ocurrió un error al solicitar los datos de las categorías de ejercicios.", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
        }

    }

    @GetMapping("/listAll")
    public ResponseEntity<ApiResponse<List<ExerciseCategory>>> getExercisesCategories() {
        try {
            List<ExerciseCategory> exerciseCategories = exerciseCategoryService.getExercisesCategories();
            ApiResponse<List<ExerciseCategory>> response = new ApiResponse<>("Categorías de ejercicios obtenidas correctamente.", exerciseCategories);
            return new ResponseEntity<>(response, HttpStatus.OK); 

        } catch (RuntimeException e) {
            ApiResponse<List<ExerciseCategory>> response = new ApiResponse<>("Ocurrió un error al solicitar los datos de las categorías de ejercicios.", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
        }

    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addExerciseCategory(@RequestBody ExerciseCategoryDTO exerciseExerciseCategoryDTO) {
        int result = exerciseCategoryService.addExerciseCategory(exerciseExerciseCategoryDTO.getName(), exerciseExerciseCategoryDTO.getParamLoggedIdUser());

        switch (result) {
            case 1 -> {
                ApiResponse<String> response = new ApiResponse<>("Categoría de ejercicio agregada correctamente.", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            // error de MySQL
            case 0 ->
                throw new AppException("Ocurrió un error al agregar la nueva categoría de ejercicio.", HttpStatus.INTERNAL_SERVER_ERROR);

            // no se encuentra el idUser
            case -1 ->
                throw new AppException("No se pudo agregar la nueva categoría de ejercicio debido a que el usuario asociado no está registrado.", HttpStatus.INTERNAL_SERVER_ERROR);

            default ->
                throw new AppException("Categoría de ejercicio no agregada debido a problemas en la consulta.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<String>> updateNotificationTemplate(@RequestBody ExerciseCategoryDTO categoryDTO) {
        int result = exerciseCategoryService.updateExerciseCategory(
            categoryDTO.getIdExerciseCategory(),
            categoryDTO.getName(),
            categoryDTO.getIsDeleted(),
            categoryDTO.getParamLoggedIdUser()
        );

        switch (result) {
            case 1 -> {
                ApiResponse<String> response = new ApiResponse<>("Categoría actualizada correctamente.", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            // error de MySQL
            case 0 ->
                throw new AppException("Ocurrió un error al actualizar la categoría de ejercicio.", HttpStatus.INTERNAL_SERVER_ERROR);

            // no se encuentra el idNotificationTemplate
            case -1 ->
                throw new AppException("No se pudo actualizar la categoría de ejercicio debido a que no se encuentra el registro.", HttpStatus.INTERNAL_SERVER_ERROR);

            // no se encuentra el idUser
            case -2 ->
                throw new AppException("No se pudo actualizar la categoría de ejercicio debido a que el usuario asociado no está registrado.", HttpStatus.INTERNAL_SERVER_ERROR);

            default ->
                throw new AppException("Categoría no actualizada debido a problemas en la consulta.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/delete/{idExerciseCategory}")
    public ResponseEntity<ApiResponse<String>> deleteExerciseCategory(@PathVariable("idExerciseCategory") Long idExerciseCategory, @RequestBody ParamLoggedIdUserDTO paramLoggedIdUser) {
        int result = exerciseCategoryService.deleteExerciseCategory(idExerciseCategory, paramLoggedIdUser.getParamLoggedIdUser());

        switch (result) {
            case 1 -> {
                ApiResponse<String> response = new ApiResponse<>("Categoría eliminada correctamente.", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            case 0 ->
                throw new AppException("Ocurrió un error al eliminar la categoría de ejercicio.", HttpStatus.INTERNAL_SERVER_ERROR);
            case -1 ->
                throw new AppException("No se encontró la categoría de ejercicio para eliminar.", HttpStatus.INTERNAL_SERVER_ERROR);
            default ->
                throw new AppException("Error inesperado al eliminar categoría de ejercicio.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
