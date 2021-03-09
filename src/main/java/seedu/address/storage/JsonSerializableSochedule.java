package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlySochedule;
import seedu.address.model.Sochedule;
import seedu.address.model.event.Event;
import seedu.address.model.task.Task;

/**
 * An Immutable Sochedule that is serializable to JSON format.
 */
@JsonRootName(value = "sochedule")
class JsonSerializableSochedule {

    public static final String MESSAGE_DUPLICATE_TASK = "Task list contains duplicate task(s).";
    public static final String MESSAGE_DUPLICATE_EVENT = "Task list contains duplicate event(s).";

    private final List<JsonAdaptedTask> tasks = new ArrayList<>();
    private final List<JsonAdaptedEvent> events = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableSochedule} with the given persons.
     */
    @JsonCreator
    public JsonSerializableSochedule(@JsonProperty("tasks") List<JsonAdaptedTask> tasks,
                                     @JsonProperty("events") List<JsonAdaptedEvent> events) {
        this.tasks.addAll(tasks);
        this.events.addAll(events);
    }

    /**
     * Converts a given {@code ReadOnlySochedule} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableSochedule}.
     */
    public JsonSerializableSochedule(ReadOnlySochedule source) {
        tasks.addAll(source.getTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
        events.addAll(source.getEventList().stream().map(JsonAdaptedEvent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this sochedule into the model's {@code Sochedule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Sochedule toModelType() throws IllegalValueException {
        Sochedule sochedule = new Sochedule();
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (sochedule.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            sochedule.addTask(task);
        }

        for (JsonAdaptedEvent jsonAdaptedEvent : events) {
            Event event = jsonAdaptedEvent.toModelType();
            if (sochedule.hasEvent(event)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EVENT);
            }
            sochedule.addEvent(event);
        }

        return sochedule;
    }

}
