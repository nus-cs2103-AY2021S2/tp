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

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedTask> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTaskTracker} with the given persons.
     */
    @JsonCreator
    public JsonSerializableTaskTracker(@JsonProperty("persons") List<JsonAdaptedTask> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyTaskTracker} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTaskTracker}.
     */
    public JsonSerializableTaskTracker(ReadOnlyTaskTracker source) {

        persons.addAll(source.getPersonList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code TaskTracker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TaskTracker toModelType() throws IllegalValueException {
        TaskTracker taskTracker = new TaskTracker();
        for (JsonAdaptedTask jsonAdaptedTask : persons) {
            Task task = jsonAdaptedTask.toModelType();
            if (taskTracker.hasPerson(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            taskTracker.addPerson(task);
        }
        return taskTracker;
    }

}
