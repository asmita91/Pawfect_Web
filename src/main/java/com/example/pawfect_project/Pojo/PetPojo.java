package com.example.pawfect_project.Pojo;

import com.example.pawfect_project.Entity.Pet;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PetPojo {
    private Integer id;

    @NotEmpty(message = "Petname can't be empty")
    private String petname;

    @NotEmpty(message = "Description can't be empty")
    private String description;

    @NotEmpty(message = "Breed can't be empty")
    private String breed;

    @NotEmpty(message = "Category can't be empty")
    private String category;

    @NotEmpty(message = "Color can't be empty")
    private String color;

    @NotNull(message = "Image can't be null")
    private MultipartFile image;

    public PetPojo(Pet pet){
        this.id = pet.getId();
        this.description = pet.getDescription();
        this.petname = pet.getPetname();
        this.breed = pet.getBreed();
        this.category = pet.getCatrgory();
        this.color = pet.getColor();
    }
}
