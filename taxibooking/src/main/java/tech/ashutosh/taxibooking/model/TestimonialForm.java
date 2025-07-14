package tech.ashutosh.taxibooking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TestimonialForm")
public class TestimonialForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "name can't be empty")
    @NotBlank(message = "name can't be blank")
    @Size(min=2, max=30, message = "Name is too long")
    @Pattern(regexp = "^[A-Za-z]+$", message = "name must contain only alphabet")
    @Column(length=50)
    private String name;

    @NotEmpty(message = "comment can't be empty")
    @NotBlank(message = "comment can't be blank")
    @Size(min=2, max=2000, message = "comment is too long")
    private String comment;

}
