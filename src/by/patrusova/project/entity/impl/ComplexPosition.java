package by.patrusova.project.entity.impl;

import by.patrusova.project.entity.AbstractEntity;
import java.util.Objects;

public class ComplexPosition extends AbstractEntity {

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
}