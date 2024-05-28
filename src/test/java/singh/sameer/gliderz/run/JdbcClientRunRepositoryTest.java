package singh.sameer.gliderz.run;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@JdbcTest   // This annotation is used to test the JdbcClientRunRepository class with limited configuration, so will throw repository not found exception that is why next annotation is used
@Import(JdbcClientRunRepository.class) // This annotation is used to import the JdbcClientRunRepository class to the test
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // This annotation is used to replace the default auto-configuration of the test (in-memory) database with the specified (docker postgresql or local postgresql) configuration
class JdbcClientRunRepositoryTest {

    @Autowired
    JdbcClientRunRepository repository;

    @BeforeEach
    void setup(){
        repository.create(new Run(1, "Morning Run", LocalDateTime.now(), LocalDateTime.now().plusMinutes(10), 5, Location.OUTDOOR, null));
        repository.create(new Run(2, "Evening Run", LocalDateTime.now(), LocalDateTime.now().plusMinutes(7), 4, Location.INDOOR, null));
    }

    /*@AfterEach
    void tearDown() {
        repository.deleteAll();
    }*/

    @Test
    void shouldFindRunById() {
        Optional<Run> run = repository.findById(1);
        assertNotNull(run, "Run with id 1 should be present");
        assertEquals("Morning Run", run.get().title(), "Run name should be 'Morning Run'");
    }

    @Test
    void shouldNotFindRunByNonExistentId() {
        Optional<Run> run = repository.findById(999);
        assertNull(run, "Run with id 999 should not be present");
    }

    @Test
    void shouldCreateRun() {
        Run newRun = new Run(3, "Afternoon Run", LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), 7, Location.OUTDOOR, null);
        repository.create(newRun);
        Optional<Run> run = repository.findById(3);
        assertNotNull(run, "Newly created run should be present");
        assertEquals("Afternoon Run", run.get().title(), "Run name should be 'Afternoon Run'");
    }

    @Test
    void shouldUpdateRun() {
        Run updatedRun = new Run(1, "Updated Run", LocalDateTime.now(), LocalDateTime.now().plusMinutes(20), 8, Location.INDOOR, null);
        repository.update(updatedRun, 1);
        Optional<Run> run = repository.findById(1);
        assertNotNull(run, "Updated run should be present");
        assertEquals("Updated Run", run.get().title(), "Run name should be 'Updated Run'");
    }

    @Test
    void shouldDeleteRun() {
        repository.delete(1);
        Optional<Run> run = repository.findById(1);
        assertNull(run, "Run with id 1 should not be present after deletion");
    }
}