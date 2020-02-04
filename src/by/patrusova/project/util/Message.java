package by.patrusova.project.util;

public class Message {

    private Integer id;
    private String text = "";

    public Message() {
    }
    public Message(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Message [id=").append(id).append(", text=")
                .append(text).append("]");
        return builder.toString();
    }
}