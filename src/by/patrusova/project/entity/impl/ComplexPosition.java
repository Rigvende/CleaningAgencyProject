package by.patrusova.project.entity.impl;

import by.patrusova.project.entity.AbstractEntity;

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
}
