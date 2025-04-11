package una.force_gym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<Category>>> getCategory() {
        try {
            List<Category> category = categoryService.getCategory();
            ApiResponse<List<Category>> response = new ApiResponse<>("Categoría obtenida correctamente.", category);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (RuntimeException e) {
            ApiResponse<List<Category>> response = new ApiResponse<>("Ocurrió un error al solicitar los datos de la categoría.", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addCategory(@RequestBody CategoryDTO categoryDTO) {
        int result = categoryService.addCategory(categoryDTO.getName(), categoryDTO.getParamLoggedIdUser());

        switch (result) {
            case 1 -> {
                ApiResponse<String> response = new ApiResponse<>("Categoría agregada correctamente.", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            case 0 ->
                throw new AppException("Ocurrió un error al agregar la categoría.", HttpStatus.INTERNAL_SERVER_ERROR);
            default ->
                throw new AppException("Categoría no agregada por un error inesperado.", HttpStatus.INTERNAL_SERVER_ERROR);
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
