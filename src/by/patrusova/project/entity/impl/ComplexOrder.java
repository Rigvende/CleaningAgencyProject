package by.patrusova.project.entity.impl;

import by.patrusova.project.entity.AbstractEntity;
import java.util.Objects;

public class ComplexOrder extends AbstractEntity {

    private User user;
    private Cleaner cleaner;
    private Client client;
    private Order order;

    public ComplexOrder(User user, Cleaner cleaner, Client client, Order order){
        this.user = user;
        this.cleaner = cleaner;
        this.client = client;
        this.order = order;
    }

    public User getUser() {
        return user;
    }
    public Cleaner getCleaner() {
        return cleaner;
    }
    public Client getClient() {
        return client;
    }
    public Order getOrder() {
        return order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ComplexOrder order1 = (ComplexOrder) o;
        return user != null && (cleaner != null && (client != null
                && (order != null && (user.equals(order1.user)
                && cleaner.equals(order1.cleaner)
                && client.equals(order1.client)
                && order.equals(order1.order)))));
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((cleaner == null) ? 0 : cleaner.hashCode());
        result = prime * result + ((client == null) ? 0 : client.hashCode());
        result = prime * result + ((order == null) ? 0 : order.hashCode());
        return result;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("ComplexOrder{");
        builder.append("user ID=").append(user.getId())
                .append("cleaner ID=").append(cleaner.getId())
                .append("client ID=").append(client.getId())
                .append(", order ID=").append(order.getId()).append('}');
        return builder.toString();
    }
}