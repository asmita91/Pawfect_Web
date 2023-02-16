package com.example.pawfect_project.Repo;

import com.example.pawfect_project.Entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepo extends JpaRepository<Pet,Integer> {
    @Query(value = "SELECT * FROM pet ORDER BY RANDOM() LIMIT 3", nativeQuery = true)
    List<Pet> getThreeRandomData();
}
