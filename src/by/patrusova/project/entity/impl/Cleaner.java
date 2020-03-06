package by.patrusova.project.entity.impl;

import by.patrusova.project.entity.AbstractEntity;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Class of entity-type for storing cleaner's data
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class Cleaner extends AbstractEntity implements Serializable, Cloneable {

    private static final long serialVersionUID = 7160632879057780533L;
    private long id;
    private long idUser;
    private BigDecimal commission;
    private String notes;

    public Cleaner() {}
    public Cleaner(long id, BigDecimal commission, String notes, long idUser) {
        this.id = id;
        this.commission = commission;
        this.notes = notes;
        this.idUser = idUser;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public BigDecimal getCommission() {
        return commission;
    }
    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public long getIdUser() {
        return idUser;
    }
    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cleaner cleaner = (Cleaner) o;
        return (id == cleaner.id
                && idUser == cleaner.idUser
                && (notes != null && notes.equals(cleaner.notes))
                && (commission != null && commission.equals(cleaner.commission)));
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((commission == null) ? 0 : commission.hashCode());
        result = (int)(prime * result + id + idUser);
        result = prime * result + ((notes == null) ? 0 : notes.hashCode());
        return result;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Cleaner{");
        builder.append("id=").append(id).append(", commission=")
                .append(commission).append(", notes='").append(notes)
                .append('\'').append(", idUser=").append(idUser).append('}');
        return builder.toString();
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}