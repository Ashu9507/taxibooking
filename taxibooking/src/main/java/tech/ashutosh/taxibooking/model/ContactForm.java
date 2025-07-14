package tech.ashutosh.taxibooking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Table(name="ContactForm")
public class ContactForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min=2, max=30, message = "Invalid Name Size")
    @Column(length = 30)
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Size(min=3, max=50, message = "Invalid email Size")
    @Column(length = 50)
    private String email;

    @NotNull(message = "Phone number cannot be empty")
    @Min(value=1000000000, message = "Phone number must be at least 10 digits")
    @Max(value=9999999999L, message = "Phone number must be at exceed 10 digits")
    @Column(length = 10)
    private Long phone;

    @NotEmpty(message = "Message cannot be empty")
    @Size(min=2, max=10000, message = "Invalid message Size")
    @Column(length = 500)
    private String message;
}


