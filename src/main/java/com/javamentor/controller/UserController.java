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
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


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

    @PostMapping(value = "/addUser")
    public String saveNewUser(@ModelAttribute User user) {
        return saveUser(user);
    }

    @GetMapping(value = "/editUser/{id}")
    public String displayEditUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("headerMessage", "Редактирование пользователя");
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping(value = "/editUser/{id}")
    public String saveEditedUser(@ModelAttribute User user) {
        return saveUser(user);
    }

    @PostMapping(value = "/deleteUser/{id}")
    public String deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/allUsers";

    }

    private String saveUser(@ModelAttribute User user) {
        boolean isAdded = userService.saveUser(user);
        if (!isAdded) {
            return "error";
        }
        return "redirect:/allUsers";
    }

}
