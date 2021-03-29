package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.groupmate.Groupmate;
import seedu.address.model.project.DeadlineList;
import seedu.address.model.project.EventList;
import seedu.address.model.project.GroupmateList;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.TodoList;
import seedu.address.model.task.CompletableDeadline;
import seedu.address.model.task.CompletableTodo;
import seedu.address.model.task.repeatable.Event;

/**
 * Jackson-friendly version of {@link Project}.
 */
class JsonAdaptedProject {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Project's %s field is missing!";

    private final String projectName;
    private final List<JsonAdaptedEvent> eventList = new ArrayList<>();
    private final List<JsonAdaptedTodo> todoList = new ArrayList<>();
    private final List<JsonAdaptedDeadline> deadlineList = new ArrayList<>();
    private final List<JsonAdaptedGroupmate> groupmateList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedProject} with the given project details.
     */
    @JsonCreator
    public JsonAdaptedProject(@JsonProperty("projectName") String projectName,
                              @JsonProperty("events") List<JsonAdaptedEvent> eventList,
                              @JsonProperty("todos") List<JsonAdaptedTodo> todoList,
                              @JsonProperty("deadlines") List<JsonAdaptedDeadline> deadlineList,
                              @JsonProperty("groupmates") List<JsonAdaptedGroupmate> groupmateList) {
        this.projectName = projectName;

        if (eventList != null) {
            this.eventList.addAll(eventList);
        }

        if (todoList != null) {
            this.todoList.addAll(todoList);
        }

        if (deadlineList != null) {
            this.deadlineList.addAll(deadlineList);
        }

        if (groupmateList != null) {
            this.groupmateList.addAll(groupmateList);
        }
    }

    /**
     * Converts a given {@code Project} into this class for Jackson use.
     */
    public JsonAdaptedProject(Project source) {
        projectName = source.getProjectName().toString();

        eventList.addAll(source.getEvents().stream()
                .map(JsonAdaptedEvent::new).collect(Collectors.toList()));
        todoList.addAll(source.getTodos().stream()
                .map(JsonAdaptedTodo::new).collect(Collectors.toList()));
        deadlineList.addAll(source.getDeadlines().stream()
                .map(JsonAdaptedDeadline::new).collect(Collectors.toList()));
        groupmateList.addAll(source.getGroupmates().stream()
                .map(JsonAdaptedGroupmate::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted project object into the model's {@code Project} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted project.
     */
    public Project toModelType() throws IllegalValueException {
        if (projectName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ProjectName.class.getSimpleName()));
        }

        ProjectName convertedProjectName;
        try {
            convertedProjectName = new ProjectName(projectName);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(e.getMessage());
        }

        final List<Event> projectEvents = new ArrayList<>();
        for (JsonAdaptedEvent event : eventList) {
            projectEvents.add(event.toModelType());
        }

        final List<CompletableTodo> projectTodos = new ArrayList<>();
        for (JsonAdaptedTodo todo : todoList) {
            projectTodos.add(todo.toModelType());
        }

        final List<CompletableDeadline> projectDeadlines = new ArrayList<>();
        for (JsonAdaptedDeadline deadline : deadlineList) {
            projectDeadlines.add(deadline.toModelType());
        }

        final List<Groupmate> projectContacts = new ArrayList<>();
        for (JsonAdaptedGroupmate groupmate : groupmateList) {
            projectContacts.add(groupmate.toModelType());
        }

        return new Project(convertedProjectName, new EventList(projectEvents), new TodoList(projectTodos),
                new DeadlineList(projectDeadlines), new GroupmateList(projectContacts));
    }

}
