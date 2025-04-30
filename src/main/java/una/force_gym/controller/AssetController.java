package una.force_gym.controller;

import java.util.HashMap;
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
import org.springframework.web.servlet.ModelAndView;

import una.force_gym.dto.ParamLoggedIdUserDTO;
import una.force_gym.dto.Asset;
import una.force_gym.exception.AppException;
import una.force_gym.service.AssetService;
import una.force_gym.util.ApiResponse;

@RestController
@RequestMapping("/asset")
public class AssetController {
    
    @Autowired
    private AssetService assetService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getAssets( 
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "1") int searchType,
            @RequestParam(defaultValue = "") String searchTerm,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "") String directionOrderBy,
            @RequestParam(defaultValue = "") String filterByStatus,
            @RequestParam(required = false) Long filterByCostRangeMin,
            @RequestParam(required = false) Long filterByCostRangeMax,
            @RequestParam(required = false) Long filterByQuantityRangeMin,
            @RequestParam(required = false) Long filterByQuantityRangeMax)  {
        try {
            Map<String, Object> responseData = assetService.getAssets(page, size, searchType, searchTerm, orderBy, directionOrderBy, filterByStatus, filterByCostRangeMin, filterByCostRangeMax, filterByQuantityRangeMin, filterByQuantityRangeMax);
            ApiResponse<Map<String, Object>> response = new ApiResponse<>("Activos obtenidos correctamente.", responseData);
            return new ResponseEntity<>(response, HttpStatus.OK); 

        } catch (RuntimeException e) {
            ApiResponse<Map<String, Object>> response = new ApiResponse<>("Ocurrió un error al solicitar los datos de los activos", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
        }

    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addAsset(@RequestBody Asset assetDTO) {
        int result = assetService.addAsset(
            assetDTO.getIdUser(), 
            assetDTO.getBoughtDate(),
            assetDTO.getCode(), 
            assetDTO.getName(), 
            assetDTO.getQuantity(), 
            assetDTO.getInitialCost(),  
            assetDTO.getServiceLifeYears(),
            assetDTO.getParamLoggedIdUser()
        );

        switch(result) {
            case 1 -> 
            { 
                ApiResponse<String> response = new ApiResponse<>("Activo agregado correctamente.", null);
                return new ResponseEntity<>(response, HttpStatus.OK); 
            }

            // error de MySQL
            case 0 -> throw new AppException("Ocurrió un error al agregar el nuevo activo .", HttpStatus.INTERNAL_SERVER_ERROR);
            
            // no se encuentra el idUser
            case -1 -> throw new AppException("No se pudo agregar el nuevo activo debido a que el usuario asociado no está registrado.", HttpStatus.INTERNAL_SERVER_ERROR);
            
            default -> throw new AppException("Activo no agregado debido a problemas en la consulta.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<String>> updateAsset(@RequestBody Asset assetDTO) {
        int result = assetService.updateAsset(
            assetDTO.getIdAsset(), 
            assetDTO.getIdUser(), 
            assetDTO.getBoughtDate(),
            assetDTO.getCode(), 
            assetDTO.getName(), 
            assetDTO.getQuantity(), 
            assetDTO.getInitialCost(),  
            assetDTO.getServiceLifeYears(),
            assetDTO.getIsDeleted(),
            assetDTO.getParamLoggedIdUser()
        );

        switch(result) {
            case 1 -> 
            { 
                ApiResponse<String> response = new ApiResponse<>("Activo actualizado correctamente.", null);
                return new ResponseEntity<>(response, HttpStatus.OK); 
            }

            // error de MySQL
            case 0 -> throw new AppException("Ocurrió un error al actualizar el activo de inventario.", HttpStatus.INTERNAL_SERVER_ERROR);

            // no se encuentra el idProductInventory
            case -1 -> throw new AppException("No se pudo actualizar el activo debido a que no se encuentra el registro.", HttpStatus.INTERNAL_SERVER_ERROR);

            // no se encuentra el idUser
            case -2 -> throw new AppException("No se pudo actualizar el activo debido a que el usuario asociado no está registrado.", HttpStatus.INTERNAL_SERVER_ERROR);
            
            default -> throw new AppException("Activo no actualizado debido a problemas en la consulta.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/delete/{idAsset}")
    public ResponseEntity<ApiResponse<String>> deleteAsset(@PathVariable("idAsset") Long idAsset, @RequestBody ParamLoggedIdUserDTO paramLoggedIdUser) {
        int result = assetService.deleteAsset(idAsset, paramLoggedIdUser.getParamLoggedIdUser());
       
        switch(result) {
            case 1 -> 
            { 
                ApiResponse<String> response = new ApiResponse<>("Activo eliminado correctamente.", null);
                return new ResponseEntity<>(response, HttpStatus.OK); 
            }

            // error de MySQL
            case 0 -> throw new AppException("Ocurrió un error al eliminar el activo.", HttpStatus.INTERNAL_SERVER_ERROR);

            // no se encuentra el idProductInventory
            case -1 -> throw new AppException("No se pudo eliminar el activodebido a que no se encuentra el registro.", HttpStatus.INTERNAL_SERVER_ERROR);

            default -> throw new AppException("Activo no eliminado debido a problemas en la consulta.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    // Nuevo endpoint para exportar los activos a PDF
    @GetMapping("/exportToPdf")
    public ModelAndView exportToPdf() {
        try {
            // Obtener los activos desde el servicio
            Map<String, Object> responseData = assetService.getAssets(1, 10, 1, "", "", "", "", null, null, null, null);

            // El modelo pasa los datos de los activos con la clave correcta
            Map<String, Object> model = new HashMap<>();
            model.put("activos", responseData.get("activos"));

            // Retorna el PDF a partir de la vista 'ProductInventoryPDF'
            return new ModelAndView("ProductInventoryPDF", model);

        } catch (Exception e) {
            // Manejo de errores si no se puede generar el PDF
            return new ModelAndView("error", "message", "No se pudieron obtener los activos.");
        }
    }

}
