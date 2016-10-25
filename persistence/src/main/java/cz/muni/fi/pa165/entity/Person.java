package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.enums.PersonRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Jakub Fiser
 *         24/10/2016
 */
@NamedQueries({
        @NamedQuery(name=Person.FIND_ALL, query="SELECT p FROM Person p"),
        @NamedQuery(name=Person.FIND_BY_LOGIN, query="SELECT p FROM Person p WHERE p.login = :login"),
        @NamedQuery(name=Person.FIND_BY_ROLE, query="SELECT p FROM Person p WHERE p.role = :role")
})
@Entity
public class Person {

    public static final String FIND_ALL = "findAll";
    public static final String FIND_BY_LOGIN = "Person.findByLogin";
    public static final String FIND_BY_ROLE = "Person.findByRole";
    public static final String PARAMETER_LOGIN = "login";
    public static final String PARAMETER_ROLE = "role";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column(nullable = false)
    @NotNull
    private String name;

    @Enumerated
    private PersonRole role;

    @Column(nullable = false, unique = true)
    @NotNull
    private String login;

    @Column(nullable = false)
    @Size(min = 8)
    @NotNull
    private String password;

    public Person() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonRole getRole() {
        return role;
    }

    public void setRole(PersonRole role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Person)) {
            return false;
        }
        Person p = (Person)obj;
        return name.equals(p.getName()) && login.equals(p.getLogin());
    }

    @Override
    public int hashCode() {
        return login.hashCode() * 21 + name.hashCode();
    }
}
