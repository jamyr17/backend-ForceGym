package una.force_gym.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import una.force_gym.domain.Routine;
import una.force_gym.dto.RoutineWithExercisesDTO;
import una.force_gym.service.RoutineService;
import una.force_gym.util.ApiResponse;

@RestController
@RequestMapping("/routine")
public class RoutineController {

    @Autowired
    private RoutineService routineService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<Routine>>> getRoutines() {
        try {
            List<Routine> routines = routineService.getRoutines();
            ApiResponse<List<Routine>> response = new ApiResponse<>(
                    "Rutinas obtenidas correctamente.",
                    routines
            );
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (RuntimeException e) {
            ApiResponse<List<Routine>> response = new ApiResponse<>(
                    "Ocurrió un error al solicitar las rutinas: " + e.getMessage(),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listExercises")
    public ResponseEntity<ApiResponse<List<RoutineWithExercisesDTO>>> getRoutinesWithExercises() {
        try {
            List<RoutineWithExercisesDTO> routines = routineService.getRoutinesWithExercisesDto();
            ApiResponse<List<RoutineWithExercisesDTO>> response = new ApiResponse<>(
                    "Rutinas con ejercicios obtenidas correctamente.",
                    routines
            );
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (RuntimeException e) {
            ApiResponse<List<RoutineWithExercisesDTO>> response = new ApiResponse<>(
                    "Ocurrió un error al solicitar las rutinas con ejercicios: " + e.getMessage(),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Routine>> createRoutineWithExercises(
            @RequestBody RoutineWithExercisesDTO routineDTO
    ) {
        try {
            Routine createdRoutine = routineService.saveWithExercises(routineDTO);
            ApiResponse<Routine> response = new ApiResponse<>(
                    "Rutina creada correctamente.",
                    createdRoutine
            );
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (RuntimeException e) {
            ApiResponse<Routine> response = new ApiResponse<>(
                    "Error al crear la rutina: " + e.getMessage(),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<Routine>> updateRoutineWithExercises(
            @RequestBody RoutineWithExercisesDTO routineDTO
    ) {
        try {
            Routine updatedRoutine = routineService.updateWithExercises(routineDTO);
            ApiResponse<Routine> response = new ApiResponse<>(
                    "Rutina actualizada correctamente.",
                    updatedRoutine
            );
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (RuntimeException e) {
            ApiResponse<Routine> response = new ApiResponse<>(
                    "Error al actualizar la rutina: " + e.getMessage(),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{idRoutine}")
    public ResponseEntity<ApiResponse<Void>> deleteRoutineWithExercises(@PathVariable Long idRoutine) {
        try {
            routineService.deleteWithExercises(idRoutine);
            ApiResponse<Void> response = new ApiResponse<>(
                    "Rutina eliminada correctamente.",
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
}
