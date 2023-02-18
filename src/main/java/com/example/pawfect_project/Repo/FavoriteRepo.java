package com.example.pawfect_project.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepo extends JpaRepository<com.example.pawfect_project.Entity.Favorite,Integer> {
    @Query(value = "SELECT * FROM favorites where user_id=?1", nativeQuery = true)
    List<com.example.pawfect_project.Entity.Favorite> findFavoriteById(Integer id);
}
