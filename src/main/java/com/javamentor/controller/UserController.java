package com.javamentor.controller;

import com.javamentor.entity.User;
import com.javamentor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/allUsers")
    public String displayAllUser(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        return "allUsers";
    }

    @GetMapping(value = "/addUser")
    public String displayNewUserForm(Model model) {
        model.addAttribute("headerMessage", "Введите данные пользователя");
        model.addAttribute("user", new User());
        return "addUser";
    }


    @GetMapping(value = "/editUser/{id}")
    public String displayEditUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("headerMessage", "Редактирование пользователя");
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping(value = "/deleteUser/{id}")
    public String deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/allUsers";
    }

    @PostMapping(value = "/editUser/{id}")
    public String saveEditedUser(@ModelAttribute User user) {
        boolean isUpdated = userService.updateUser(user);
        if (!isUpdated) {
            return "error";
        }
        return "redirect:/allUsers";
    }

    @PostMapping(value = "/addUser")
    public String saveNewUser(@ModelAttribute User user) {
        boolean isAdded = userService.saveUser(user);
        if (!isAdded) {
            return "error";
        }
        return "redirect:/allUsers";
    }

}
