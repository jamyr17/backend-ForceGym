package una.force_gym.service;

import una.force_gym.repository.ExerciseRepository;
import una.force_gym.service.ExerciseService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ExerciseServiceTest {

    protected ExerciseRepository exerciseRepository;
    protected ExerciseService exerciseService;

    @BeforeEach
    void setUp() {
        exerciseRepository = mock(ExerciseRepository.class);
        exerciseService = new ExerciseService();
        exerciseService.exerciseRepo = exerciseRepository;
    }

    @Test
    void shouldReturnMinusTwoWhenNameAlreadyExists() {
        // Arrange
        String name = "Esto no existe";
        String description = "Ejercicio de";
        Long idDifficulty = 1L;
        Long idCategory = 1L;
        Long userId = 1L;

        when(exerciseRepository.addExercise(name, description, idDifficulty, idCategory, userId))
                .thenReturn(-2);

        // Act
        int result = exerciseService.addExercise(name, description, idDifficulty, idCategory, userId);

        System.out.println("El ejercicio ya existe en la base de datos (resultado = -2)"); 
  
        // Assert
        assertEquals(-2, result, "Debe retornar -2 si el nombre del ejercicio ya existe");
    }

    @Test
    void shouldReturnPositiveWhenNewExerciseIsAdded() {
        // Arrange
        String name = "Nuevo Ejercicio";
        String description = "Descripción del nuevo ejercicio";
        Long idDifficulty = 2L;
        Long idCategory = 3L;
        Long userId = 1L;

        // Simulamos que el repositorio devuelve 1 al agregar con éxito
        when(exerciseRepository.addExercise(name, description, idDifficulty, idCategory, userId))
                .thenReturn(1);

        // Act
        int result = exerciseService.addExercise(name, description, idDifficulty, idCategory, userId);

        System.out.println("El ejercicio fue agregado correctamente (resultado = " + result + ")");

        // Assert
        assertTrue(result > 0, "Debe retornar un valor positivo cuando el ejercicio se agrega correctamente");
    }
}
