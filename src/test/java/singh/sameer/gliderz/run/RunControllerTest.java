package singh.sameer.gliderz.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RunController.class)
class RunControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    RunRepository repository;

    private final List<Run> runs = new ArrayList<>();

    @BeforeEach
    void setup(){
        runs.add(new Run(1, "location1", LocalDateTime.now(), LocalDateTime.now().plusMinutes(20), 30, Location.OUTDOOR, null));
        runs.add(new Run(2, "location2", LocalDateTime.now(), LocalDateTime.now().plusMinutes(20), 20, Location.INDOOR, null));
        runs.add(new Run(3, "location3", LocalDateTime.now(), LocalDateTime.now().plusMinutes(20), 50, Location.OUTDOOR, null));
    }

    @Test
    void shouldFindAllRuns() throws Exception {
        when(repository.findAll()).thenReturn(runs);
        mvc.perform(MockMvcRequestBuilders.get("/api/runs"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.size()", is(runs.size())));
    }

    @Test
    void shouldFindRunById() throws Exception {
        Run run = new Run(1, "location1", LocalDateTime.now(), LocalDateTime.now().plusMinutes(20), 30, Location.OUTDOOR, null);
        when(repository.findById(eq(1))).thenReturn(Optional.of(run));

        mvc.perform(get("/api/runs/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotFindRunByNonExistentId() throws Exception {
        when(repository.findById(eq(999))).thenReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders.get("/api/runs/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateRun() throws Exception {
        Run run = new Run(1, "location1", LocalDateTime.now(), LocalDateTime.now().plusMinutes(20), 30, Location.OUTDOOR, null);
        when(repository.save(any(Run.class))).thenReturn(run);

        mvc.perform(post("/api/runs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(run)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateRun() throws Exception {
        Run run = new Run(1, "location1", LocalDateTime.now(), LocalDateTime.now().plusMinutes(20), 30, Location.OUTDOOR, null);
        when(repository.save(any(Run.class))).thenReturn(run);

        mvc.perform(MockMvcRequestBuilders.put("/api/runs/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(run)))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldDeleteRun() throws Exception {
//        Mockito.doNothing().when(repository).delete(any(Run.class));

        mvc.perform(delete("/api/runs/1"))
                .andExpect(status().isNoContent());
    }
}