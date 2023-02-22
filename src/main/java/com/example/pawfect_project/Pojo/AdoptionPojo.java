package com.example.pawfect_project.Pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.example.pawfect_project.Entity.Adoption;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdoptionPojo {

    @Positive(message = "ID must be a positive number.")
    private Integer id;

    @NotNull(message = "Pet ID cannot be null.")
    @Positive(message = "Pet ID must be a positive number.")
    private int pet_id;

    @NotNull(message = "User ID cannot be null.")
    @Positive(message = "User ID must be a positive number.")
    private int user_id;

    public AdoptionPojo(Adoption adoption) {
        this.id=adoption.getId();
        this.pet_id=adoption.getPet_id().getId();
        this.user_id=adoption.getUser_id().getId();
    }
}
