package com.example.pawfect_project.controller;

import com.example.pawfect_project.Entity.Adoption;
import com.example.pawfect_project.Entity.Pet;
import com.example.pawfect_project.Entity.User;
import com.example.pawfect_project.Pojo.PetPojo;
import com.example.pawfect_project.Services.AdoptionServices;
import com.example.pawfect_project.Services.PetServices;
import com.example.pawfect_project.Services.UserServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private  final PetServices petServices;
    private final UserServices userServices;
    private  final AdoptionServices adoptionServices;

    @GetMapping("/addpet")
    public String getAddPetForm(Model model) {
        model.addAttribute("add",new PetPojo());
        return "Admin/AddPet";
    }

    @PostMapping("/savepet")
    public String createUser(@Valid PetPojo petPojo,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes
    ) throws IOException {

        Map<String, String> requestError = validateRequest(bindingResult);
        if (requestError != null) {
            redirectAttributes.addFlashAttribute("requestError", requestError);
            return "redirect:/admin/addpet";
        }
        petServices.save(petPojo);
        redirectAttributes.addFlashAttribute("successMsg", "Pet saved successfully");
        return "redirect:/admin/viewallpet";
    }

    @GetMapping("/viewallpet")
    public String getPetinList(Model model) {
        List<Pet> petList=petServices.findAll();
        model.addAttribute("alllist", petList);
//        return "Admin/AddPet";
        return "Admin/allPetList";
    }

    @GetMapping("/editpet/{id}")
    public String editMembers(@PathVariable("id") Integer id, Model model) {
        Pet pet = petServices.findById(id);
        model.addAttribute("add", new PetPojo(pet));
        return "Admin/AddPet";
    }

    @GetMapping("/deletepet/{id}")
    public String deleteMembers(@PathVariable("id") Integer id) {
        if(adoptionServices.existsById(id)) {
            adoptionServices.deleteById(id);
        }
        petServices.deleteById(id);
        return "redirect:/admin/viewallpet";
    }

    @GetMapping("/viewalladoption")
    public String getAdoptionList(Model model) {
        List<Adoption> adoptions=adoptionServices.findAll();
        model.addAttribute("alllist", adoptions);
        return "Admin/ViewAdoptions";
    }



    public Map<String, String> validateRequest(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return null;
        }
        Map<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;
    }

    @GetMapping("/list")
    public String showUserList(Model model) {
        List<User> users = userServices.fetchAll();
        model.addAttribute("userList", users);
        System.out.println(users);
        return "Admin/user_list";
    }


}
