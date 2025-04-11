package una.force_gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import una.force_gym.domain.Category;
import una.force_gym.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    @Transactional
    public List<Category> getCategory() {
        return categoryRepo.getCategory();
    }

    @Transactional
    public int addCategory(String name, Long loggedIdUser) {
        return categoryRepo.addCategory(name, loggedIdUser);
    }

    @Transactional
    public int deleteCategory(Long idCategory, Long loggedIdUser) {
        return categoryRepo.deleteCategory(idCategory, loggedIdUser);
    }

}
