package by.patrusova.project.entity.impl;

import by.patrusova.project.entity.AbstractEntity;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Class of entity-type for storing service's data
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class Service extends AbstractEntity implements Serializable, Cloneable {

    private static final long serialVersionUID = 6L;
    private long id;
    private String service;
    private BigDecimal cost;
    private BigDecimal sales;

    public Service() {}
    public Service(long id, String service, BigDecimal cost, BigDecimal sales) {
        this.id = id;
        this.service = service;
        this.cost = cost;
        this.sales = sales;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getService() {
        return service;
    }
    public void setService(String service) {
        this.service = service;
    }
    public BigDecimal getCost() {
        return cost;
    }
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
    public BigDecimal getSales() {
        return sales;
    }
    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Service services = (Service) o;
        return (id == services.id
                && cost != null && cost.equals(services.cost)
                && sales != null && sales.equals(services.sales)
                && service != null && service.equals(services.service));
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cost == null) ? 0 : cost.hashCode());
        result = (int)(prime * result + id);
        result = prime * result + ((sales == null) ? 0 : sales.hashCode());
        result = prime * result + ((service == null) ? 0 : service.hashCode());
        return result;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Service{");
        builder.append("id=").append(id).append(", service='")
                .append(service).append('\'').append(", cost=")
                .append(cost).append(", discount=").append(sales).append('}');
        return builder.toString();
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}