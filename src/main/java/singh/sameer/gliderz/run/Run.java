package singh.sameer.gliderz.run;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public record Run(
        @Id // refers to type Integer in ListCrudRepository in interface RunRepository
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        Integer miles,
        Location location,
        @Version
        Integer version // required by data jdbc to track whether new or existing row
) {
    //    Data Validation Checks
    public Run {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (startedOn == null) {
            throw new IllegalArgumentException("Started on is required");
        }
        if (miles == null || miles < 1) {
            throw new IllegalArgumentException("Miles must be greater than 0");
        }
        if (location == null) {
            throw new IllegalArgumentException("Location is required");
        }
        if (completedOn != null && completedOn.isBefore(startedOn)) {
            throw new IllegalArgumentException("Completed on must be after started on");
        }
    }
}
