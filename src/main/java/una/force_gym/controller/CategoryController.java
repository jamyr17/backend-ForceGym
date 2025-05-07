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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import una.force_gym.domain.Category;
import una.force_gym.dto.CategoryDTO;
import una.force_gym.dto.ParamLoggedIdUserDTO;
import una.force_gym.exception.AppException;
import una.force_gym.service.CategoryService;
import una.force_gym.util.ApiResponse;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listAll")
    public ResponseEntity<ApiResponse<List<Category>>> getCategorys() {
        try {
            List<Category> category = categoryService.getCategory();
            ApiResponse<List<Category>> response = new ApiResponse<>("Categoría obtenida correctamente.", category);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (RuntimeException e) {
            ApiResponse<List<Category>> response = new ApiResponse<>("Ocurrió un error al solicitar los datos de la categoría.", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getCategorys(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "1") int searchType,
            @RequestParam(defaultValue = "") String searchTerm,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "") String directionOrderBy,
            @RequestParam(defaultValue = "") String filterByStatus
    ) {
        try {
            Map<String, Object> responseData = categoryService.getCategories(page, size, searchType, searchTerm, orderBy, directionOrderBy, filterByStatus);
            ApiResponse<Map<String, Object>> response = new ApiResponse<>("Categorias obtenidas correctamente.", responseData);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (RuntimeException e) {
            ApiResponse<Map<String, Object>> response = new ApiResponse<>("Ocurrió un error al solicitar los datos de las categorias.", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addCategory(@RequestBody CategoryDTO categoryDTO) {
        int result = categoryService.addCategory(categoryDTO.getName(), categoryDTO.getParamLoggedIdUser());

        switch (result) {
            case 1 -> {
                ApiResponse<String> response = new ApiResponse<>("Categoria agregado correctamente.", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            // error de MySQL
            case 0 ->
                throw new AppException("Ocurrió un error al agregar la nueva categoria.", HttpStatus.INTERNAL_SERVER_ERROR);

            // no se encuentra el idUser
            case -1 ->
                throw new AppException("No se pudo agregar la nueva categoria debido a que el usuario asociado no está registrado.", HttpStatus.INTERNAL_SERVER_ERROR);

            // no se encuentra el idUser
            case -2 ->
                throw new AppException("No se pudo actualizar la categoría debido a que el nombre ya fue asignado a otro registro", HttpStatus.INTERNAL_SERVER_ERROR);

            default ->
                throw new AppException("Categoria no agregada debido a problemas en la consulta.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<String>> updateNotificationTemplate(@RequestBody CategoryDTO categoryDTO) {
        int result = categoryService.updateCategory(
                categoryDTO.getIdCategory(),
                categoryDTO.getName(),
                categoryDTO.getIsDeleted(),
                categoryDTO.getParamLoggedIdUser()
        );

        switch (result) {
            case 1 -> {
                ApiResponse<String> response = new ApiResponse<>("Categoría actualizado correctamente.", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            // error de MySQL
            case 0 ->
                throw new AppException("Ocurrió un error al actualizar la categoría.", HttpStatus.INTERNAL_SERVER_ERROR);

            // no se encuentra el idNotificationTemplate
            case -1 ->
                throw new AppException("No se pudo actualizar la categoría debido a que no se encuentra el registro.", HttpStatus.INTERNAL_SERVER_ERROR);

            // name duplicado
            case -2 ->
                throw new AppException("No se pudo actualizar la categoría debido a que el nuevo nombre ya fue asignado a otro registro", HttpStatus.INTERNAL_SERVER_ERROR);

            default ->
                throw new AppException("Categoría no actualizada debido a problemas en la consulta.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/delete/{idCategory}")
    public ResponseEntity<ApiResponse<String>> deleteCategory(@PathVariable("idCategory") Long idCategory, @RequestBody ParamLoggedIdUserDTO paramLoggedIdUser) {
        int result = categoryService.deleteCategory(idCategory, paramLoggedIdUser.getParamLoggedIdUser());

        switch (result) {
            case 1 -> {
                ApiResponse<String> response = new ApiResponse<>("Categoría eliminada correctamente.", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            case 0 ->
                throw new AppException("Ocurrió un error al eliminar la categoría.", HttpStatus.INTERNAL_SERVER_ERROR);
            case -1 ->
                throw new AppException("No se encontró la categoría para eliminar.", HttpStatus.INTERNAL_SERVER_ERROR);
            default ->
                throw new AppException("Error inesperado al eliminar categoría.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
