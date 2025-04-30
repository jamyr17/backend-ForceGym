package una.force_gym.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import una.force_gym.domain.*;
import una.force_gym.dto.*;
import una.force_gym.repository.*;

@Service
public class RoutineService {

    @Autowired
    private RoutineRepository routineRepository;

    @Autowired
    private RoutineExerciseRepository routineExerciseRepository;

    @Autowired
    private RoutineAssignmentRepository routineAssignmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DifficultyRoutineRepository difficultyRoutineRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Transactional
    public RoutineWithExercisesDTO saveWithExercisesAndClients(RoutineWithExercisesDTO dto) {
        validateRoutineDTO(dto);

        if (routineRepository.existsByNameAndUser_IdUserAndIsDeleted(dto.getName(), dto.getIdUser(), 0L)) {
            throw new RuntimeException("Ya existe una rutina con el nombre '" + dto.getName() + "' para este usuario");
        }

        Routine routine = createRoutineFromDTO(dto);
        Routine savedRoutine = routineRepository.save(routine);

        saveRoutineExercises(savedRoutine, dto.getExercises());

        if (dto.getAssignments() != null && !dto.getAssignments().isEmpty()) {
            saveRoutineAssignments(savedRoutine, dto.getAssignments());
        }

        return mapRoutineToDTO(savedRoutine);
    }

    @Transactional
    public RoutineWithExercisesDTO updateWithExercisesAndClients(RoutineWithExercisesDTO dto) {
        validateRoutineDTO(dto);

        Routine routine = routineRepository.findById(dto.getIdRoutine())
                .orElseThrow(() -> new RuntimeException("Rutina no encontrada con ID: " + dto.getIdRoutine()));

        if (routineRepository.existsByNameAndUser_IdUserAndIsDeletedAndIdRoutineNot(
                dto.getName(),
                dto.getIdUser(),
                0L,
                dto.getIdRoutine())) {
            throw new RuntimeException("Ya existe una rutina con el nombre '" + dto.getName() + "' para este usuario");
        }

        updateRoutineFromDTO(routine, dto);
        Routine updatedRoutine = routineRepository.save(routine);

        routineExerciseRepository.deleteByRoutineId(updatedRoutine.getIdRoutine());
        saveRoutineExercises(updatedRoutine, dto.getExercises());

        routineAssignmentRepository.deleteByRoutineId(updatedRoutine.getIdRoutine());
        if (dto.getAssignments() != null && !dto.getAssignments().isEmpty()) {
            saveRoutineAssignments(updatedRoutine, dto.getAssignments());
        }

        return mapRoutineToDTO(updatedRoutine);
    }

    @Transactional(readOnly = true)
    public List<RoutineWithExercisesDTO> getAllRoutinesWithDetails() {
        List<Routine> routines = routineRepository.findAll();

        return routines.stream()
                .filter(routine -> routine.getIsDeleted() == 0)
                .map(this::mapRoutineToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RoutineWithExercisesDTO getRoutineWithDetails(Long id) {
        Routine routine = routineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rutina no encontrada con ID: " + id));

        return mapRoutineToDTO(routine);
    }

    @Transactional
    public void deleteRoutineWithDependencies(Long id) {
        if (!routineRepository.existsById(id)) {
            throw new RuntimeException("Rutina no encontrada con ID: " + id);
        }

        routineExerciseRepository.deleteByRoutineId(id);
        routineAssignmentRepository.deleteByRoutineId(id);
        routineRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<RoutineWithExercisesDTO> getRoutinesByClient(Long clientId) {
        if (!clientRepository.existsById(clientId)) {
            throw new RuntimeException("Cliente no encontrado con ID: " + clientId);
        }

        return routineAssignmentRepository.findByClient_IdClient(clientId).stream()
                .map(RoutineAssignment::getRoutine)
                .filter(routine -> routine.getIsDeleted() == 0)
                .map(this::mapRoutineToDTO)
                .collect(Collectors.toList());
    }

    private void validateRoutineDTO(RoutineWithExercisesDTO dto) {
        if (dto == null) {
            throw new RuntimeException("Los datos de la rutina no pueden ser nulos");
        }

        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new RuntimeException("El nombre de la rutina no puede estar vacío");
        }

        if (dto.getExercises() == null || dto.getExercises().isEmpty()) {
            throw new RuntimeException("Debe incluir al menos un ejercicio");
        }

        if (dto.getIdUser() == null || !userRepository.existsById(dto.getIdUser())) {
            throw new RuntimeException("Usuario no válido");
        }
    }

    private Routine createRoutineFromDTO(RoutineWithExercisesDTO dto) {
        Routine routine = new Routine();
        routine.setName(dto.getName());
        routine.setDate(dto.getDate());
        routine.setIsDeleted(0L);
        routine.setCreatedByUser(dto.getIdUser());
        routine.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        User user = userRepository.findById(dto.getIdUser())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUser()));
        routine.setUser(user);

        DifficultyRoutine difficulty = difficultyRoutineRepository.findById(dto.getDifficultyRoutine().getIdDifficultyRoutine())
                .orElseThrow(() -> new RuntimeException("Dificultad no encontrada con ID: " + dto.getDifficultyRoutine().getIdDifficultyRoutine()));
        routine.setDifficultyRoutine(difficulty);

        return routine;
    }

