package add;

import by.patrusova.project.entity.AbstractEntity;
import java.util.Date;
import java.util.Objects;

public class ClientAdd extends AbstractEntity {

    private static final long serialVersionUID = 2L;
    private long id;
    private String name;
    private String surname;
    private Date birthday;
    private String address;
    private String email;
    private long phone;
    private String location;
    private String relative;
    private double discount;
    private String notes;

    public ClientAdd(long id, String name, String surname, long phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }
    public ClientAdd(long id, String name, String surname, Date birthday, String address, String email,
                  long phone, String location, String relative, double discount, String notes) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.relative = relative;
        this.discount = discount;
        this.notes = notes;
    }

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
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
    public long getPhone() {
        return phone;
    }
    public void setPhone(long phone) {
        this.phone = phone;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getRelative() {
        return relative;
    }
    public void setRelative(String relative) {
        this.relative = relative;
    }
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClientAdd client = (ClientAdd) o;
        return ((client.name != null && client.surname != null
                && client.address != null && client.email != null
                && client.location != null && client.relative != null) &&
                (id == client.id &&
                        phone == client.phone &&
                        Double.compare(client.discount, discount) == 0 &&
                        Objects.equals(name, client.name) &&
                        Objects.equals(surname, client.surname) &&
                        Objects.equals(address, client.address) &&
                        Objects.equals(email, client.email) &&
                        Objects.equals(location, client.location) &&
                        Objects.equals(relative, client.relative)));
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (int)(prime * result + id + phone + discount);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((relative == null) ? 0 : relative.hashCode());
        return result;
    }
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone=" + phone +
                '}';
    }
}
