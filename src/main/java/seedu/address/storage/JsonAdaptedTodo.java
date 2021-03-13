package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.CompletableTodo;
import seedu.address.model.task.todo.Todo;

/**
 * Jackson-friendly version of {@link CompletableTodo}.
 */
class JsonAdaptedTodo {
    private final String description;
    private final Boolean isDone;

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given {@code description} and {@code isDone}.
     */
    @JsonCreator
    public JsonAdaptedTodo(@JsonProperty("description")String description,
                           @JsonProperty("isDone") Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Converts a given {@code CompletableTodo} into this class for Jackson use.
     */
    public JsonAdaptedTodo(CompletableTodo source) {
        description = source.getDescription();
        isDone = source.getIsDone();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code CompletableTodo} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event.
     */
    public CompletableTodo toModelType() throws IllegalValueException {
        return new Todo(description, isDone);
    }
}
