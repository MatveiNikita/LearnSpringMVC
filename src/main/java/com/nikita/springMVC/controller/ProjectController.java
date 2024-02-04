package com.nikita.springMVC.controller;

import com.nikita.springMVC.DAO.PersonDAO;
import com.nikita.springMVC.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String create(@ModelAttribute("person") Person person){
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
    public String update(@ModelAttribute("person") Person person,
                         @PathVariable("id") int id){

        personDAO.update(id, person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }

}
