package tech.ashutosh.taxibooking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "bookingform")
public class BookingForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "name can't be empty")
    @NotBlank(message = "name can't be blank")
    @Size(min=2, max=30, message = "Name is too long")
    @Pattern(regexp = "^[A-Za-z]+$", message = "name must contain only alphabet")
    @Column(length=50)
    private String name;

    @NotEmpty(message = "source can't be empty")
    @NotBlank(message = "source can't be blank")
    @Size(min=2, max=30, message = "source is too long")
    @Column(length = 50)
    private String source;

    @NotEmpty(message = "email can't be empty")
    @NotBlank(message = "email can't be blank")
    @Size(min=8, max=30, message = "email is too long")
    @Column(length = 50)
    private String email;

    @NotEmpty(message = "destination can't be empty")
    @NotBlank(message = "destination can't be blank")
    @Size(min=2, max=30, message = "destination is too long")
    @Column(length = 50)
    private String destination;

    @NotNull(message = "time can't be empty")
    private LocalTime time;

    @NotNull(message = "time can't be empty")
    private LocalDate date;

    @NotEmpty(message = "comfort can't be empty")
    @Size(min=2, max=30, message = "destination is too long")
    private String comfort;

    @Min(value = 1, message = "There should be least 1 adult")
    @Max(value = 3, message = "Adult should be less than 4")
    private int adult;

    @Max(value = 3, message = "Children can be atmost 3")
    private int children;

    @NotEmpty(message = "message can't be empty")
    @NotBlank(message = "message can't be blank")
    @Size(min=2, max=2000, message = "message is too long")
    private String message;

}
