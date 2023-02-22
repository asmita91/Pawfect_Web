package com.example.pawfect_project.Services.impl;

import com.example.pawfect_project.Entity.Adoption;
import com.example.pawfect_project.Pojo.AdoptionPojo;
import com.example.pawfect_project.Repo.PetRepo;
import com.example.pawfect_project.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AdoptionServiceImpl implements com.example.pawfect_project.Services.AdoptionServices {
    private  final com.example.pawfect_project.Repo.AdoptionRepo adoptionRepo;
    private  final UserRepo userRepo;
    private  final PetRepo petRepo;
    @Override
    public AdoptionPojo save(AdoptionPojo adoptionPojo) {
        Adoption adoption=new Adoption();
        if(adoptionPojo.getId()!=null){
            adoption.setId(adoption.getId());
        }
        adoption.setUser_id(userRepo.findById(adoptionPojo.getUser_id()).orElseThrow());
        adoption.setPet_id(petRepo.findById(adoptionPojo.getPet_id()).orElseThrow());
        adoptionRepo.save(adoption);
        return new AdoptionPojo(adoption);
    }
    @Override
    public List<com.example.pawfect_project.Entity.Adoption> findAdoptionById(Integer id) {
        return findAllinList(adoptionRepo.findAdoptionById(id));
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
    public List<com.example.pawfect_project.Entity.Adoption> findAll() {
        return findAllinList(adoptionRepo.findAll());
    }
    public List<com.example.pawfect_project.Entity.Adoption> findAllinList(List<com.example.pawfect_project.Entity.Adoption> list){

        Stream<com.example.pawfect_project.Entity.Adoption> allJobsWithImage = list.stream().map(pet ->
                com.example.pawfect_project.Entity.Adoption.builder()
                        .id(pet.getId())
                        .user_id(pet.getUser_id())
                        .pet_id(pet.getPet_id())
                        .build()
        );
        list = allJobsWithImage.toList();
        return list;
    }




    @Override
    public com.example.pawfect_project.Entity.Adoption findById(Integer id) {
        com.example.pawfect_project.Entity.Adoption pet=adoptionRepo.findById(id).orElseThrow(()-> new RuntimeException("not found"));
        pet= com.example.pawfect_project.Entity.Adoption.builder()
                .id(pet.getId())
                .user_id(pet.getUser_id())
                .pet_id(pet.getPet_id())
                .build();
        return pet;
    }

    @Override
    public void deleteById(Integer id) {
        adoptionRepo.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return false;
    }
}
