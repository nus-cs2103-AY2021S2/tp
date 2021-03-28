package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.HeyMatez;
import seedu.address.model.ReadOnlyHeyMatez;
import seedu.address.model.assignee.Assignee;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * An Immutable HeyMatez that is serializable to JSON format.
 */
@JsonRootName(value = "heymatez")
class JsonSerializableHeyMatez {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableHeyMatez} with the given persons.
     */
    @JsonCreator
    public JsonSerializableHeyMatez(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                    @JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.persons.addAll(persons);
        this.tasks.addAll(tasks);
    }

    /**
     * Converts a given {@code ReadOnlyHeyMatez} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableHeyMatez}.
     */
    public JsonSerializableHeyMatez(ReadOnlyHeyMatez source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        tasks.addAll(source.getTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts hey matez into the model's {@code HeyMatez} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public HeyMatez toModelType() throws IllegalValueException {
        HeyMatez heyMatez = new HeyMatez();

        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (heyMatez.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            heyMatez.addPerson(person);
        }

        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();

            for (Assignee assignee : task.getAssignees()) {

                Person person = new Person(new Name(assignee.assigneeName));
                if (!heyMatez.hasPerson(person)) {
                    throw new IllegalValueException("No such member in the displayed member's list!");
                }
            }

            heyMatez.addTask(task);
        }

        return heyMatez;
    }

}
