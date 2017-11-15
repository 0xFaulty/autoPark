package com.defaulty.autopark.controller;

import com.defaulty.autopark.model.Journal;
import com.defaulty.autopark.service.auto.AutoService;
import com.defaulty.autopark.service.journal.JournalService;
import com.defaulty.autopark.service.routes.RouteService;
import com.defaulty.autopark.validator.JournalValidator;
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
public class JournalController {

    @Autowired
    private JournalService journalService;

    @Autowired
    private AutoService autoService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private JournalValidator journalValidator;

    @Autowired
    private HttpServletRequest httpServletRequest;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping(value = "/journal", method = RequestMethod.GET)
    public String list(Model model) {
        if (httpServletRequest.isUserInRole("ROLE_ADMIN")) {
            model.addAttribute("editActive", true);
            model.addAttribute("editForm", new Journal());
        } else
            model.addAttribute("editActive", false);

        model.addAttribute("itemList", this.journalService.list());
        model.addAttribute("autoList", this.autoService.list());
        model.addAttribute("routeList", this.routeService.list());

        return "tables/journal";
    }

    @RequestMapping(value = "journal/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("editForm") Journal editForm, BindingResult bindingResult, Model model) {

        if (!httpServletRequest.isUserInRole("ROLE_ADMIN"))
            return "redirect:/tables/autos";

        model.addAttribute("itemList", this.journalService.list());
        model.addAttribute("autoList", this.autoService.list());
        model.addAttribute("routeList", this.routeService.list());

        journalValidator.validate(editForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "tables/journal";
        }

        editForm.setAuto(autoService.convert(editForm.getAuto_str()));
        editForm.setRoute(routeService.convert(editForm.getRoute_str()));

        if (editForm.getId() == null) {
            this.journalService.add(editForm);
        } else {
            this.journalService.update(editForm);
        }

        return "redirect:/journal";
    }

    @RequestMapping("/journal/remove/{id}")
    public String remove(@PathVariable("id") int id) {
        this.journalService.remove(id);

        return "redirect:/journal";
    }

    @RequestMapping("/journal/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        if (httpServletRequest.isUserInRole("ROLE_ADMIN")) {
            model.addAttribute("editActive", true);
            model.addAttribute("editForm", this.journalService.getById(id));
        } else
            model.addAttribute("editActive", false);

        model.addAttribute("itemList", this.journalService.list());
        model.addAttribute("autoList", this.autoService.list());
        model.addAttribute("routeList", this.routeService.list());

        return "/tables/journal";
    }


}
