package com.nikita.springMVC.DAO;

import com.nikita.springMVC.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person VALUES(1,?,?,?)", person.getAge(), person.getName(),person.getEmail());
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }

    public void update(int id, Person uptadatePerson) {
        jdbcTemplate.update("UPDATE person SET name=?, age=?, email=? WHERE id=?", uptadatePerson.getName(),
                uptadatePerson.getAge(), uptadatePerson.getEmail(), id);
    }
}
