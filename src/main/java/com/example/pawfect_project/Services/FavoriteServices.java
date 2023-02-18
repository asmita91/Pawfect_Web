package com.example.pawfect_project.Services;

import com.example.pawfect_project.Pojo.FavoritePojo;

import java.util.List;

public interface FavoriteServices {
    FavoritePojo save(FavoritePojo favoritePojo);
    List<com.example.pawfect_project.Entity.Favorite> findFavoriteById(Integer id);
    List<com.example.pawfect_project.Entity.Favorite> findAll();
    com.example.pawfect_project.Entity.Favorite findById(Integer id);
    void deleteById(Integer id);

    boolean existsById(Integer id);
}
