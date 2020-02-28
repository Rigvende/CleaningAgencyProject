package by.patrusova.project.entity.impl;

import by.patrusova.project.entity.AbstractEntity;
import java.util.Objects;

public class ComplexClient extends AbstractEntity {

    private User user;
    private Client client;

    public ComplexClient(User user, Client client) {
        this.user = user;
        this.client = client;
    }

    public User getUser() {
        return user;
    }
    public Client getClient() {
        return client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplexClient client1 = (ComplexClient) o;
        return user != null && (client != null
                && (user.equals(client1.user)
                && client.equals(client1.client)));
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((client == null) ? 0 : client.hashCode());
        return result;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("ComplexClient{");
        builder.append("user ID=").append(user.getId())
                .append(", client ID=").append(client.getId()).append('}');
        return builder.toString();
    }
}