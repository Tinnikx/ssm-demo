package com.taokaixiang.ssm.demo.v1.service;

import com.taokaixiang.ssm.demo.v1.dao.entity.Person;
import com.taokaixiang.ssm.demo.v1.dao.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person getPerson() {
        return personRepository.findById(1L);
    }

    public Person createOrUpdate() {
        throw new NullPointerException();
//        Person person = new Person();
//        person.setGender("2");
//        person.setName("taokaixiang3");
//        personRepository.create(person);
//        return personRepository.findById(2L);
    }
}
