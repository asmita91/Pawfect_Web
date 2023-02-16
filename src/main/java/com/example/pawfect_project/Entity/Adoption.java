package com.example.pawfect_project.Entity;

import com.example.pawfect_project.Entity.User;
import jakarta.persistence.*;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="adoptions")
public class Adoption {
    @Id
    @SequenceGenerator(name = "adopt_seq_gen", sequenceName = "adopt_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "adopt_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "id")
    private Pet pet_id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_id;


}

