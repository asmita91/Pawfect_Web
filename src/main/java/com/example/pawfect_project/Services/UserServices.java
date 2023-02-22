package com.example.pawfect_project.Services;

import com.example.pawfect_project.Entity.User;
import com.example.pawfect_project.Pojo.UserPojo;

import java.util.List;

public interface UserServices {
    UserPojo save (UserPojo userPojo);
    User findByEmail(String email);
    User findBYId(Integer id);
    List<User> fetchAll();
    void deleteById(Integer id);
}
