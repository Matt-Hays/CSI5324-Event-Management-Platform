package domain;

import domain.validation.AgeValidation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@AgeValidation(message = "Minimum age must be 18 or above is age restricted is set.")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Version
    private Long version;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime lastModifiedAt;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private LocalDate dateHeld;

    @NotNull
    @Column(nullable = false)
    private LocalDate dateRegistrationBegins;

    @NotBlank
    @Column(nullable = false)
    private String description;

    @NotNull
    @Min(20)
    @Max(200)
    @Column(nullable = false)
    private Long capacity;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime startTime;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime endTime;

    @NotNull
    @Column(nullable = false)
    private Boolean ageRestricted;

    @NotNull
    @Min(0)
    @Max(24)
    @Column(nullable = false)
    private Integer minimumAge = 0;

    @ManyToOne
    @NotNull
    @Column(nullable = false)
    private Location location;

    @ManyToOne
    @NotNull
    @Column(nullable = false)
    private Performer performer;
}
