package com.example.pawfect_project.Entity;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="pet")
public class Pet {
    @Id
    @SequenceGenerator(name = "jps_user_seq_gen", sequenceName = "jps_user_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "jps_user_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "petname")
    private String petname;

    @Column(name = "description")
    private String description;

    @Column(name = "breed")
    private String breed;

    @Column(name = "category")
    private String catrgory;

    @Column(name = "color")
    private String color;

    @Column(name = "image")
    private String image;

    @Transient
    private String imageBase64;

    @OneToMany(mappedBy = "pet_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Adoption> adoptions;

}
