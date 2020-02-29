package by.patrusova.project.entity.impl;

import by.patrusova.project.entity.AbstractEntity;
import java.time.LocalDate;

/**
 * Class of entity-type for storing order's data
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class Order extends AbstractEntity {

    private static final long serialVersionUID = 5L;
    private long id;
    private LocalDate orderTime;
    private LocalDate deadline;
    private String orderStatus;
    private int mark;
    private long idClient;
    private long idCleaner;

    public enum Status {
        NEW("new"),
        REGISTERED("registered"),
        IN_PROCESS("in process"),
        DONE("done"),
        DECLINED("declined");
        private String value;
        Status(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    public Order() {}
    public Order(long id, LocalDate orderTime, LocalDate deadline, String orderStatus,
                 int mark, long idClient, long idCleaner) {
        this.id = id;
        this.orderTime = orderTime;
        this.deadline = deadline;
        this.orderStatus = orderStatus.toLowerCase();
        this.mark = mark;
        this.idClient = idClient;
        this.idCleaner = idCleaner;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public LocalDate getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(LocalDate orderTime) {
        this.orderTime = orderTime;
    }
    public LocalDate getDeadline() {
        return deadline;
    }
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
    public String getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    public int getMark() {
        return mark;
    }
    public void setMark(int mark) {
        this.mark = mark;
    }
    public long getIdClient() {
        return idClient;
    }
    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }
    public long getIdCleaner() {
        return idCleaner;
    }
    public void setIdCleaner(long idCleaner) {
        this.idCleaner = idCleaner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return (id == order.id
                && idClient == order.idClient
                && idCleaner == order.idCleaner
                && mark == order.mark
                && orderStatus != null && orderStatus.equals(order.orderStatus)
                && orderTime != null && orderTime.equals(order.orderTime)
                && deadline != null && deadline.equals(order.deadline));
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
        result = (int)(prime * result + id + idCleaner + idClient + mark);
        result = prime * result + ((orderTime == null) ? 0 : orderTime.hashCode());
        result = prime * result + ((deadline == null) ? 0 : deadline.hashCode());
        return result;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Order{");
        builder.append("id=").append(id).append(", orderTime=").append(orderTime)
                .append(", orderStatus=").append(orderStatus).append(", mark=")
                .append(mark).append(", idClient=").append(idClient)
                .append(", idCleaner=").append(idCleaner).append('}');
        return builder.toString();
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}