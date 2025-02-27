package una.force_gym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import una.force_gym.domain.Category;
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
}