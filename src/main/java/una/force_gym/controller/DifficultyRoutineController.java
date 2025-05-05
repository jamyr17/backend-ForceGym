package una.force_gym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import una.force_gym.domain.DifficultyRoutine;
import una.force_gym.service.DifficultyRoutineService;
import una.force_gym.util.ApiResponse;

@RestController
@RequestMapping("/difficultyRoutine")
public class DifficultyRoutineController {

    @Autowired
    private DifficultyRoutineService DifficultyRoutineService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<DifficultyRoutine>>> getDifficultyRoutines() {
        try {
            List<DifficultyRoutine> DifficultyRoutines = DifficultyRoutineService.getDifficultyRoutines();
            ApiResponse<List<DifficultyRoutine>> response = new ApiResponse<>("DifficultyRoutines obtenidos correctamente.", DifficultyRoutines);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (RuntimeException e) {
            ApiResponse<List<DifficultyRoutine>> response = new ApiResponse<>("Ocurrió un error al solicitar los datos de los DifficultyRoutines.", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
