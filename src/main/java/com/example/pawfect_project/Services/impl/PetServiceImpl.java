package com.example.pawfect_project.Services.impl;

import com.example.pawfect_project.Entity.Pet;
import com.example.pawfect_project.Pojo.PetPojo;
import com.example.pawfect_project.Repo.PetRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class PetServiceImpl implements com.example.pawfect_project.Services.PetServices {
    private  final PetRepo petRepo;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/pet";

    @Override
    public PetPojo save(PetPojo petPojo) throws IOException {
        Pet pet;
        if (petPojo.getId() != null) {
            pet = petRepo.findById(petPojo.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        } else {
            pet = new Pet();
        }

        if(petPojo.getId()!=null){
            pet.setId(petPojo.getId());
        }
        pet.setDescription(petPojo.getDescription());
        pet.setPetname(petPojo.getPetname());
        pet.setBreed(petPojo.getBreed());
        pet.setColor(petPojo.getColor());
        pet.setCatrgory(petPojo.getCategory());

        if(petPojo.getImage()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, petPojo.getImage().getOriginalFilename());
            fileNames.append(petPojo.getImage().getOriginalFilename());
            Files.write(fileNameAndPath, petPojo.getImage().getBytes());

            pet.setImage(petPojo.getImage().getOriginalFilename());
        }

        petRepo.save(pet);
        return new PetPojo(pet);
    }

    @Override
    public List<Pet> findAll() {
        return findAllinList(petRepo.findAll());
    }

    @Override
    public List<Pet> getThreeRandomData() {
        return findAllinList(petRepo.getThreeRandomData());
    }


    @Override
    public Pet findById(Integer id) {
        Pet pet=petRepo.findById(id).orElseThrow(()-> new RuntimeException("not found"));
        pet=Pet.builder()
                .id(pet.getId())
                .petname(pet.getPetname())
                .breed(pet.getBreed())
                .catrgory(pet.getCatrgory())
                .color(pet.getColor())
                .description(pet.getDescription())
                .imageBase64(getImageBase64(pet.getImage()))
                .build();
        return pet;
    }

    @Override
    public void deleteById(Integer id) {
        petRepo.deleteById(id);
    }


    public List<Pet> findAllinList(List<Pet> list){

        Stream<Pet> allJobsWithImage = list.stream().map(pet ->
                Pet.builder()
                        .id(pet.getId())
                        .petname(pet.getPetname())
                        .breed(pet.getBreed())
                        .color(pet.getColor())
                        .description(pet.getDescription())
                        .imageBase64(getImageBase64(pet.getImage()))
                        .build()
        );
        list = allJobsWithImage.toList();
        return list;
    }


    public String getImageBase64(String fileName) {
        if (fileName!=null) {
            String filePath = System.getProperty("user.dir")+"/pet/";
            File file = new File(filePath + fileName);
            byte[] bytes;
            try {
                bytes = Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return Base64.getEncoder().encodeToString(bytes);
        }
        return null;
    }
}
