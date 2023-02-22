package com.example.pawfect_project;

import com.example.pawfect_project.Entity.User;
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
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTest {
    @Autowired
    private UserRepo userRepo;
    @Test
    @Order(1)
    @Rollback(value=false)
    public void saveUserTest(){
        User user= User.builder()
                .fullname("Asmita Katel")
                .email("asmita@gmail.com")
                .age(18)
                .gender("Female")
                .password("test123")
                .build();

        userRepo.save(user);

        Assertions.assertThat(user.getId()).isGreaterThan(0);
    }
    @Test
    @Order(2)
    public void getUserTest(){
        User userCreated=userRepo.findById(1).get();
        Assertions.assertThat(userCreated.getId()).isEqualTo(1);
    }
    @Test
    @Order(3)
    public void getListOfUserTest(){
        List<User> Users=userRepo.findAll();
        Assertions.assertThat(Users.size()).isGreaterThan(0);
    }
    @Test
    @Order(4)
    @Rollback(value=false)
    public void updateUserTest(){
        User user=userRepo.findById(1).get();
        user.setFullname("Asm Katel");
        User userUpdated=userRepo.save(user);
        Assertions.assertThat(userUpdated.getFullname()).isEqualTo("Asm Katel");

    }
    @Test
    @Order(5)
    @Rollback(value=false)
    public void deleteUserTest(){
        User user=userRepo.findById(1).get();
        userRepo.delete(user);
        User user1=null;
        Optional<User> optionalUser=userRepo.findUserByFullname("Asm Katel");
        if(optionalUser.isPresent()){
            user1=optionalUser.get();
        }
        Assertions.assertThat(user1).isNull();
    }
}
