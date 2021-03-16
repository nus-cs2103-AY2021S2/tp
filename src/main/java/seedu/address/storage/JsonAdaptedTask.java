package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Date;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;

/**
 * Jackson-friendly version of {@link Task}
 */
public class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String taskDescription;
    private final String taskDeadline;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("taskDescription") String taskDescription,
                           @JsonProperty("taskDeadline") String taskDeadline,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.taskDescription = taskDescription;
        this.taskDeadline = taskDeadline;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        taskDescription = source.getTaskDescription().description;
        taskDeadline = source.getDate().toString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted schedule oject into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Task toModelType() throws IllegalValueException {
        final List<Tag> taskTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            taskTags.add(tag.toModelType());
        }
        if (taskDescription == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskDescription.class.getSimpleName()));
        }
        if (!TaskDescription.isValidDescription(taskDescription)) {
            throw new IllegalValueException(TaskDescription.MESSAGE_CONSTRAINTS);
        }
        final TaskDescription modelTaskDescription = new TaskDescription(taskDescription);

        if (taskDeadline == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        final Date modelTaskDeadline = new Date(taskDeadline);

        final Set<Tag> modelTags = new HashSet<>(taskTags);
        return new Task(modelTaskDescription, modelTaskDeadline, modelTags);
    }

}
