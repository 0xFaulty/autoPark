package com.defaulty.autopark.controller;

import com.defaulty.autopark.model.Route;
import com.defaulty.autopark.service.routes.RouteService;
import com.defaulty.autopark.validator.RouteValidator;
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
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private HttpServletRequest httpServletRequest;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping(value = "/routes", method = RequestMethod.GET)
    public String list(Model model) {
        if (httpServletRequest.isUserInRole("ROLE_ADMIN")) {
            model.addAttribute("editActive", true);
            model.addAttribute("editForm", new Route());
        } else
            model.addAttribute("editActive", false);

        model.addAttribute("itemList", this.routeService.list());

        return "tables/routes";
    }

    @RequestMapping(value = "routes/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("editForm") Route editForm, BindingResult bindingResult, Model model) {

        if (!httpServletRequest.isUserInRole("ROLE_ADMIN"))
            return "redirect:/tables/autos";

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
        if (httpServletRequest.isUserInRole("ROLE_ADMIN")) {
            model.addAttribute("editActive", true);
            model.addAttribute("editForm", this.routeService.getById(id));
        } else
            model.addAttribute("editActive", false);

        model.addAttribute("itemList", this.routeService.list());

        return "/tables/routes";
    }

}
