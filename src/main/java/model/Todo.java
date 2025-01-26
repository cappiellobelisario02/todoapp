package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Todo {

    private final int id;
    private String title;
    private String description;

    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    public Todo(String title, String description) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Id: " + id + ",\nTitle: " + title + ",\nDescription: " + description;
    }
}
