package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.enums.PersonRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Jakub Fiser
 *         24/10/2016
 */
public interface PersonDAO extends CrudRepository<Person, Long> {


    Person findByLogin(@Param("login") String login);

    List<Person> findAll();

    List<Person> findByRole(@Param("role") PersonRole role);

    Person findOne(Long id);
}

