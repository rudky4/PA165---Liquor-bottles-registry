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
    private String email;
    private String password;
    private PersonRole role;
    private StoreDTO store;
    private ManufacturerDTO manufacturer;
    private LaboratoryDTO laboratory;



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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public StoreDTO getStore() {
        return store;
    }

    public void setStore(StoreDTO store) {
        this.store = store;
    }

    public ManufacturerDTO getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerDTO manufacturer) {
        this.manufacturer = manufacturer;
    }

    public LaboratoryDTO getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(LaboratoryDTO laboratory) {
        this.laboratory = laboratory;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof PersonDTO)) {
            return false;
        }
        PersonDTO p = (PersonDTO)obj;
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
        result += 21 * (name != null ? name.hashCode() : 0);
        result += 11 * (email != null ? email.hashCode() : 0);
        result += (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", store=" + store +
                ", manufacturer=" + manufacturer +
                ", laboratory=" + laboratory +
                '}';
    }
}
