package com.example.pawfect_project.Services.impl;

import com.example.pawfect_project.Entity.User;
import com.example.pawfect_project.Pojo.UserPojo;
import com.example.pawfect_project.Repo.AdoptionRepo;
import com.example.pawfect_project.Repo.FavoriteRepo;
import com.example.pawfect_project.Services.UserServices;
import com.example.pawfect_project.config.PasswordEncoderUtil;
import com.example.pawfect_project.exception.AppException;
import com.example.pawfect_project.Repo.UserRepo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServices {
    private final UserRepo userRepo;
    private final AdoptionRepo adoptionRepo;
    private final FavoriteRepo favoriteRepo;
    private final ThreadPoolTaskExecutor taskExecutor;
    @Override
    public UserPojo save(UserPojo userPojo) {
        User user;
        if (userPojo.getId() != null) {
            user = userRepo.findById(userPojo.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        } else {
            user = new User();
        }
        user.setEmail(userPojo.getEmail());
        user.setFullname(userPojo.getFullname());
        user.setAge(userPojo.getAge());
        user.setGender(userPojo.getGender());
        user.setPassword(PasswordEncoderUtil.getInstance().encode(userPojo.getPassword()));
        userRepo.save(user);
        return new UserPojo(user);
    }
    @Override
    public List<User> fetchAll() {
        return userRepo.findAll();
    }
    @Override
    public User findByEmail(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new AppException("Invalid User email", HttpStatus.BAD_REQUEST));
        return user;
    }

    @Override
    public User findBYId(Integer id) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        user = User.builder()
                .id(user.getId())
                .fullname(user.getFullname())
                .email(user.getEmail())
                .build();
        return user;
    }



    @Override
    public void deleteById(Integer id) {
        adoptionRepo.deleteById(id);
        favoriteRepo.deleteById(id);
        userRepo.deleteById(id);
    }



}

