package tudu.jms;

import java.io.Serializable;
import java.util.Date;

public class TuduMessage implements Serializable {
	private String operationType;
	private String todoId;
    private Date todoCreationDate;
    private String todoDescription;
    private int todoPriority;
    private boolean todoCompleted;

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getTodoId() {
		return todoId;
	}

	public void setTodoId(String todoId) {
		this.todoId = todoId;
	}

	public boolean isTodoCompleted() {
		return todoCompleted;
	}

	public void setTodoCompleted(boolean completed) {
		this.todoCompleted = completed;
	}

	public Date getTodoCreationDate() {
		return todoCreationDate;
	}

	public void setTodoCreationDate(Date creationDate) {
		this.todoCreationDate = creationDate;
	}

	public String getTodoDescription() {
		return todoDescription;
	}

	public void setTodoDescription(String description) {
		this.todoDescription = description;
	}

	public int getTodoPriority() {
		return todoPriority;
	}

	public void setTodoPriority(int priority) {
		this.todoPriority = priority;
	}
}
