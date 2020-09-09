package com.herusantoso.registration.entities;

import com.herusantoso.registration.enums.Gender;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = -7369920601847524273L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mobile_number", length = 14, nullable = false, unique = true)
    private String mobileNumber;

    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

}
