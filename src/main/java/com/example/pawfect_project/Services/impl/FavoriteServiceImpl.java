package com.example.pawfect_project.Services.impl;

import com.example.pawfect_project.Entity.Favorite;
import com.example.pawfect_project.Pojo.FavoritePojo;
import com.example.pawfect_project.Repo.PetRepo;
import com.example.pawfect_project.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements com.example.pawfect_project.Services.FavoriteServices {
    private  final com.example.pawfect_project.Repo.FavoriteRepo favoriteRepo;
    private  final UserRepo userRepo;
    private  final PetRepo petRepo;

    @Override
    public FavoritePojo save(FavoritePojo favoritePojo) {
        Favorite favorite=new Favorite();
        if(favoritePojo.getId()!=null){
            favorite.setId(favorite.getId());
        }
        favorite.setUser_id(userRepo.findById(favoritePojo.getUser_id()).orElseThrow());
        favorite.setPet_id(petRepo.findById(favoritePojo.getPet_id()).orElseThrow());

        favoriteRepo.save(favorite);
        return new FavoritePojo(favorite);
    }

    @Override
    public List<com.example.pawfect_project.Entity.Favorite> findFavoriteById(Integer id) {
        return findAllinList(favoriteRepo.findFavoriteById(id));
    }


//    @Override
//    public Order findOrderById(Integer id) {
//            Order order=orderRepo.findById(id).orElseThrow(()-> new RuntimeException("not found"));
//            order=Order.builder()
//                    .id(order.getId())
//                    .quantity(order.getQuantity())
//                    .user_id(order.getUser_id())
//                    .product_id(order.getProduct_id())
//                    .address(order.getAddress())
//                    .build();
//            return order;
//    }


    @Override
    public List<com.example.pawfect_project.Entity.Favorite> findAll() {
        return findAllinList(favoriteRepo.findAll());
    }
    public List<com.example.pawfect_project.Entity.Favorite> findAllinList(List<com.example.pawfect_project.Entity.Favorite> list){

        Stream<com.example.pawfect_project.Entity.Favorite> allJobsWithImage = list.stream().map(pet ->
                com.example.pawfect_project.Entity.Favorite.builder()
                        .id(pet.getId())
                        .user_id(pet.getUser_id())
                        .pet_id(pet.getPet_id())
                        .build()
        );
        list = allJobsWithImage.toList();
        return list;
    }




    @Override
    public com.example.pawfect_project.Entity.Favorite findById(Integer id) {
        com.example.pawfect_project.Entity.Favorite pet=favoriteRepo.findById(id).orElseThrow(()-> new RuntimeException("not found"));
        pet= com.example.pawfect_project.Entity.Favorite.builder()
                .id(pet.getId())
                .user_id(pet.getUser_id())
                .pet_id(pet.getPet_id())
                .build();
        return pet;
    }

    @Override
    public void deleteById(Integer id) {
        favoriteRepo.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return false;
    }
}
