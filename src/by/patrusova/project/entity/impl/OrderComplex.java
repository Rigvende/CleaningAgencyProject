package by.patrusova.project.entity.impl;

public class OrderComplex {
    private User user;
    private Cleaner cleaner;
    private Client client;
    private Order order;

    public OrderComplex(User user, Cleaner cleaner, Client client, Order order){
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
}
