package by.patrusova.project.entity.impl;

import by.patrusova.project.entity.AbstractEntity;
import java.util.Date;
import java.util.Objects;

public class Order extends AbstractEntity {

    private static final long serialVersionUID = 5L;
    private long id;
    private Date orderTime;
    private Date deadline;
    private String orderStatus;
    private int mark;
    private long idClient;
    private long idCleaner;

    public enum Status {
        REGISTERED("registered"),
        DONE("done"),
        IN_PROCESS("in process"),
        DECLINED("declined"),
        EXTENDED("extended");
        private String value;
        Status(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    public Order() {}
    public Order(long id, Date orderTime, Date deadline, String orderStatus,
                 int mark, long idClient, long idCleaner) {
        this.id = id;
        this.orderTime = orderTime;
        this.deadline = deadline;
        this.orderStatus = orderStatus.toLowerCase();
        this.mark = mark;
        this.idClient = idClient;
        this.idCleaner = idCleaner;
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
    public Date getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
    public Date getDeadline() {
        return deadline;
    }
    public void setDeadline(Date deadline) {
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