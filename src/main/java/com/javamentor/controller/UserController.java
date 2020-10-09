package com.javamentor.controller;

import com.javamentor.entity.User;
import com.javamentor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/allUsers")
    public ModelAndView displayAllUser() {
        ModelAndView mv = new ModelAndView();
        List<User> userList = userService.getAllUsers();
        mv.addObject("userList", userList);
        mv.setViewName("allUsers");
        return mv;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public ModelAndView displayNewUserForm() {
        ModelAndView mv = new ModelAndView("addUser");
        mv.addObject("headerMessage", "Введите данные пользователя");
        mv.addObject("user", new User());
        return mv;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String saveNewUser(@ModelAttribute User user) {
        return saveUser(user);
    }

    @RequestMapping(value = "/editUser/{id}", method = RequestMethod.GET)
    public ModelAndView displayEditUserForm(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("/editUser");
       User user = userService.getUserById(id);
       mv.addObject("headerMessage", "Редактирование пользователя");
       mv.addObject("user", user);
       return mv;
    }

    @RequestMapping(value = "/editUser/{id}", method = RequestMethod.POST)
    public String saveEditedUser(@ModelAttribute User user) {
    return saveUser(user);
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.POST)
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
