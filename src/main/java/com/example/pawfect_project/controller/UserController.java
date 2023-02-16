package com.example.pawfect_project.controller;

import com.example.pawfect_project.Entity.Pet;
import com.example.pawfect_project.Entity.User;
import com.example.pawfect_project.Pojo.AdoptionPojo;
import com.example.pawfect_project.Pojo.PetPojo;
import com.example.pawfect_project.Pojo.UserPojo;
import com.example.pawfect_project.Services.UserServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.text.AbstractDocument;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserServices userService;
    private final com.example.pawfect_project.Services.PetServices petServices;
    private final com.example.pawfect_project.Services.AdoptionServices adoptionServices;

    @GetMapping(value = {"/homepage"})
    public String getSetting(Model model ,Principal principal, Authentication authentication) {

        if (authentication!=null){
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                if (grantedAuthority.getAuthority().equals("Admin")) {
                    return "redirect:/admin/list";
                }
            }
        }
        List<Pet> petList=petServices.findAll();
        model.addAttribute("add", petList);
        List<Pet> pets = petServices.getThreeRandomData();
        model.addAttribute("petfetch", pets);
        model.addAttribute("userdata",userService.findByEmail(principal.getName()));

        return "index1";
    }

    @GetMapping("/signup")
    public String getSignupPage(Model model) {
        model.addAttribute("create", new UserPojo());
        return "signin";
    }

    @PostMapping("/saveuser")
    public String saveUser(@Valid UserPojo userPojo) {
        userService.save(userPojo);
        return "redirect:/signup";
    }


    @PostMapping("/saveadoption")
    public String getHire(@Valid AdoptionPojo adoptionPojo){
        adoptionServices.save(adoptionPojo);
        return "redirect:/user/homepage";
    }


    @GetMapping("/petinfo/{id}")
    public String GetmoreInfo(@PathVariable("id") Integer id , Model model, Principal principal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/user/signup";
        }
        Pet pet= petServices.findById(id);
        model.addAttribute("adoption", new AdoptionPojo());
        model.addAttribute("petinfo",new PetPojo(pet));
        model.addAttribute("petdata",pet);
        model.addAttribute("info",userService.findByEmail(principal.getName()));
        return "petDesc";
    }

    @GetMapping("/viewAllMyAdoptions/{id}")
    public String getAdoptioninList(@PathVariable("id") Integer id, Model model, Principal principal) {
        List<com.example.pawfect_project.Entity.Adoption> adoption=adoptionServices.findAdoptionById(id);
        model.addAttribute("adoptionList", adoption);
        model.addAttribute("userdata",userService.findByEmail(principal.getName()));

        User user = userService.findBYId(id);
        model.addAttribute("signup", new UserPojo(user));
        model.addAttribute("signups", user);
        return "profile";
    }


    @GetMapping("/delete/{id}")
    public String deleteAdoption(@PathVariable("id") Integer id) {
        adoptionServices.deleteById(id);
        return "redirect:/user/homepage";
    }


    @PostMapping("/updateprofile")
    public String updateRegister(@Valid UserPojo userPojo){
        userService.save(userPojo);
        return "redirect:/user/homepage";}


    @GetMapping("/sendEmail")
    public String sendRegistrationEmail() {
        this.userService.sendEmail();
        return "emailsuccess";
    }


}

