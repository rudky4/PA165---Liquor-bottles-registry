package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.enums.PersonRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Jakub Fiser
 *         24/10/2016
 */
public interface PersonDAO extends CrudRepository<Person, Long> {

    Person findByLogin(@Param(Person.PARAMETER_LOGIN) String login);

    List<Person> findAll();

    List<Person> findByRole(@Param(Person.PARAMETER_ROLE) PersonRole role);

    Person findOne(Long id);
}

