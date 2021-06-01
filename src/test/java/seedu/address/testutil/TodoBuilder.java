package seedu.address.testutil;

import seedu.address.model.task.todo.Todo;

/**
 * A utility class to help with building Todo objects.
 */
public class TodoBuilder {

    public static final String DEFAULT_DESCRIPTION = "Todo Description";
    public static final Boolean DEFAULT_IS_DONE = false;

    private String description;
    private Boolean isDone;

    /**
     * Creates a {@code TodoBuilder} with the default details.
     */
    public TodoBuilder() {
        description = DEFAULT_DESCRIPTION;
        isDone = DEFAULT_IS_DONE;
    }

    /**
     * Initializes the TodoBuilder with the data of {@code todoToCopy}.
     */
    public TodoBuilder(Todo todoToCopy) {
        description = todoToCopy.getDescription();
        isDone = todoToCopy.getIsDone();
    }

    /**
     * Sets the {@code Description} of the {@code Todo} that we are building.
     */
    public TodoBuilder withDescription(String description) {
        this.description = description;
        return this;
    }


    /**
     * Sets the {@code isDone} status of the {@code Todo} that we are building.
     */
    public TodoBuilder withIsDone(Boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    /**
     * Builds the {@code Todo} object.
     *
     * @return {@code Todo}.
     */
    public Todo build() {
        return new Todo(description, isDone);
    }

}
