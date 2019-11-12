package com.taokaixiang.ssm.demo.v1.dao.repository;

import com.taokaixiang.ssm.demo.v1.dao.entity.Person;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository extends MainRepository<Person, Long>{

    @Override
    protected Class<Person> getType() {
        return Person.class;
    }
}
