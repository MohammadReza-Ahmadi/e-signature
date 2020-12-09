package com.vsq.esignature.service;

import com.vsq.esignature.model.Person;
import com.vsq.esignature.repositorie.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * author: MohammadReza Ahmadi  11.08.20
 * email: m.reza79ahmadi@gmail.com
 */

@Service
public class PersonService {
    private final Logger log = Logger.getLogger(PersonService.class.getCanonicalName());
    private final PersonRepository personRepository;


    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person get(Long personId) {
        Optional<Person> person = personRepository.findById(personId);
        this.log.log(Level.ALL, "Person loaded by id: " + personId);
        return person.get();
    }

    public Person insert(Person person) {
        personRepository.save(person);
        this.log.log(Level.ALL, "Person persisted.");
        return person;
    }




}
