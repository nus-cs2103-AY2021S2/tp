package seedu.address.storage;

import static seedu.address.commons.core.Messages.MESSAGE_PAST_DEADLINE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.common.Category;
import seedu.address.model.common.Date;
import seedu.address.model.common.DatePastPredicate;
import seedu.address.model.common.Name;
import seedu.address.model.common.Tag;
import seedu.address.model.task.CompletionStatus;
import seedu.address.model.task.PinnedStatus;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String name;
    private final String deadline;
    private final String priority;
    private final String completionStatus;
    private final String pinnedStatus;
    private final List<JsonAdaptedCategory> category = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("name") String name, @JsonProperty("deadline") String deadline,
                             @JsonProperty("priority") String priority,
                             @JsonProperty("completionStatus") String completionStatus,
                             @JsonProperty("pinnedStatus") String pinnedStatus,
                             @JsonProperty("category") List<JsonAdaptedCategory> category,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.deadline = deadline;
        this.priority = priority;
        this.completionStatus = completionStatus;
        this.pinnedStatus = pinnedStatus;
        if (category != null) {
            this.category.addAll(category);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        name = source.getName().toString();
        deadline = source.getDeadline().toString();
        priority = source.getPriority().toString();
        completionStatus = source.isComplete() ? "COMPLETE" : "INCOMPLETE";
        pinnedStatus = source.isPinned() ? "PINNED" : "UNPINNED";
        category.addAll(source.getCategories().stream()
                .map(JsonAdaptedCategory::new)
                .collect(Collectors.toList()));
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        final List<Category> taskCategories = new ArrayList<>();
        final List<Tag> taskTags = new ArrayList<>();

        for (JsonAdaptedTag tag : tagged) {
            taskTags.add(tag.toModelType());
        }

        for (JsonAdaptedCategory cat : category) {
            taskCategories.add(cat.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(deadline)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDeadline = new Date(deadline);

        if (!Priority.isValidPriority(priority)) {
            throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
        }
        final Priority modelPriority = new Priority(priority);

        if (completionStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompletionStatus.class.getSimpleName()));
        }
        if (!CompletionStatus.isValidStatus(completionStatus)) {
            throw new IllegalValueException(CompletionStatus.MESSAGE_CONSTRAINTS);
        }
        final CompletionStatus modelCompletionStatus = new CompletionStatus(completionStatus);

        //is deadline past today check
        if (!new DatePastPredicate().test(modelDeadline)) {
            throw new IllegalValueException(MESSAGE_PAST_DEADLINE);
        }
        if (pinnedStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PinnedStatus.class.getSimpleName()));
        }
        if (!PinnedStatus.isValidStatus(pinnedStatus)) {
            throw new IllegalValueException(PinnedStatus.MESSAGE_CONSTRAINTS);
        }
        final PinnedStatus modelPinnedStatus = new PinnedStatus(pinnedStatus);

        final Set<Tag> modelTags = new HashSet<>(taskTags);
        final Set<Category> modelCategories = new HashSet<>(taskCategories);

        Task task = new Task(modelName, modelDeadline, modelPriority, modelCategories, modelTags);
        if (modelCompletionStatus.isComplete()) {
            task.markTaskAsDone();
        }

        if (modelPinnedStatus.isPinned()) {
            task.pin();
        }
        return task;
    }

}
