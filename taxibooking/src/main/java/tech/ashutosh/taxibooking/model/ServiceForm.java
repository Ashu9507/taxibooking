package tech.ashutosh.taxibooking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "service")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ServiceForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String image;

    @NotEmpty(message = "Title cannot be empty")
    @Size(min=2, max = 25, message = "Make title between 2 to 25 letters")
    private String title;


    @NotEmpty(message = "Description cannot be empty")
    @Size(min=2, max = 2100, message = "Make description between 2 to 2100 letters")
    private String description;

}
