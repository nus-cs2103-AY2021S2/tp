package seedu.heymatez.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.heymatez.commons.exceptions.IllegalValueException;
import seedu.heymatez.model.assignee.Assignee;
import seedu.heymatez.model.task.Deadline;
import seedu.heymatez.model.task.Description;
import seedu.heymatez.model.task.Priority;
import seedu.heymatez.model.task.Task;
import seedu.heymatez.model.task.TaskStatus;
import seedu.heymatez.model.task.Title;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String title;
    private final String description;
    private final String status;
    private final String deadline;
    private final String priority;
    private final List<JsonAdaptedAssignee> assignee = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedtask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("title") String title, @JsonProperty("description") String description,
                           @JsonProperty("deadline") String deadline, @JsonProperty("status") String status,
                           @JsonProperty("priority") String priority,
                           @JsonProperty("assignee") List<JsonAdaptedAssignee> assignee) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.priority = priority;
        if (assignee != null) {
            this.assignee.addAll(assignee);
        }
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        assert source != null;
        title = source.getTitle().taskTitle;
        description = source.getDescription().desc;
        deadline = source.getDeadline().getUnformattedDate();
        status = source.getTaskStatus().getStatus();
        priority = source.getPriority().getPriority();
        assignee.addAll(source.getAssignees().stream()
                .map(JsonAdaptedAssignee::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        final List<Assignee> personAssignees = new ArrayList<>();
        for (JsonAdaptedAssignee currentAssignee : assignee) {
            personAssignees.add(currentAssignee.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Deadline.class.getSimpleName()));
        }
        if (!Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        final Deadline modelDeadline = new Deadline(deadline);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskStatus.getEnumName()));
        }
        if (!TaskStatus.isValidValue(status)) {
            throw new IllegalValueException(TaskStatus.MESSAGE_CONSTRAINTS);
        }

        final TaskStatus modelTaskStatus = TaskStatus.valueOf(status.toUpperCase());

        if (priority == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Priority.getEnumName()));
        }
        if (!Priority.isValidValue(priority)) {
            throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
        }

        final Priority modelPriority = Priority.valueOf(priority.toUpperCase());

        final Set<Assignee> modelAssignees = new HashSet<>(personAssignees);
        return new Task(modelTitle, modelDescription, modelDeadline, modelTaskStatus, modelPriority, modelAssignees);
    }
}
