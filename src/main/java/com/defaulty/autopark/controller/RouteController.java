package com.defaulty.autopark.controller;

import com.defaulty.autopark.filter.RepairEncoding;
import com.defaulty.autopark.model.data.Route;
import com.defaulty.autopark.service.routes.RouteService;
import com.defaulty.autopark.validator.RouteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;

@Controller
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private RouteValidator routeValidator;

    @RequestMapping(value = "/routes", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("editForm", new Route());
        model.addAttribute("itemList", this.routeService.list());

        return "tables/routes";
    }

    @RequestMapping(value = "routes/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("editForm") Route editForm, BindingResult bindingResult, Model model) {

        model.addAttribute("itemList", this.routeService.list());

        routeValidator.validate(editForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "tables/routes";
        }

        if (editForm.getId() == null) {
            this.routeService.add(editForm);
        } else {
            this.routeService.update(editForm);
        }

        return "redirect:/routes";
    }

    @RequestMapping("/routes/remove/{id}")
    public String remove(@PathVariable("id") int id) {
        this.routeService.remove(id);

        return "redirect:/routes";
    }

    @RequestMapping("/routes/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {

        model.addAttribute("editForm", this.routeService.getById(id));
        model.addAttribute("itemList", this.routeService.list());

        return "/tables/routes";
    }


}
