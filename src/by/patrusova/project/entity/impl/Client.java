package by.patrusova.project.entity.impl;

import by.patrusova.project.entity.AbstractEntity;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Class of entity-type for storing client's data
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class Client extends AbstractEntity implements Serializable, Cloneable {

    private static final long serialVersionUID = 4648287772613080698L;
    private long id;
    private long idUser;
    private BigDecimal discount;
    private String location;
    private String relative;
    private String notes;

    public Client() {}
    public Client(long id, long idUser, BigDecimal discount,
                  String location, String relative, String notes) {
        this.id = id;
        this.idUser = idUser;
        this.discount = discount;
        this.location = location;
        this.relative = relative;
        this.notes = notes;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getIdUser() {
        return idUser;
    }
    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }
    public BigDecimal getDiscount() {
        return discount;
    }
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
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
        Client client = (Client) o;
        return (id == client.id
                && idUser == client.idUser
                && notes != null && notes.equals(client.notes)
                && discount != null && discount.equals(client.discount)
                && location != null && location.equals(client.location)
                && relative != null && relative.equals(client.relative));
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((discount == null) ? 0 : discount.hashCode());
        result = (int)(prime * result + id + idUser);
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((relative == null) ? 0 : relative.hashCode());
        result = prime * result + ((notes == null) ? 0 : notes.hashCode());
        return result;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Client{");
        builder.append("id=").append(id).append(", idUser=")
                .append(idUser).append(", discount=").append(discount)
                .append(", location='").append(location).append('\'')
                .append(", relative='").append(relative).append('\'')
                .append(", notes='").append(notes).append('\'').append('}');
        return builder.toString();
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}