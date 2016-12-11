package cz.muni.fi.pa165.entity;

import cz.muni.fi.pa165.enums.PersonRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @Pattern(regexp = "[\\p{L} ]+", message = "Invalid name.")
    @NotNull
    private String name;

    @Enumerated
    private PersonRole role;

    @Column(nullable = false, unique = true)
    @Size(min = 4)
    @NotNull
    private String login;

    @Column(nullable = false)
    @NotNull
    private String passwordHash;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message = "Invalid email.")
    private String email;

    @ManyToOne
    private Store store;

    @ManyToOne
    private Manufacturer manufacturer;

    @ManyToOne
    private Laboratory laboratory;

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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Laboratory getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(Laboratory laboratory) {
        this.laboratory = laboratory;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Person)) {
            return false;
        }
        Person p = (Person)obj;
        if(role != null ? !role.equals(p.getRole()) : p.getRole() != null ||
                name != null ? !name.equals(p.getName()) : p.getName() != null ||
                email != null ? !email.equals(p.getEmail()) : p.getEmail() != null) {
            return false;
        }
        return (login != null ? login.equals(p.getLogin()) : p.getLogin() == null);
    }

    @Override
    public int hashCode() {
        int result = 31 * (login != null ? login.hashCode() : 0);
        result += 19 * (name != null ? name.hashCode() : 0);
        result += 11 * (email != null ? email.hashCode() : 0);
        result += (role != null ? role.hashCode() : 0);
        return result;
    }
}
