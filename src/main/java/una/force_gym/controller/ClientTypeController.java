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

import una.force_gym.dto.ClientTypeDTO;
import una.force_gym.dto.ParamLoggedIdUserDTO;
import una.force_gym.exception.AppException;
import una.force_gym.service.ClientTypeService;
import una.force_gym.util.ApiResponse;

@RestController
@RequestMapping("/clientType")
public class ClientTypeController {

    @Autowired
    private ClientTypeService clientTypeService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getClientTypes(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "1") int searchType,
            @RequestParam(defaultValue = "") String searchTerm,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "") String directionOrderBy,
            @RequestParam(defaultValue = "") String filterByStatus) {

        try {
            Map<String, Object> responseData = clientTypeService.getClientTypes(page, size, searchType, searchTerm, orderBy, directionOrderBy, filterByStatus);
            ApiResponse<Map<String, Object>> response = new ApiResponse<>("Tipos de cliente obtenidos correctamente.", responseData);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            ApiResponse<Map<String, Object>> response = new ApiResponse<>("Ocurrió un error al solicitar los tipos de cliente.", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addClientType(@RequestBody ClientTypeDTO clientTypeDTO) {
        int result = clientTypeService.addClientType(
                clientTypeDTO.getName(),
                clientTypeDTO.getParamLoggedIdUser()
        );

        return switch (result) {
            case 1 -> new ResponseEntity<>(new ApiResponse<>("Tipo de cliente agregado correctamente.", null), HttpStatus.OK);
            case 0 -> throw new AppException("Ocurrió un error al agregar el tipo de cliente.", HttpStatus.INTERNAL_SERVER_ERROR);
            default -> throw new AppException("Tipo de cliente no agregado debido a problemas en la consulta.", HttpStatus.INTERNAL_SERVER_ERROR);
        };
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<String>> updateClientType(@RequestBody ClientTypeDTO clientTypeDTO) {
        int result = clientTypeService.updateClientType(
                clientTypeDTO.getIdClientType(),
                clientTypeDTO.getName(),
                clientTypeDTO.getIsDeleted(),
                clientTypeDTO.getParamLoggedIdUser()
        );

        return switch (result) {
            case 1 -> new ResponseEntity<>(new ApiResponse<>("Tipo de cliente actualizado correctamente.", null), HttpStatus.OK);
            case 0 -> throw new AppException("Ocurrió un error al actualizar el tipo de cliente.", HttpStatus.INTERNAL_SERVER_ERROR);
            case -1 -> throw new AppException("No se pudo actualizar el tipo de cliente debido a que no se encuentra el registro.", HttpStatus.INTERNAL_SERVER_ERROR);
            default -> throw new AppException("Tipo de cliente no actualizado debido a problemas en la consulta.", HttpStatus.INTERNAL_SERVER_ERROR);
        };
    }

    @DeleteMapping("/delete/{idClientType}")
    public ResponseEntity<ApiResponse<String>> deleteClientType(@PathVariable("idClientType") Long idClientType, @RequestBody ParamLoggedIdUserDTO paramLoggedIdUser) {
        int result = clientTypeService.deleteClientType(idClientType, paramLoggedIdUser.getParamLoggedIdUser());

        return switch (result) {
            case 1 -> new ResponseEntity<>(new ApiResponse<>("Tipo de cliente eliminado correctamente.", null), HttpStatus.OK);
            case 0 -> throw new AppException("Ocurrió un error al eliminar el tipo de cliente.", HttpStatus.INTERNAL_SERVER_ERROR);
            case -1 -> throw new AppException("No se pudo eliminar el tipo de cliente debido a que no se encuentra el registro.", HttpStatus.INTERNAL_SERVER_ERROR);
            default -> throw new AppException("Tipo de cliente no eliminado debido a problemas en la consulta.", HttpStatus.INTERNAL_SERVER_ERROR);
        };
    }
}
