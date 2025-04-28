package una.force_gym.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import una.force_gym.dto.RoutineDTO;
import una.force_gym.service.RoutineService;
import una.force_gym.util.ApiResponse;

@RestController
@RequestMapping("/routine")
public class RoutineController {

    @Autowired
    private RoutineService routineService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addRoutine(@RequestBody RoutineDTO dto) {
        int result = routineService.addRoutine(
                dto.getName(),
                dto.getDate(),
                dto.getIdUser(),
                dto.getParamLoggedIdUser(),
                dto.getExercises(),
                dto.getAssignments()
        );

        switch (result) {
            case 1 -> {
                ApiResponse<String> response = new ApiResponse<>("Rutina agregada correctamente.", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            case 0 -> {
                ApiResponse<String> response = new ApiResponse<>("Ocurrió un error al agregar la rutina.", null);
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            default -> {
                ApiResponse<String> response = new ApiResponse<>("Error inesperado al agregar la rutina.", null);
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getRoutines(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "1") int searchType,
            @RequestParam(defaultValue = "") String searchTerm,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "") String directionOrderBy,
            @RequestParam(defaultValue = "") String filterByStatus,
            @RequestParam(defaultValue = "-1") int filterByNotificationType
    ) {
        try {
            Map<String, Object> responseData = routineService.getRoutines(page, size, searchType, searchTerm, orderBy, directionOrderBy, filterByStatus, filterByNotificationType);
            ApiResponse<Map<String, Object>> response = new ApiResponse<>("Rutinas obtenidas correctamente.", responseData);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            ApiResponse<Map<String, Object>> response = new ApiResponse<>("Ocurrió un error al solicitar los datos de las rutinas.", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
