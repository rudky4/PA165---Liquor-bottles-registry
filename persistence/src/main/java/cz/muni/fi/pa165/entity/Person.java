package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.enums.PersonRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Jakub Fiser
 *         24/10/2016
 */

@Entity
public class Person {

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
        if(role != null ? !role.equals(p.getRole()) : p.getRole() != null ||
                name != null ? !name.equals(p.getName()) : p.getName() != null ||
                password != null ? !password.equals(p.getPassword()) : p.getPassword() != null) {
            return false;
        }
        return (login != null ? login.equals(p.getLogin()) : p.getLogin() == null);
    }

    @Override
    public int hashCode() {
        int result = 31 * (login != null ? login.hashCode() : 0);
        result += 21 * (name != null ? name.hashCode() : 0);
        result += 11 * (password != null ? password.hashCode() : 0);
        result += (role != null ? role.hashCode() : 0);
        return result;
    }
}
