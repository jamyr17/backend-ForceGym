package una.force_gym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import una.force_gym.domain.ActivityType;
import una.force_gym.dto.ActivityTypeWithFeesDTO;
import una.force_gym.service.ActivityTypeService;
import una.force_gym.util.ApiResponse;

@RestController
@RequestMapping("/activityType")
public class ActivityTypeController {
    
    @Autowired
    private ActivityTypeService activityTypeService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<ActivityType>>> getActivityTypes() {
        try {
            List<ActivityType> activityTypes = activityTypeService.getActivityTypes();
            ApiResponse<List<ActivityType>> response = new ApiResponse<>(
                "Tipos de actividad obtenidos correctamente.", 
                activityTypes
            );
            return new ResponseEntity<>(response, HttpStatus.OK); 

        } catch (RuntimeException e) {
            ApiResponse<List<ActivityType>> response = new ApiResponse<>(
                "Ocurrió un error al solicitar los datos de los tipos de actividad.", 
                null
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    @GetMapping("/listFees")
    public ResponseEntity<ApiResponse<List<ActivityTypeWithFeesDTO>>> getActivityTypesWithFees() {
        try {
            // Necesitarás implementar este método en el servicio
            List<ActivityTypeWithFeesDTO> activityTypes = activityTypeService.getActivityTypesWithFees();
            ApiResponse<List<ActivityTypeWithFeesDTO>> response = new ApiResponse<>(
                "Tipos de actividad con tarifas obtenidos correctamente.", 
                activityTypes
            );
            return new ResponseEntity<>(response, HttpStatus.OK); 

        } catch (RuntimeException e) {
            ApiResponse<List<ActivityTypeWithFeesDTO>> response = new ApiResponse<>(
                "Ocurrió un error al solicitar los tipos de actividad con tarifas.", 
                null
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<ActivityType>> createActivityTypeWithFees(
        @RequestBody ActivityTypeWithFeesDTO activityTypeDTO
    ) {
        try {
            ActivityType createdActivity = activityTypeService.saveWithFees(activityTypeDTO);
            ApiResponse<ActivityType> response = new ApiResponse<>(
                "Tipo de actividad creado correctamente.", 
                createdActivity
            );
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (RuntimeException e) {
            ApiResponse<ActivityType> response = new ApiResponse<>(
                "Error al crear el tipo de actividad: " + e.getMessage(), 
                null
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<ActivityType>> updateActivityTypeWithFees(
        @RequestBody ActivityTypeWithFeesDTO activityTypeDTO
    ) {
        try {
            ActivityType updatedActivity = activityTypeService.updateWithFees(activityTypeDTO);
            ApiResponse<ActivityType> response = new ApiResponse<>(
                "Tipo de actividad actualizado correctamente.", 
                updatedActivity
            );
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (RuntimeException e) {
            ApiResponse<ActivityType> response = new ApiResponse<>(
                "Error al actualizar el tipo de actividad: " + e.getMessage(), 
                null
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{idActivityType}")
    public ResponseEntity<ApiResponse<Void>> deleteActivityTypeWithFees(@PathVariable Long idActivityType) {
        try {
            activityTypeService.deleteWithFees(idActivityType);
            ApiResponse<Void> response = new ApiResponse<>(
                "Tipo de actividad eliminado correctamente.", 
                null
            );
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

        } catch (RuntimeException e) {
            ApiResponse<Void> response = new ApiResponse<>(
                "Error al eliminar el tipo de actividad: " + e.getMessage(), 
                null
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}