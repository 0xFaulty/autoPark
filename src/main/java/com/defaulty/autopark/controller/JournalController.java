package com.defaulty.autopark.controller;

import com.defaulty.autopark.model.data.Auto;
import com.defaulty.autopark.model.data.AutoPersonnel;
import com.defaulty.autopark.model.data.Journal;
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

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping(value = "/journal", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("editForm", new Journal());
        model.addAttribute("itemList", this.journalService.list());
        model.addAttribute("autoList", this.autoService.list());
        model.addAttribute("routeList", this.routeService.list());

        return "tables/journal";
    }

    @RequestMapping(value = "journal/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("editForm") Journal editForm, BindingResult bindingResult, Model model) {

        model.addAttribute("itemList", this.journalService.list());
        model.addAttribute("autoList", this.autoService.list());
        model.addAttribute("routeList", this.routeService.list());

        journalValidator.validate(editForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "tables/journal";
        }

        editForm.setAuto_id(autoService.convert(editForm.getAuto_str()));
        editForm.setRoute_id(routeService.convert(editForm.getRoute_str()));

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

        model.addAttribute("editForm", this.journalService.getById(id));
        model.addAttribute("itemList", this.journalService.list());
        model.addAttribute("autoList", this.autoService.list());
        model.addAttribute("routeList", this.routeService.list());

        return "/tables/journal";
    }


}
