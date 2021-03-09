package seedu.address.model.task;

public abstract class Task {

    protected String description;
    protected Boolean isDone;
    protected String taskType;

    /**
     * Constructor for Task.
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "Task";
    }

    /**
     * Constructor for Task
     * @param description Description of the Task.
     * @param isDone Marks whether the Task is Done.
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.taskType = "Task";
    }

    /**
     * Returns a status icon dependent on the status of the Task.
     * @return A string representing the Task's status.
     */
    public String getStatusIcon() {
        assert isDone != null;
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the Task description.
     * @return A String representing the Task description.
     */
    public String getDescription() {
        assert this.description != null;
        return this.description;
    }

    /**
     * Returns the status of the Task.
     * @return A Boolean representing the Task's status.
     */
    public Boolean getIsDone() {
        assert this.isDone != null;
        return this.isDone;
    }

    /**
     * Returns the type of Task.
     * @return A String representing the type of Task.
     */
    public String getTaskType() {
        assert this.taskType != null;
        return this.taskType;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a String representation of the Task.
     * @return A String representation of the Task.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}