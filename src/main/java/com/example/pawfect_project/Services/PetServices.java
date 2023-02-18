package com.example.pawfect_project.Services;

import com.example.pawfect_project.Entity.Pet;
import com.example.pawfect_project.Pojo.PetPojo;

import java.util.*;
import java.io.IOException;

public interface PetServices {
    PetPojo save(PetPojo petPojo) throws IOException;
    List<Pet> findAll();
    List<Pet> getThreeRandomData();
    List<Pet> getPetsByCategory(String catrgory);
    Pet findById(Integer id);

    void deleteById(Integer id);
}

