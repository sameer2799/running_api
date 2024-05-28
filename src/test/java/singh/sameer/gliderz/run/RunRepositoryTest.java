/*
package singh.sameer.gliderz.run;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RunRepositoryTest {


    RunRepository repository;

    @BeforeEach
    void setUp(){
        repository = new RunRepository();
        repository.create(new Run(1, "Monday Morning Run", LocalDateTime.now(), LocalDateTime.now().plusMinutes(8), 5, Location.OUTDOOR, null));
        repository.create(new Run(2, "Wednesday Evening Run", LocalDateTime.now(), LocalDateTime.now().plusMinutes(10), 6, Location.INDOOR, null));
    }

    @Test
    void shouldFindAllRuns(){
        List<Run> runs = repository.findAll();
        System.out.println(Collections.unmodifiableList(runs));
        assertEquals(2, runs.size(), "Should have returned 2 runs");
    }

    @Test
    void shouldFindRunById() {
        Optional<Run> run = repository.findById(1);
        assertTrue(run.isPresent(), "Run with id 1 should be present");
        assertEquals("Monday Morning Run", run.get().title(), "Run name should be 'Monday Morning Run'");
        assertEquals(5, run.get().miles(), "Run distance should be 5");
    }

    @Test
    void shouldNotFindRunByNonExistentId() {

        RunNotFoundException runNotFoundException = assertThrows(RunNotFoundException.class, () -> repository.findById(999).get());
        assertEquals("Run not found", runNotFoundException.getMessage(), "RunNotFoundException should be thrown");

    }

    @Test
    void shouldCreateRun() {
        Run newRun = new Run(3, "Friday Afternoon Run", LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), 7, Location.OUTDOOR, null);
        repository.create(newRun);
        Optional<Run> run = repository.findById(3);
        assertTrue(run.isPresent(), "Newly created run should be present");
        assertEquals("Friday Afternoon Run", run.get().title(), "Run name should be 'Friday Afternoon Run'");
    }

    @Test
    void shouldUpdateRun() {
        Run updatedRun = new Run(1, "Updated Run", LocalDateTime.now(), LocalDateTime.now().plusMinutes(20), 8, Location.INDOOR, null);
        repository.update(updatedRun, 1);
        Optional<Run> run = repository.findById(1);
        assertTrue(run.isPresent(), "Updated run should be present");
        assertEquals("Updated Run", run.get().title(), "Run name should be 'Updated Run'");
    }

    @Test
    void shouldNotUpdateNonExistentRun() {
        Run updatedRun = new Run(999, "Updated Run", LocalDateTime.now(), LocalDateTime.now().plusMinutes(20), 8, Location.INDOOR, null);
        RunNotFoundException run = assertThrows(RunNotFoundException.class,()-> repository.update(updatedRun, 999));
        assertEquals("Run not found", run.getMessage(), "RunNotFoundException should be thrown");

    }

    @Test
    void shouldDeleteRun() {
        repository.delete(1);
        RunNotFoundException run = assertThrows(RunNotFoundException.class,()-> repository.findById(1));
        assertEquals("Run not found", run.getMessage(), "RunNotFoundException should be thrown");

    }

    @Test
    void shouldNotDeleteNonExistentRun() {
        RunNotFoundException run = assertThrows(RunNotFoundException.class,()-> repository.delete(999));
        assertEquals("Run not found", run.getMessage(), "RunNotFoundException should be thrown");

    }
}
*/
