package una.force_gym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import una.force_gym.dto.RoutineDTO;
import una.force_gym.service.RoutineService;
import una.force_gym.util.ApiResponse;

import java.util.Map;

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

        return switch (result) {
            case 1 ->
                ResponseEntity.ok(new ApiResponse<>("Rutina registrada correctamente.", null));
            case 0 ->
                ResponseEntity.internalServerError().body(new ApiResponse<>("Error al registrar la rutina.", null));
            default ->
                ResponseEntity.internalServerError().body(new ApiResponse<>("Error inesperado al registrar la rutina.", null));
        };
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getRoutines(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "1") int searchType,
            @RequestParam(defaultValue = "") String searchTerm,
            @RequestParam(defaultValue = "idRoutine") String orderBy,
            @RequestParam(defaultValue = "ASC") String directionOrderBy,
            @RequestParam(defaultValue = "") String filterByStatus
    ) {
        Map<String, Object> result = routineService.getRoutines(
                page, limit, searchType, searchTerm, orderBy, directionOrderBy, filterByStatus
        );
        return ResponseEntity.ok(new ApiResponse<>("Rutinas obtenidas correctamente.", result));
    }
}
