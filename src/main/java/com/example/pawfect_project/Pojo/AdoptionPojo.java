package com.example.pawfect_project.Pojo;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdoptionPojo {
    private Integer id;
    private  int pet_id;
    private  int user_id;

    public AdoptionPojo(com.example.pawfect_project.Entity.Adoption adoption) {
        this.id=adoption.getId();
        this.pet_id=adoption.getPet_id().getId();
        this.user_id=adoption.getUser_id().getId();
    }
}
