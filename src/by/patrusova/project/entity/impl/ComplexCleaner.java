package by.patrusova.project.entity.impl;

import by.patrusova.project.entity.AbstractEntity;
import java.io.Serializable;

/**
 * Class of entity-type for storing complex cleaner's and user's data
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class ComplexCleaner extends AbstractEntity implements Serializable, Cloneable {

    private static final long serialVersionUID = 5710191304935155658L;
    private User user;
    private Cleaner cleaner;

    public ComplexCleaner(User user, Cleaner cleaner) {
        this.user = user;
        this.cleaner = cleaner;
    }

    public User getUser() {
        return user;
    }
    public Cleaner getCleaner() {
        return cleaner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ComplexCleaner cleaner1 = (ComplexCleaner) o;
        return user != null && (cleaner != null
                && (user.equals(cleaner1.user)
                && cleaner.equals(cleaner1.cleaner)));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((cleaner == null) ? 0 : cleaner.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("ComplexCleaner{");
        builder.append("user ID=").append(user.getId())
                .append(", cleaner ID=").append(cleaner.getId()).append('}');
        return builder.toString();
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}