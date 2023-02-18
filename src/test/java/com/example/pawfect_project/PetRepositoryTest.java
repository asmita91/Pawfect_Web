package com.example.pawfect_project;


import com.example.pawfect_project.Entity.Pet;
import com.example.pawfect_project.Entity.User;
import com.example.pawfect_project.Repo.PetRepo;
import com.example.pawfect_project.Repo.UserRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PetRepositoryTest {
    @Autowired
    private PetRepo petRepo;

    @Test
    @Order(1)
    @Rollback(value=false)
    public void savePetTest(){
        Pet pet= Pet.builder()
                .petname("Saggy")
                .breed("idk")
                .catrgory("dog")
                .color("Black")
                .description("this is a dog")
                .image("xyz")
                .build();

        petRepo.save(pet);

        Assertions.assertThat(pet.getId()).isGreaterThan(0);
    }
    @Test
    @Order(2)
    public void getPetTest(){
        Pet petCreated=petRepo.findById(1).get();
        Assertions.assertThat(petCreated.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    public void getListOfPetTest(){
        List<Pet> Pets=petRepo.findAll();
        Assertions.assertThat(Pets.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value=false)
    public void updatePetTest(){
        Pet pet=petRepo.findById(1).get();
        pet.setPetname("Fluffy");
        Pet petUpdated=petRepo.save(pet);
        Assertions.assertThat(petUpdated.getPetname()).isEqualTo("Saggy");
    }
}
