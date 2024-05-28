package singh.sameer.gliderz.run;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface RunRepository extends ListCrudRepository<Run, Integer> {

//    If a custom method is required then
//    @Query("select * from run where location= :location")   custom query also possible
    List<Run> findAllByLocation(String location);
}

/*
public class RunRepository {

    private static final Logger log = LoggerFactory.getLogger(RunRepository.class);
    private List<Run> runs = new ArrayList<>();

    List<Run> findAll() {
        return runs;
    }

    public Optional<Run> findById(Integer id) {
        return Optional.ofNullable(runs.stream()
                .filter(run -> run.id().equals(id))
                .findFirst()
                .orElseThrow(RunNotFoundException::new));
    }

    void create(Run run) {
        runs.add(run);
    }

    void update(Run run, Integer id) {
        Optional<Run> existingRun = findById(id);

        if (existingRun.isPresent()) {
            runs.set(runs.indexOf(existingRun.get()), run);
        }
        else {
            log.error("Run with id {} not found", id);
            throw new RunNotFoundException();
        }
    }

    void delete(Integer id) {
        runs.stream().findAny().filter(run -> run.id().equals(id)).orElseThrow(RunNotFoundException::new);
        runs.removeIf(run -> run.id().equals(id));
    }


    @PostConstruct
    private void init() {
        runs.add(new Run(1, "Monday Morning Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 5, Location.OUTDOOR, null));
        runs.add(new Run(2, "Wednesday Evening Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 10, Location.INDOOR, null));
    }
}*/
