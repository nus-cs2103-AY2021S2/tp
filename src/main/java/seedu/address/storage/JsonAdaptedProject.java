package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.project.CompletableTaskList;
import seedu.address.model.project.EventList;
import seedu.address.model.project.ParticipantList;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.task.CompletableTodo;
import seedu.address.model.task.repeatable.Event;

/**
 * Jackson-friendly version of {@link Project}.
 */
class JsonAdaptedProject {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Project's %s field is missing!";

    private final String projectName;
    private final List<JsonAdaptedEvent> eventList = new ArrayList<>();
    private final List<JsonAdaptedCompletable> completableList = new ArrayList<>();
    private final List<JsonAdaptedPerson> participantsList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedProject} with the given project details.
     */
    @JsonCreator
    public JsonAdaptedProject(@JsonProperty("projectName") String projectName,
                              @JsonProperty("events") List<JsonAdaptedEvent> eventList,
                              @JsonProperty("completable") List<JsonAdaptedCompletable> completableList,
                              @JsonProperty("participants") List<JsonAdaptedPerson> participantsList) {
        this.projectName = projectName;

        if (eventList != null) {
            this.eventList.addAll(eventList);
        }

        if (completableList != null) {
            this.completableList.addAll(completableList);
        }

        if (participantsList != null) {
            this.participantsList.addAll(participantsList);
        }
    }

    /**
     * Converts a given {@code Project} into this class for Jackson use.
     */
    public JsonAdaptedProject(Project source) {
        projectName = source.getProjectName().toString();

        eventList.addAll(source.getEvents().stream()
                .map(JsonAdaptedEvent::new).collect(Collectors.toList()));
        completableList.addAll(source.getCompletableTasks().stream()
                .map(JsonAdaptedCompletable::new).collect(Collectors.toList()));
        participantsList.addAll(source.getParticipants().stream()
                .map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted project object into the model's {@code Project} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted project.
     */
    public Project toModelType() throws IllegalValueException {
        final List<Event> projectEvents = new ArrayList<>();
        for (JsonAdaptedEvent event : eventList) {
            projectEvents.add(event.toModelType());
        }

        final List<CompletableTodo> projectCompletableTodos = new ArrayList<>();
        for (JsonAdaptedCompletable completable : completableList) {
            projectCompletableTodos.add(completable.toModelType());
        }

        final List<Person> projectParticipants = new ArrayList<>();
        for (JsonAdaptedPerson person : participantsList) {
            projectParticipants.add(person.toModelType());
        }

        if (projectName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    projectName.getClass().getSimpleName()));
        }

        return new Project(new ProjectName(projectName), new EventList(projectEvents),
                new CompletableTaskList(projectCompletableTodos), new ParticipantList(projectParticipants));
    }

}
