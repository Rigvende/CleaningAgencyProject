package by.patrusova.project.entity.impl;

import by.patrusova.project.entity.AbstractEntity;
import java.util.Objects;

public class BasketPosition extends AbstractEntity {

    private static final long serialVersionUID = 4L;
    private long id;
    private long idOrder;
    private long idService;

    public BasketPosition() {}
    public BasketPosition(long id, long idOrder, long idService) {
        this.id = id;
        this.idOrder = idOrder;
        this.idService = idService;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getIdOrder() {
        return idOrder;
    }
    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }
    public long getIdService() {
        return idService;
    }
    public void setIdService(long idService) {
        this.idService = idService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BasketPosition basketPosition = (BasketPosition) o;
        return (id == basketPosition.id
                && idOrder == basketPosition.idOrder
                && idService == basketPosition.idService);
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        return (int)(prime * result + id + idOrder + idService);
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("BasketPosition{");
        builder.append("id=").append(id).append(", idOrder=").append(idOrder)
                .append(", idService=").append(idService).append('}');
        return builder.toString();
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}