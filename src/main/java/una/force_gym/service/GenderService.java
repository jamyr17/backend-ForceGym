package una.force_gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import una.force_gym.domain.Gender;
import una.force_gym.repository.GenderRepository;

@Service
public class GenderService {

    @Autowired
    private GenderRepository genderRepository;

    @Transactional
    public List<Gender> getGenders(){
        return genderRepository.getGenders();
    }
    
}
