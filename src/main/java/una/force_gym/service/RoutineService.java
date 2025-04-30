package una.force_gym.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import una.force_gym.domain.DifficultyRoutine;
import una.force_gym.domain.Exercise;
import una.force_gym.domain.Routine;
import una.force_gym.domain.RoutineExercise;
import una.force_gym.domain.User;
import una.force_gym.dto.RoutineExerciseDTO;
import una.force_gym.dto.RoutineWithExercisesDTO;
import una.force_gym.repository.DifficultyRoutineRepository;
import una.force_gym.repository.ExerciseRepository;
import una.force_gym.repository.RoutineExerciseRepository;
import una.force_gym.repository.RoutineRepository;
import una.force_gym.repository.UserRepository;

@Service
public class RoutineService {

    @Autowired
    private RoutineRepository routineRepository;

    @Autowired
    private RoutineExerciseRepository routineExerciseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DifficultyRoutineRepository difficultyRoutineRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Transactional
    public Routine saveWithExercises(RoutineWithExercisesDTO dto) {
        // Validaciones iniciales
        if (dto == null) {
            throw new RuntimeException("Los datos de la rutina no pueden ser nulos");
        }

        if (dto.getExercises() == null || dto.getExercises().isEmpty()) {
            throw new RuntimeException("Debe incluir al menos un ejercicio");
        }

        // Crear y configurar la rutina principal
        final Routine routine = new Routine();
        routine.setName(dto.getName());
        routine.setDate(dto.getDate());
        routine.setIsDeleted(0L);
        // Añadir estos valores
        routine.setCreatedByUser(dto.getIdUser()); // O el ID del usuario logueado
        routine.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        // Obtener y asignar el usuario
        User user = userRepository.findById(dto.getIdUser())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUser()));
        routine.setUser(user);

        // Obtener y asignar la dificultad
        DifficultyRoutine difficulty = difficultyRoutineRepository.findById(dto.getDifficultyRoutine().getIdDifficultyRoutine())
                .orElseThrow(() -> new RuntimeException("Dificultad no encontrada con ID: " + dto.getDifficultyRoutine().getIdDifficultyRoutine()));
        routine.setDifficultyRoutine(difficulty);

        // Guardar la rutina principal primero
        Routine savedRoutine = routineRepository.save(routine);

        // Procesar los ejercicios
        List<RoutineExercise> exercises = dto.getExercises().stream()
                .map(exDto -> {
                    // Validar series y repeticiones
                    if (exDto.getSeries() == null || exDto.getSeries() < 1) {
                        throw new RuntimeException("Las series deben ser al menos 1");
                    }
                    if (exDto.getRepetitions() == null || exDto.getRepetitions() < 1) {
                        throw new RuntimeException("Las repeticiones deben ser al menos 1");
                    }
                    if (exDto.getIdExercise() == null || exDto.getIdExercise().isEmpty()) {
                        throw new RuntimeException("Debe especificar al menos un ejercicio");
                    }

                    RoutineExercise routineExercise = new RoutineExercise();
                    routineExercise.setRoutine(savedRoutine);

                    Exercise exercise = exerciseRepository.findById(exDto.getIdExercise().get(0))
                            .orElseThrow(() -> new RuntimeException("Ejercicio no encontrado con ID: " + exDto.getIdExercise().get(0)));
                    routineExercise.setExercise(exercise);

                    routineExercise.setSeries(exDto.getSeries());
                    routineExercise.setRepetitions(exDto.getRepetitions());

                    return routineExercise;
                })
                .collect(Collectors.toList());

        // Guardar todos los ejercicios
        routineExerciseRepository.saveAll(exercises);

        return savedRoutine;
    }

    @Transactional
    public Routine updateWithExercises(RoutineWithExercisesDTO dto
    ) {
        final Routine routine = routineRepository.findById(dto.getIdRoutine())
                .orElseThrow(() -> new RuntimeException("Rutina no encontrada con ID: " + dto.getIdRoutine()));

        routine.setName(dto.getName());
        routine.setDate(dto.getDate());

        if (!routine.getUser().getIdUser().equals(dto.getIdUser())) {
            User user = userRepository.findById(dto.getIdUser())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUser()));
            routine.setUser(user);
        }

        if (!routine.getDifficultyRoutine().getIdDifficultyRoutine().equals(dto.getDifficultyRoutine().getIdDifficultyRoutine())) {
            DifficultyRoutine difficulty = difficultyRoutineRepository.findById(dto.getDifficultyRoutine().getIdDifficultyRoutine())
                    .orElseThrow(() -> new RuntimeException("Dificultad no encontrada con ID: " + dto.getDifficultyRoutine().getIdDifficultyRoutine()));
            routine.setDifficultyRoutine(difficulty);
        }

        Routine updatedRoutine = routineRepository.save(routine);

        // Eliminar ejercicios existentes
        routineExerciseRepository.deleteByRoutineId(updatedRoutine.getIdRoutine());

        // Guardar los nuevos ejercicios
        List<RoutineExercise> exercises = dto.getExercises().stream()
                .map(exDto -> {
                    RoutineExercise routineExercise = new RoutineExercise();
                    routineExercise.setRoutine(updatedRoutine);

                    Exercise exercise = exerciseRepository.findById(exDto.getIdExercise().get(0))
                            .orElseThrow(() -> new RuntimeException("Ejercicio no encontrado con ID: " + exDto.getIdExercise().get(0)));
                    routineExercise.setExercise(exercise);

                    routineExercise.setSeries(exDto.getSeries());
                    routineExercise.setRepetitions(exDto.getRepetitions());
                    return routineExercise;
                })
                .collect(Collectors.toList());

        routineExerciseRepository.saveAll(exercises);

        return updatedRoutine;
    }

    public List<Routine> getRoutines() {
        return routineRepository.getRoutine();
    }

    @Transactional(readOnly = true)
    public List<RoutineWithExercisesDTO> getRoutinesWithExercises() {
        List<Routine> routines = routineRepository.findAll();

        return routines.stream()
                .filter(routine -> routine.getIsDeleted() == 0) // Filtra rutinas no eliminadas
                .map(routine -> {
                    RoutineWithExercisesDTO dto = new RoutineWithExercisesDTO();

                    // Mapeo de campos básicos
                    dto.setIdRoutine(routine.getIdRoutine());
                    dto.setName(routine.getName());
                    dto.setDate(routine.getDate());
                    dto.setIsDeleted(routine.getIsDeleted());

                    // Mapeo del usuario
                    if (routine.getUser() != null) {
                        dto.setIdUser(routine.getUser().getIdUser());
                    }

                    // Mapeo de la dificultad
                    if (routine.getDifficultyRoutine() != null) {
                        dto.setDifficultyRoutine(routine.getDifficultyRoutine());
                    }

                    // Mapeo de ejercicios
                    List<RoutineExercise> exercises = routineExerciseRepository.findByRoutine_IdRoutine(routine.getIdRoutine());
                    List<RoutineExerciseDTO> exerciseDtos = exercises.stream()
                            .map(ex -> {
                                RoutineExerciseDTO exDto = new RoutineExerciseDTO();
                                exDto.setIdExercise(List.of(ex.getExercise().getIdExercise()));
                                exDto.setSeries(ex.getSeries());
                                exDto.setRepetitions(ex.getRepetitions());
                                return exDto;
                            })
                            .collect(Collectors.toList());

                    dto.setExercises(exerciseDtos);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RoutineWithExercisesDTO> getRoutinesWithExercisesDto() {
        List<Routine> routines = routineRepository.findAll();

        return routines.stream().map(routine -> {
            RoutineWithExercisesDTO dto = new RoutineWithExercisesDTO();
            dto.setIdRoutine(routine.getIdRoutine());
            dto.setName(routine.getName());
            dto.setDate(routine.getDate());
            dto.setIdUser(routine.getUser().getIdUser());
            dto.setDifficultyRoutine(routine.getDifficultyRoutine());
            dto.setIsDeleted(routine.getIsDeleted());

            List<RoutineExercise> exercises = routineExerciseRepository.findByRoutine_IdRoutine(routine.getIdRoutine());

            List<RoutineExerciseDTO> exerciseDtos = exercises.stream()
                    .map(ex -> {
                        RoutineExerciseDTO exDto = new RoutineExerciseDTO();
                        exDto.setIdExercise(List.of(ex.getExercise().getIdExercise()));
                        exDto.setSeries(ex.getSeries());
                        exDto.setRepetitions(ex.getRepetitions());
                        return exDto;
                    })
                    .collect(Collectors.toList());

            dto.setExercises(exerciseDtos);
            return dto;
        }).collect(Collectors.toList());
    }

    @Transactional
    public void deleteWithExercises(Long id) {
        // Verificar que la rutina existe primero
        if (!routineRepository.existsById(id)) {
            throw new RuntimeException("Rutina no encontrada con ID: " + id);
        }

        // Eliminar ejercicios asociados primero
        routineExerciseRepository.deleteByRoutineId(id);

        // Luego eliminar la rutina
        routineRepository.deleteById(id);
    }
}
