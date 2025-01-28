package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Todo {

    private String task;
    private LocalDate dueDay;
    private boolean completed;

    public Todo(String task) {
        this.task = task;
        this.dueDay = LocalDate.parse(LocalDate.now().plusDays(10).format(DateTimeFormatter.ISO_LOCAL_DATE));
        completed = false;
    }

    public Todo(String task, LocalDate dueDay) {
        this.task = task;
        this.dueDay = dueDay;
        completed = false;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public LocalDate getDueDay() {
        return dueDay;
    }

    public void setDueDay(LocalDate dueDay) {
        this.dueDay = dueDay;
    }

    protected void setToCompleted(){
        completed = true;
    }

    @Override
    public String toString() {
        return "Task: " + task + ", Due Day: " + dueDay;
    }
}
