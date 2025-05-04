package una.force_gym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import una.force_gym.dto.RoutineWithExercisesDTO;
import una.force_gym.service.RoutineService;
import una.force_gym.util.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/routine")
public class RoutineController {

    @Autowired
    private RoutineService routineService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<RoutineWithExercisesDTO>> createRoutineWithExercisesAndClients(
            @RequestBody RoutineWithExercisesDTO routineDTO) {
        try {
            RoutineWithExercisesDTO createdRoutine = routineService.saveWithExercisesAndClients(routineDTO);
            ApiResponse<RoutineWithExercisesDTO> response = new ApiResponse<>(
                    "Rutina creada correctamente con ejercicios y asignaciones de clientes.",
                    createdRoutine
            );
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            ApiResponse<RoutineWithExercisesDTO> response = new ApiResponse<>(
                    "Error al crear la rutina: " + e.getMessage(),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<RoutineWithExercisesDTO>> updateRoutineWithExercisesAndClients(
            @RequestBody RoutineWithExercisesDTO routineDTO) {
        try {
            RoutineWithExercisesDTO updatedRoutine = routineService.updateWithExercisesAndClients(routineDTO);
            ApiResponse<RoutineWithExercisesDTO> response = new ApiResponse<>(
                    "Rutina actualizada correctamente con ejercicios y asignaciones de clientes.",
                    updatedRoutine
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            ApiResponse<RoutineWithExercisesDTO> response = new ApiResponse<>(
                    "Error al actualizar la rutina: " + e.getMessage(),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<RoutineWithExercisesDTO>>> getAllRoutinesWithDetails() {
        try {
            List<RoutineWithExercisesDTO> routines = routineService.getAllRoutinesWithDetails();
            ApiResponse<List<RoutineWithExercisesDTO>> response = new ApiResponse<>(
                    "Rutinas obtenidas correctamente con sus detalles.",
                    routines
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            ApiResponse<List<RoutineWithExercisesDTO>> response = new ApiResponse<>(
                    "Error al obtener las rutinas: " + e.getMessage(),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoutineWithExercisesDTO>> getRoutineWithDetails(@PathVariable Long id) {
        try {
            RoutineWithExercisesDTO routine = routineService.getRoutineWithDetails(id);
            ApiResponse<RoutineWithExercisesDTO> response = new ApiResponse<>(
                    "Rutina obtenida correctamente con sus detalles.",
                    routine
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            ApiResponse<RoutineWithExercisesDTO> response = new ApiResponse<>(
                    "Error al obtener la rutina: " + e.getMessage(),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRoutineWithDependencies(@PathVariable Long id) {
        try {
            routineService.deleteRoutineWithDependencies(id);
            ApiResponse<Void> response = new ApiResponse<>(
                    "Rutina eliminada correctamente con todas sus dependencias.",
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            ApiResponse<Void> response = new ApiResponse<>(
                    "Error al eliminar la rutina: " + e.getMessage(),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<ApiResponse<List<RoutineWithExercisesDTO>>> getRoutinesByClient(
            @PathVariable Long clientId) {
        try {
            List<RoutineWithExercisesDTO> routines = routineService.getRoutinesByClient(clientId);
            ApiResponse<List<RoutineWithExercisesDTO>> response = new ApiResponse<>(
                    "Rutinas del cliente obtenidas correctamente.",
                    routines
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            ApiResponse<List<RoutineWithExercisesDTO>> response = new ApiResponse<>(
                    "Error al obtener las rutinas del cliente: " + e.getMessage(),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
