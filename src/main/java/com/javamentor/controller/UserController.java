package com.javamentor.controller;

import com.javamentor.entity.User;
import com.javamentor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    public UserController() {
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView hello() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home");
        return mv;
    }

    @RequestMapping(value = "/allUsers", method = {RequestMethod.GET, RequestMethod.POST})
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
        mv.addObject("headerMessage", "Add User Details");
        mv.addObject("user", new User());
        return mv;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ModelAndView saveNewUser(@ModelAttribute User user, BindingResult result) {
        return getModelAndView(user, result);
    }

    private ModelAndView getModelAndView(@ModelAttribute User user, BindingResult result) {
        ModelAndView mv = new ModelAndView("redirect:/allUsers");

        if (result.hasErrors()) {
            return new ModelAndView("error");
        }
        boolean isAdded = userService.saveUser(user);
        if (!isAdded) {
            return new ModelAndView("error");
        }
        return mv;
    }

    @RequestMapping(value = "/editUser/{id}", method = RequestMethod.GET)
    public ModelAndView displayEditUserForm(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("/editUser");
        User user = userService.getUserById(id);
        mv.addObject("headerMessage", "Edit User Details");
        mv.addObject("user", user);
        return mv;
    }

    @RequestMapping(value = "/editUser/{id}", method = RequestMethod.POST)
    public ModelAndView saveEditedUser(@ModelAttribute User user, BindingResult result) {
        return getModelAndView(user, result);
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ModelAndView("redirect:/allUsers");

    }

}
