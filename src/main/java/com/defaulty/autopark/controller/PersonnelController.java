package com.defaulty.autopark.controller;

import com.defaulty.autopark.model.AutoPersonnel;
import com.defaulty.autopark.service.personnel.AutoPersonnelService;
import com.defaulty.autopark.validator.AutoPersonnelValidator;
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

import javax.servlet.http.HttpServletRequest;

@Controller
public class PersonnelController {

    @Autowired
    private AutoPersonnelService autoPersonnelService;

    @Autowired
    private AutoPersonnelValidator autoPersonnelValidator;

    @Autowired
    private HttpServletRequest httpServletRequest;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping(value = "/personnel", method = RequestMethod.GET)
    public String list(Model model) {
        if (httpServletRequest.isUserInRole("ROLE_ADMIN")) {
            model.addAttribute("editActive", true);
            model.addAttribute("editForm", new AutoPersonnel());
        } else
            model.addAttribute("editActive", false);

        model.addAttribute("itemList", this.autoPersonnelService.list());

        return "tables/personnel";
    }

    @RequestMapping(value = "personnel/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("editForm") AutoPersonnel editForm, BindingResult bindingResult, Model model) {

        if (!httpServletRequest.isUserInRole("ROLE_ADMIN"))
            return "redirect:/tables/autos";

        model.addAttribute("itemList", this.autoPersonnelService.list());

        autoPersonnelValidator.validate(editForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "tables/personnel";
        }

        if (editForm.getId() == null) {
            this.autoPersonnelService.add(editForm);
        } else {
            this.autoPersonnelService.update(editForm);
        }

        return "redirect:/personnel";
    }

    @RequestMapping("/personnel/remove/{id}")
    public String remove(@PathVariable("id") int id) {
        this.autoPersonnelService.remove(id);

        return "redirect:/personnel";
    }

    @RequestMapping("/personnel/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        if (httpServletRequest.isUserInRole("ROLE_ADMIN")) {
            model.addAttribute("editActive", true);
            model.addAttribute("editForm", this.autoPersonnelService.getById(id));
        } else
            model.addAttribute("editActive", false);

        model.addAttribute("itemList", this.autoPersonnelService.list());

        return "/tables/personnel";
    }

}
