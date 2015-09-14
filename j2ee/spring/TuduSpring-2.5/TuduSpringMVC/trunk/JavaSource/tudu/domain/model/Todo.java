package tudu.domain.model;

import java.io.Serializable;
import java.util.Date;

/**
 * A Todo.
 * 
 * @author Julien Dubois
 */
public class Todo implements Serializable, Comparable {

    /**
     * The serialVersionUID.
     */
    private static final long serialVersionUID = 4048798961366546485L;

    private String todoId;

    private TodoList todoList;
    
    private Date creationDate;

    private String description;

    private int priority;

    private boolean completed;

    private Date completionDate;
    
    private Date dueDate;

    public String getTodoId() {
        return todoId;
    }

    public void setTodoId(String todoId) {
        this.todoId = todoId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public TodoList getTodoList() {
        return todoList;
    }

    public void setTodoList(TodoList todoList) {
        this.todoList = todoList;
    }

    public int compareTo(Object o) {
        Todo that = (Todo) o;
        int order = that.getPriority() - this.getPriority();
        if (this.isCompleted()) {
            order += 10000;
        }
        if (that.isCompleted()) {
            order -= 10000;
        }
        if (order == 0) {
            order = (this.getDescription() + this.getTodoId()).compareTo(that
                    .getDescription()
                    + that.getTodoId());
        }
        return order;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Todo)) {
            return false;
        }

        final Todo todo = (Todo) o;

        if (todoId != null ? !todoId.equals(todo.todoId) : todo.todoId != null) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        return (todoId != null ? todoId.hashCode() : 0);
    }

}
