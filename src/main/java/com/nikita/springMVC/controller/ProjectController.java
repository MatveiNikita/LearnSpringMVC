package com.nikita.springMVC.controller;

import com.nikita.springMVC.DAO.PersonDAO;
import com.nikita.springMVC.model.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class ProjectController {
    private final PersonDAO personDAO;
    @Autowired
    public ProjectController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", personDAO.index());
        return "people/index.html";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
        return "people/show.html";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/new.html";
    }
    @PostMapping()
    public String create(@ModelAttribute("person")@Valid Person person,
                         BindingResult bindingResult){
            if (bindingResult.hasErrors()){
                return "people/new.html";
            }
        personDAO.save(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model,
                       @PathVariable("id") int id){
        model.addAttribute("person", personDAO.show(id));
        return "people/edit.html";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id){
        if (bindingResult.hasErrors()){
            return "people/edit.html";
        }
        personDAO.update(id, person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }

}
