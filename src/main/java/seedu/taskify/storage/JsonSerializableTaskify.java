package seedu.taskify.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.taskify.commons.exceptions.IllegalValueException;
import seedu.taskify.model.ReadOnlyTaskify;
import seedu.taskify.model.Taskify;
import seedu.taskify.model.task.Task;

/**
 * An Immutable TaskifyParser that is serializable to JSON format.
 */
@JsonRootName(value = "taskify")
class JsonSerializableTaskify {

    public static final String MESSAGE_DUPLICATE_TASK = "Tasks list contains duplicate task(s).";

    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTaskify} with the given tasks.
     */
    @JsonCreator
    public JsonSerializableTaskify(@JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Converts a given {@code ReadOnlyTaskify} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTaskify}.
     */
    public JsonSerializableTaskify(ReadOnlyTaskify source) {
        tasks.addAll(source.getTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code TaskifyParser} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Taskify toModelType() throws IllegalValueException {
        Taskify taskify = new Taskify();
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (taskify.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            taskify.addTask(task);
        }
        return taskify;
    }

}
