package com.example.pawfect_project.Services;

import com.example.pawfect_project.Pojo.AdoptionPojo;

import java.util.List;

public interface AdoptionServices {
    AdoptionPojo save(AdoptionPojo adoptionPojo);
    List<com.example.pawfect_project.Entity.Adoption> findAdoptionById(Integer id);
    List<com.example.pawfect_project.Entity.Adoption> findAll();
    com.example.pawfect_project.Entity.Adoption findById(Integer id);
    void deleteById(Integer id);

    boolean existsById(Integer id);
}
