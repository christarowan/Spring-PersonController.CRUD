package io.zipcoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//Create a `PersonController` class with `Person createPerson(Person p)`, 
//`Person getPerson(int id)`, `List<Person> getPersonList()`, `Person updatePerson(Person p)`, and `void DeletePerson(int id)` methods, and let it track a list of Person objects.
@RestController
public class PersonController {

    @Autowired
    private PersonRepository peeps;

    // HashMap<Integer, Person> peeps = new HashMap<Integer, Person>();

    @PostMapping("/people")
    public ResponseEntity<Person> createPerson(@RequestBody Person p) {
        peeps.save(p);
        return new ResponseEntity<Person>(p, HttpStatus.CREATED);
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable int id) {

        return new ResponseEntity<Person>(peeps.findOne(id), HttpStatus.OK);
    }

    @GetMapping("/people")
    public ResponseEntity<List<Person>> getPersonList() {

        ArrayList<Person> peepList = new ArrayList<>();
        peeps.findAll().forEach(e -> peepList.add(e));
        return new ResponseEntity<List<Person>>(peepList, HttpStatus.OK);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person p) {
        peeps.save(p);
        return new ResponseEntity<Person>(p, HttpStatus.CREATED);
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable int id) {
        peeps.delete(peeps.findOne(id));
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
