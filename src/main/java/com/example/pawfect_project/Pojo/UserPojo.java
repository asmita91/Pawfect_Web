package com.example.pawfect_project.Pojo;
import com.example.pawfect_project.Entity.User;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPojo {
    private Integer id;
    private String email;
    private  String fullname;
    private  String password;
    private String gender;
    private int age;



    public UserPojo(User user) {
        this.id=user.getId();
        this.email=user.getEmail();
        this.fullname=user.getFullname();
        this.gender=user.getGender();
        this.age=user.getAge();
        this.password=user.getPassword();
    }
}
