package cz.muni.fi.pa165.dto;

import cz.muni.fi.pa165.enums.PersonRole;

/**
 * @author Jakub Fiser
 * @date 14/11/2016
 */
public class PersonDTO {

    private long id;
    private String name;
    private String login;
    private String password;
    private PersonRole role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public PersonRole getRole() {
        return role;
    }

    public void setRole(PersonRole role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        // TODO: implement
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // TODO: implement
        return super.equals(obj);
    }
}
