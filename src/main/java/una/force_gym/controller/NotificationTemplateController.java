package una.force_gym.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import una.force_gym.dto.NotificationTemplateDTO;
import una.force_gym.dto.ParamLoggedIdUserDTO;
import una.force_gym.exception.AppException;
import una.force_gym.service.NotificationTemplateService;
import una.force_gym.util.ApiResponse;

@RestController
@RequestMapping("/notificationTemplate")
public class NotificationTemplateController {

    @Autowired
    private NotificationTemplateService notificationTemplateService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getNotificactionTemplates(
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
            Map<String, Object> responseData = notificationTemplateService.getNotificationTemplates(page, size, searchType, searchTerm, orderBy, directionOrderBy, filterByStatus, filterByNotificationType);
            ApiResponse<Map<String, Object>> response = new ApiResponse<>("Plantillas de notificación obtenidas correctamente.", responseData);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (RuntimeException e) {
            ApiResponse<Map<String, Object>> response = new ApiResponse<>("Ocurrió un error al solicitar los datos de las plantillas de notificación.", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addNotificationTemplate(@RequestBody NotificationTemplateDTO notificationTemplateDTO) {
        int result = notificationTemplateService.addNotificationTemplate(
                notificationTemplateDTO.getIdNotificationType(),
                notificationTemplateDTO.getIdUser(),
                notificationTemplateDTO.getMessage(),
                notificationTemplateDTO.getParamLoggedIdUser()
        );

        switch (result) {
            case 1 -> {
                ApiResponse<String> response = new ApiResponse<>("Plantilla de notificación agregada correctamente.", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            // error de MySQL
            case 0 ->
                throw new AppException("Ocurrió un error al agregar la nueva plantilla de notificación.", HttpStatus.INTERNAL_SERVER_ERROR);

            // no se encuentra el idUser
            case -1 ->
                throw new AppException("No se pudo agregar la plantilla debido a que el usuario asociado no está registrado.", HttpStatus.BAD_REQUEST);

            // no se encuentra el idNotificationType
            case -2 ->
                throw new AppException("No se pudo agregar la plantilla debido a que el tipo de notificación no está registrado.", HttpStatus.BAD_REQUEST);

            // mensaje vacío
            case -3 ->
                throw new AppException("El mensaje de la plantilla no puede estar vacío.", HttpStatus.BAD_REQUEST);

            // plantilla duplicada
            case -4 ->
                throw new AppException("Ya existe una plantilla con el mismo mensaje para este usuario y tipo de notificación.", HttpStatus.CONFLICT);

            default ->
                throw new AppException("Plantilla no agregada debido a problemas en la consulta.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<String>> updateNotificationTemplate(@RequestBody NotificationTemplateDTO notificationTemplateDTO) {
        int result = notificationTemplateService.updateNotificationTemplate(
                notificationTemplateDTO.getIdNotificationTemplate(),
                notificationTemplateDTO.getIdNotificationType(),
                notificationTemplateDTO.getIdUser(),
                notificationTemplateDTO.getMessage(),
                notificationTemplateDTO.getIsDeleted(),
                notificationTemplateDTO.getParamLoggedIdUser()
        );

        switch (result) {
            case 1 -> {
                ApiResponse<String> response = new ApiResponse<>("Plantilla de notificación actualizada correctamente.", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            // error de MySQL
            case 0 ->
                throw new AppException("Ocurrió un error al actualizar la plantilla de notificación.", HttpStatus.INTERNAL_SERVER_ERROR);

            // no se encuentra el idNotificationTemplate
            case -1 ->
                throw new AppException("No se pudo actualizar la plantilla debido a que no se encuentra el registro.", HttpStatus.NOT_FOUND);

            // no se encuentra el idUser
            case -2 ->
                throw new AppException("No se pudo actualizar la plantilla debido a que el usuario asociado no está registrado.", HttpStatus.BAD_REQUEST);

            // no se encuentra el idNotificationType
            case -3 ->
                throw new AppException("No se pudo actualizar la plantilla debido a que el tipo de notificación no está registrado.", HttpStatus.BAD_REQUEST);

            // mensaje vacío
            case -4 ->
                throw new AppException("El mensaje de la plantilla no puede estar vacío.", HttpStatus.BAD_REQUEST);

            // plantilla duplicada
            case -5 ->
                throw new AppException("Ya existe una plantilla con el mismo mensaje para este usuario y tipo de notificación.", HttpStatus.CONFLICT);

            default ->
                throw new AppException("Plantilla no actualizada debido a problemas en la consulta.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{idNotificationTemplate}")
    public ResponseEntity<ApiResponse<String>> deleteNotificationTemplate(@PathVariable("idNotificationTemplate") Long idNotificationTemplate, @RequestBody ParamLoggedIdUserDTO paramLoggedIdUser) {
        int result = notificationTemplateService.deleteNotificationTemplate(idNotificationTemplate, paramLoggedIdUser.getParamLoggedIdUser());

        switch (result) {
            case 1 -> {
                ApiResponse<String> response = new ApiResponse<>("Plantilla de notificación eliminada correctamente.", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            // error de MySQL
            case 0 ->
                throw new AppException("Ocurrió un error al eliminar la plantilla de notificación.", HttpStatus.INTERNAL_SERVER_ERROR);

            // no se encuentra el idNotificationTemplate
            case -1 ->
                throw new AppException("No se pudo eliminar la plantilla debido a que no se encuentra el registro.", HttpStatus.NOT_FOUND);

            default ->
                throw new AppException("Plantilla no eliminada debido a problemas en la consulta.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
