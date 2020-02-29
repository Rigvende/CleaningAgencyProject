package by.patrusova.project.entity.impl;

import by.patrusova.project.entity.AbstractEntity;

/**
 * Class of entity-type for storing user's data
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class User extends AbstractEntity {

    private static final long serialVersionUID = 1L;
    private long id;
    private String login;
    private String password;
    private String role;
    private String name;
    private String lastname;
    private long phone;
    private String address;
    private String email;

    public User() {}
    public User(long id, String login, String password, String role, String name,
                String lastname, long phone, String address, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role.toLowerCase();
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public long getPhone() {
        return phone;
    }
    public void setPhone(long phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return (id == user.id
                && phone == user.phone
                && login != null && login.equals(user.login)
                && password != null && password.equals(user.password)
                && role != null && role.equals(user.role)
                && name != null && name.equals(user.name)
                && lastname != null && lastname.equals(user.lastname)
                && address != null && address.equals(user.address)
                && email != null && email.equals(user.email));
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = (int)(prime * result + id + phone);
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("User{");
        builder.append("id=").append(id).append(", name='").append(name)
                .append('\'').append(", lastname='").append(lastname)
                .append('\'').append(", role='").append(role).append('\'')
                .append(", phone=").append(phone).append(", address='")
                .append(address).append('\'').append(", email='")
                .append(email).append('\'').append('}');
        return builder.toString();
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}