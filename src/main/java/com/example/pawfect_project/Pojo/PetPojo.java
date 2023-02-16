package com.example.pawfect_project.Pojo;
import com.example.pawfect_project.Entity.Pet;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PetPojo {
    private Integer id;
    private  String petname;
    private String description;
     private String breed;
     private String category;
     private String color;
     private MultipartFile image;

     public PetPojo(Pet pet){
       this.id=pet.getId();
       this.description=pet.getDescription();
       this.petname=pet.getPetname();
       this.breed=pet.getBreed();
       this.category=pet.getCatrgory();
       this.color=pet.getColor();
     }
}