    private void updateRoutineFromDTO(Routine routine, RoutineWithExercisesDTO dto) {
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
    }

    private void saveRoutineExercises(Routine routine, List<RoutineExerciseDTO> exerciseDTOs) {
        List<RoutineExercise> exercises = exerciseDTOs.stream()
                .map(exDto -> {
                    validateExerciseDTO(exDto);

                    RoutineExercise routineExercise = new RoutineExercise();
                    routineExercise.setRoutine(routine);

                    Exercise exercise = exerciseRepository.findById(exDto.getIdExercise())
                            .orElseThrow(() -> new RuntimeException("Ejercicio no encontrado con ID: " + exDto.getIdExercise()));
                    routineExercise.setExercise(exercise);

                    routineExercise.setSeries(exDto.getSeries());
                    routineExercise.setRepetitions(exDto.getRepetitions());

                    return routineExercise;
                })
                .collect(Collectors.toList());

        routineExerciseRepository.saveAll(exercises);
    }

    private void saveRoutineAssignments(Routine routine, List<RoutineAssignmentDTO> assignmentDTOs) {
        List<RoutineAssignment> assignments = assignmentDTOs.stream()
                .map(assignmentDto -> {
                    validateAssignmentDTO(assignmentDto);

                    RoutineAssignment assignment = new RoutineAssignment();
                    assignment.setRoutine(routine);
                    assignment.setClient(clientRepository.findById(assignmentDto.getIdClient())
                            .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + assignmentDto.getIdClient())));
                    assignment.setAssignmentDate(assignmentDto.getAssignmentDate() != null
                            ? assignmentDto.getAssignmentDate() : new Date());

                    return assignment;
                })
                .collect(Collectors.toList());

        routineAssignmentRepository.saveAll(assignments);
    }

    private void validateExerciseDTO(RoutineExerciseDTO exDto) {
        if (exDto.getSeries() == null || exDto.getSeries() < 1) {
            throw new RuntimeException("Las series deben ser al menos 1");
        }
        if (exDto.getRepetitions() == null || exDto.getRepetitions() < 1) {
            throw new RuntimeException("Las repeticiones deben ser al menos 1");
        }
        if (exDto.getIdExercise() == null) {
            throw new RuntimeException("Debe especificar un ejercicio válido");
        }
    }

    private void validateAssignmentDTO(RoutineAssignmentDTO assignmentDto) {
        if (assignmentDto.getIdClient() == null) {
            throw new RuntimeException("Debe especificar un cliente");
        }
    }

    private RoutineWithExercisesDTO mapRoutineToDTO(Routine routine) {
        RoutineWithExercisesDTO dto = new RoutineWithExercisesDTO();
        dto.setIdRoutine(routine.getIdRoutine());
        dto.setName(routine.getName());
        dto.setDate(routine.getDate());
        dto.setIdUser(routine.getUser().getIdUser());
        dto.setDifficultyRoutine(routine.getDifficultyRoutine());
        dto.setIsDeleted(routine.getIsDeleted());

        // Mapear ejercicios
        List<RoutineExerciseDTO> exerciseDTOs = routineExerciseRepository.findByRoutine_IdRoutine(routine.getIdRoutine())
                .stream()
                .map(ex -> {
                    RoutineExerciseDTO exDto = new RoutineExerciseDTO();
                    exDto.setIdExercise(ex.getExercise().getIdExercise());
                    exDto.setSeries(ex.getSeries());
                    exDto.setRepetitions(ex.getRepetitions());
                    return exDto;
                })
                .collect(Collectors.toList());
        dto.setExercises(exerciseDTOs);

        // Mapear asignaciones a clientes
        List<RoutineAssignmentDTO> assignmentDTOs = routineAssignmentRepository.findByRoutine_IdRoutine(routine.getIdRoutine())
                .stream()
                .map(assignment -> {
                    RoutineAssignmentDTO assignmentDto = new RoutineAssignmentDTO();
                    assignmentDto.setIdClient(assignment.getClient().getIdClient());
                    assignmentDto.setAssignmentDate(assignment.getAssignmentDate());
                    return assignmentDto;
                })
                .collect(Collectors.toList());
        dto.setAssignments(assignmentDTOs);

        return dto;
    }
}
