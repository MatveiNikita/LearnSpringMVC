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

        people.add(new Person("Nikita",++ID,21,"cmamk@cma.com"));
        people.add(new Person("ALex",++ID,19,"hhrr@mail.com"));
        people.add(new Person("Json", ++ID,25,"hro@vsv.com"));

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
    public void delete(int id){

        people.removeIf(e -> e.getId() == id);
    }

    public void update(int id, Person person) {
        Person personToUpdated = show(id);
        personToUpdated.setName(person.getName());

        personToUpdated.setEmail(person.getEmail());
        personToUpdated.setAge(person.getAge());
    }
}
