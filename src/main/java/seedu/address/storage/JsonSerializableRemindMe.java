package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyRemindMe;
import seedu.address.model.RemindMe;
import seedu.address.model.event.GeneralEvent;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;


/**
 * An Immutable RemindMe that is serializable to JSON format.
 */
@JsonRootName(value = "remindMe")
class JsonSerializableRemindMe {

    public static final String MESSAGE_DUPLICATE_MODULE = "Module list contains duplicate module(s).";
    public static final String MESSAGE_DUPLICATE_PERSON = "Person list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_EVENT = "Event list contains duplicate event(s).";
    private final List<JsonAdaptedModule> modules = new ArrayList<>();
    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedGeneralEvent> events = new ArrayList<>();


    /**
     * Constructs a {@code JsonSerializableRemindMeApp} with the given modules and persons.
     */
    @JsonCreator
    public JsonSerializableRemindMe(@JsonProperty("modules") List<JsonAdaptedModule> modules,
                                       @JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                    @JsonProperty("events") List<JsonAdaptedGeneralEvent> events) {
        this.modules.addAll(modules);
        this.persons.addAll(persons);
        this.events.addAll(events);
    }


    /**
     * Converts a given {@code ReadOnlyRemindMeApp} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableRemindMeApp}.
     */
    public JsonSerializableRemindMe(ReadOnlyRemindMe source) {
        modules.addAll(source.getModuleList().stream().map(JsonAdaptedModule::new).collect(Collectors.toList()));
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        events.addAll(source.getEventList()
            .stream()
            .map(JsonAdaptedGeneralEvent::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this RemindMe into the model's {@code RemindMe} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public RemindMe toModelType() throws IllegalValueException {
        RemindMe remindMe = new RemindMe();
        for (JsonAdaptedModule jsonAdaptedModule : modules) {
            Module module = jsonAdaptedModule.toModelType();
            if (remindMe.hasModule(module)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE);
            }
            remindMe.addModule(module);
        }
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (remindMe.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            remindMe.addPerson(person);
        }

        for (JsonAdaptedGeneralEvent jsonAdaptedGeneralEvent : events) {
            GeneralEvent generalEvent = jsonAdaptedGeneralEvent.toModelType();
            if (remindMe.hasEvent(generalEvent)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EVENT);
            }
            remindMe.addEvent(generalEvent);
        }
        return remindMe;
    }
}
