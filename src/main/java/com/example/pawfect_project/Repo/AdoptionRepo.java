package com.example.pawfect_project.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptionRepo extends JpaRepository<com.example.pawfect_project.Entity.Adoption,Integer> {
    @Query(value = "SELECT * FROM adoptions where user_id=?1", nativeQuery = true)
    List<com.example.pawfect_project.Entity.Adoption> findAdoptionById(Integer id);

    @Query(value = "DELETE from adoptions where user_id=?1", nativeQuery = true)
    Integer deleteByUser(Integer id);
}
