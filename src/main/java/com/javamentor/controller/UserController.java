package com.javamentor.controller;

import com.javamentor.entity.Role;
import com.javamentor.entity.User;
import com.javamentor.repository.RoleRepository;
import com.javamentor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/allUsers")
    public String displayAllUser(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        return "allUsers";
    }

    @GetMapping(value = "login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/user")
    public String userPage() {
        return "user";
    }

    @GetMapping(value = "/addUser")
    public String displayNewUserForm(Model model) {
        model.addAttribute("headerMessage", "Введите данные пользователя");
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping(value = "/deleteUser")
    public String deleteUserById(@RequestParam("userid") Long id) {
        userService.deleteUserById(id);
        return "redirect:/allUsers";
    }

    @GetMapping(value = "/editUser/{id}")
    public String displayEditUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("headerMessage", "Редактирование пользователя");
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping(value = "/editUser/{id}")
    public String saveEditedUser(@ModelAttribute User user, @RequestParam("role") String[] role) {
        Set<Role> new_roles = new HashSet<>();
        user.setRoles(new_roles);
        userService.updateUser(user);
        if (Arrays.toString(role).contains("ROLE_ADMIN")) {
            new_roles.add(roleRepository.getById(2L));
        }
        if (Arrays.toString(role).contains("ROLE_USER")) {
            new_roles.add(roleRepository.getById(1L));
        }
        user.setRoles(new_roles);
        userService.updateUser(user);
        return "redirect:/allUsers";
    }

    @PostMapping(value = "/addUser")
    public String saveNewUser(@Valid User user, BindingResult bindingResult ) {
        if (bindingResult.hasErrors()) {
            return "addUser";
        }
        boolean isAdded = userService.saveUser(user);
        if (!isAdded) {
            return "error";
        }
        return "redirect:/allUsers";
    }
}
