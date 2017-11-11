package com.defaulty.autopark.controller;

import com.defaulty.autopark.model.user.User;
import com.defaulty.autopark.service.user.UserService;
import com.defaulty.autopark.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String itemList(Model model) {
        model.addAttribute("editForm", new User());
        model.addAttribute("itemList", this.userService.list());

        return "user/admin";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("editForm") User editForm, BindingResult bindingResult, Model model) {

        model.addAttribute("itemList", this.userService.list());

        userValidator.validateAttributes(editForm, bindingResult);

        if (editForm.getId() == null) {
            userValidator.validateUsername(editForm, bindingResult);
            userValidator.validateNewPassword(editForm, bindingResult);
        }

        if (bindingResult.hasErrors()) {
            return "user/admin";
        }

        if (editForm.getNewPassword().equals(""))
            editForm.setPassword(this.userService.findByUsername(editForm.getUsername()).getPassword());

        if (editForm.getId() == null) {
            this.userService.add(editForm);
        } else {
            this.userService.update(editForm);
        }

        return "redirect:/admin";
    }

    @RequestMapping("/admin/remove/{id}")
    public String removeUser(@PathVariable("id") int id) {
        this.userService.remove(id);

        return "redirect:/admin";
    }

    @RequestMapping("/admin/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        User user = this.userService.getById(id);
        if (user.isActive()) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            user.setBirthdayString(df.format(user.getBirthday()));
            model.addAttribute("editForm", user);
        } else
            model.addAttribute("editForm", new User());

        model.addAttribute("itemList", this.userService.list());

        return "user/admin";
    }

    @RequestMapping("/admin/addresses/{id}")
    public String addressUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("setAddresses", this.userService.getById(id).getAddresses().toArray());

        return "user/addresses";
    }

    @RequestMapping("/admin/activetoggle/{id}")
    public String activeUser(@PathVariable("id") int id, Model model) {
        this.userService.activateToggle(id);

        return "redirect:/admin";
    }

}
