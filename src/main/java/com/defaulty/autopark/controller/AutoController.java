package com.defaulty.autopark.controller;

import com.defaulty.autopark.model.data.Auto;
import com.defaulty.autopark.model.data.AutoPersonnel;
import com.defaulty.autopark.service.auto.AutoService;
import com.defaulty.autopark.service.personnel.AutoPersonnelService;
import com.defaulty.autopark.service.security.SecurityServiceImpl;
import com.defaulty.autopark.validator.AutoValidator;
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

@Controller
public class AutoController {

    @Autowired
    private AutoService autoService;

    @Autowired
    private AutoPersonnelService autoPersonnelService;

    @Autowired
    private AutoValidator autoValidator;

    private static final Logger logger = LoggerFactory.getLogger(AutoController.class);

    @RequestMapping(value = "/autos", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("editForm", new Auto());
        model.addAttribute("itemList", this.autoService.list());
        model.addAttribute("personnelList", this.autoPersonnelService.list());

        return "tables/autos";
    }

    @RequestMapping(value = "autos/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("editForm") Auto editForm, BindingResult bindingResult, Model model) {

        model.addAttribute("itemList", this.autoService.list());
        model.addAttribute("personnelList", this.autoPersonnelService.list());

        autoValidator.validate(editForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "tables/autos";
        }

        editForm.setPersonnel_id(autoPersonnelService.convert(editForm.getPersonnel_str()));

        if (editForm.getId() == null) {
            this.autoService.add(editForm);
        } else {
            this.autoService.update(editForm);
        }

        return "redirect:/autos";
    }

    @RequestMapping("/autos/remove/{id}")
    public String remove(@PathVariable("id") int id) {
        this.autoService.remove(id);

        return "redirect:/autos";
    }

    @RequestMapping("/autos/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {

        model.addAttribute("editForm", this.autoService.getById(id));
        model.addAttribute("itemList", this.autoService.list());
        model.addAttribute("personnelList", this.autoPersonnelService.list());

        return "/tables/autos";
    }

}
