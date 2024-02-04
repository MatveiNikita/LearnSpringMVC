package com.nikita.springMVC.DAO;

import com.nikita.springMVC.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int ID;
    List<Person> people;
    {
        people = new ArrayList<>();

        people.add(new Person("Nikita",++ID));
        people.add(new Person("ALex",++ID));
        people.add(new Person("Json", ++ID));

    }

    public List<Person> index(){
        return people;
    }

    public Person show(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++ID);
        people.add(person);
    }
}
