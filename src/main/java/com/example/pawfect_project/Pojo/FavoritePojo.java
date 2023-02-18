package com.example.pawfect_project.Pojo;

import com.example.pawfect_project.Entity.Favorite;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FavoritePojo {
    private Integer id;
    private  int pet_id;
    private  int user_id;

    public FavoritePojo(Favorite favorite) {
        this.id=favorite.getId();
        this.pet_id=favorite.getPet_id().getId();
        this.user_id=favorite.getUser_id().getId();
    }
}
