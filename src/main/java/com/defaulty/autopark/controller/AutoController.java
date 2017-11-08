package com.defaulty.autopark.controller;

import com.defaulty.autopark.model.data.Auto;
import com.defaulty.autopark.service.auto.AutoService;
import com.defaulty.autopark.validator.AutoValidator;
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
    private AutoValidator autoValidator;

    @RequestMapping(value = "/autos", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("editForm", new Auto());
        model.addAttribute("itemList", this.autoService.list());

        return "tables/autos";
    }

    @RequestMapping(value = "autos/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("editForm") Auto editForm, BindingResult bindingResult, Model model) {

        model.addAttribute("itemList", this.autoService.list());

        autoValidator.validate(editForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "tables/autos";
        }

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

        return "/tables/autos";
    }

}
