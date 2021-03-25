package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTaskTracker;
import seedu.address.model.TaskTracker;
import seedu.address.model.person.Task;

/**
 * An Immutable TaskTracker that is serializable to JSON format.
 */
@JsonRootName(value = "tasktracker")
class JsonSerializableTaskTracker {

    public static final String MESSAGE_DUPLICATE_TASK = "Tasks list contains duplicate task(s).";

    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTaskTracker} with the given tasks.
     */
    @JsonCreator
    public JsonSerializableTaskTracker(@JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Converts a given {@code ReadOnlyTaskTracker} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTaskTracker}.
     */
    public JsonSerializableTaskTracker(ReadOnlyTaskTracker source) {

        tasks.addAll(source.getTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code TaskTracker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TaskTracker toModelType() throws IllegalValueException {
        TaskTracker taskTracker = new TaskTracker();
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (taskTracker.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            taskTracker.addTask(task);
        }
        return taskTracker;
    }

}
