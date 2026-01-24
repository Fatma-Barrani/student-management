package tn.esprit.studentmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDepartment;
    private String name;
    private String location;
    private String phone;
    private String head; // chef de département

    public Department(Long id, String name) {
        this.idDepartment = id;
        this.name = name;
    }
    @OneToMany(mappedBy = "department")
    private List<Student> students;
}
