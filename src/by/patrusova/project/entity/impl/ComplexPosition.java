package by.patrusova.project.entity.impl;

import by.patrusova.project.entity.AbstractEntity;
import java.io.Serializable;

/**
 * Class of entity-type for storing complex basket position's and service's data
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class ComplexPosition extends AbstractEntity implements Serializable, Cloneable {

    private static final long serialVersionUID = -4413736620201927845L;
    private BasketPosition position;
    private Service service;

    public ComplexPosition(BasketPosition position, Service service) {
        this.position = position;
        this.service = service;
    }

    public BasketPosition getPosition() {
        return position;
    }
    public Service getService() {
        return service;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ComplexPosition that = (ComplexPosition) o;
        return position != null && (service != null
                && (position.equals(that.position)
                && service.equals(that.service)));
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        result = prime * result + ((service == null) ? 0 : service.hashCode());
        return result;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("ComplexPosition{");
        builder.append("position ID=").append(position.getId())
                .append(", service ID=").append(service.getId()).append('}');
        return builder.toString();
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}