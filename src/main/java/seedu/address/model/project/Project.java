package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javafx.collections.transformation.FilteredList;
import seedu.address.model.person.Person;
import seedu.address.model.task.CompletableDeadline;
import seedu.address.model.task.CompletableTodo;
import seedu.address.model.task.deadline.Deadline;
import seedu.address.model.task.repeatable.Event;
import seedu.address.model.task.todo.Todo;

/**
 * Represents a Project in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Project {

    // Identity fields
    private final ProjectName projectName;

    // Data fields
    private final EventList events;
    private final TodoList todos;
    private final DeadlineList deadlines;
    private final ParticipantList participants;

    /**
     * Constructs an empty {@code Project}.
     * Every field must be present and not null.
     */
    public Project(ProjectName projectName) {
        requireAllNonNull(projectName);

        this.projectName = projectName;
        this.events = new EventList();
        this.todos = new TodoList();
        this.deadlines = new DeadlineList();
        this.participants = new ParticipantList();
    }

    /**
     * Constructs a {@code Project}
     * Every field must be present and not null.
     */
    public Project(ProjectName projectName, EventList events, TodoList todos, DeadlineList deadlines,
                   ParticipantList participants) {
        requireAllNonNull(projectName, events, todos, deadlines, participants);
        this.projectName = projectName;
        this.events = events;
        this.todos = todos;
        this.deadlines = deadlines;
        this.participants = participants;
    }

    public ProjectName getProjectName() {
        assert projectName != null;
        return projectName;
    }

    public EventList getEvents() {
        assert events != null;
        return events;
    }

    public TodoList getTodos() {
        assert todos != null;
        return todos;
    }

    public DeadlineList getDeadlines() {
        assert deadlines != null;
        return deadlines;
    }

    public ParticipantList getParticipants() {
        assert participants != null;
        return participants;
    }

    /**
     * Returns all {@code Events} that fall on a specific {@code LocalDate}
     *
     * @param dateOfEvent The {@code LocalDate} which the events occur on.
     * @return A {@code FilteredList<Event>}
     */
    public FilteredList<Event> getEventsOnDate(LocalDate dateOfEvent) {
        return events.getEventsOnDate(dateOfEvent);
    }

    /**
     * Returns all {@code CompletableDeadline} that fall on a specific {@code LocalDate}
     *
     * @param dateOfEvent The {@code LocalDate} which the deadlines occur on.
     * @return A {@code FilteredList<CompletableDeadline>}
     */
    public FilteredList<CompletableDeadline> getDeadlinesOnDate(LocalDate dateOfEvent) {
        return deadlines.getDeadlinesOnDate(dateOfEvent);
    }

    /**
     * Adds a participant to the {@code ParticipantList}.
     *
     * @param person {@code Person} to add.
     */
    public void addParticipant(Person person) {
        requireNonNull(person);
        this.participants.addParticipant(person);
    }

    /**
     * Returns true if a participant with the same identity as {@code person} exists
     * in this {@code Project}'s {@code participants}.
     *
     * @param person the {@code Person} to compare.
     * @return true if a participant with the same identity as {@code person} exists under this {@code Project}.
     */
    public boolean hasParticipant(Person person) {
        return participants.contains(person);
    }

    /**
     * Adds a deadline to {@code DeadlineList} field of this {@code Project}.
     *
     * @param deadline {@code Deadline} to add.
     */
    public void addDeadline(Deadline deadline) {
        requireNonNull(deadline);
        this.deadlines.addDeadline(deadline);
    }

    /**
     * Adds an event to {@code EventList} field of this {@code Project}.
     *
     * @param event {@code Event} to add.
     */
    public void addEvent(Event event) {
        requireNonNull(event);
        this.events.addEvent(event);
    }

    /**
     * Adds an todo to {@code TodoList} field of this {@code Project}.
     *
     * @param todo {@code Todo} to add.
     */
    public void addTodo(Todo todo) {
        requireNonNull(todo);
        this.todos.addTodo(todo);
    }

    /**
     *  Returns the {@code Person}. at the specified position in this {@code Project}'s {@code ParticipantList}.
     *
     * @return the {@code Person}. at the specified position in this {@code Project}'s {@code ParticipantList}.
     */
    public Person getParticipant(Integer i) {
        requireNonNull(i);
        return participants.get(i);
    }

    /**
     *  Deletes a participant from {@code ParticipantList} field of this {@code Project}.
     *
     * @param i Index of {@code Person} to be deleted.
     */
    public void deleteParticipant(Integer i) {
        requireNonNull(i);
        this.participants.delete(i);
    }

    /**
     * Deletes a deadline from {@code DeadlineList} field of this {@code Project}.
     *
     * @param i Index of {@code Deadline} to be deleted.
     */
    public void deleteDeadline(Integer i) {
        requireNonNull(i);
        this.deadlines.deleteDeadline(i);
    }

    /**
     * Deletes an event from {@code EventList} field of this {@code Project}.
     *
     * @param i Index of {@code Event} to be deleted.
     */
    public void deleteEvent(Integer i) {
        requireNonNull(i);
        this.events.deleteEvent(i);
    }

    /**
     * Deletes a todo from {@code TodoList} field of this {@code Project}.
     *
     * @param i Index of {@code Todo} to be deleted.
     */
    public void deleteTodo(Integer i) {
        requireNonNull(i);
        this.todos.deleteTodo(i);
    }

    /**
     * Marks a deadline from {@code DeadlineList} field of this {@code Project} as done.
     *
     * @param i Index of {@code Deadline} to be marked as done.
     */
    public void markDeadline(Integer i) {
        requireNonNull(i);
        this.deadlines.markAsDone(i);
    }

    /**
     * Marks an event from {@code EventList} field of this {@code Project} as done.
     *
     * @param i Index of {@code Event} to be marked as done.
     */
    public void markEvent(Integer i) {
        requireNonNull(i);
        this.events.markAsDone(i);
    }

    /**
     * Marks a todo from {@code TodoList} field of this {@code Project} as done.
     *
     * @param i Index of {@code Todo} to be marked as done.
     */
    public void markTodo(Integer i) {
        requireNonNull(i);
        this.todos.markAsDone(i);
    }

    /**
     * Returns true if both projects have the same name.
     * This defines a weaker notion of equality between two projects.
     */
    public boolean isSameProject(Project otherProject) {
        if (otherProject == this) {
            return true;
        }

        return otherProject != null
                && otherProject.getProjectName().equals(getProjectName());
    }

    /**
     * Returns true if both projects have the same identity and data fields.
     * This defines a stronger notion of equality between two projects.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Project)) {
            return false;
        }

        Project otherProject = (Project) other;
        return otherProject.getProjectName().equals(getProjectName())
                && otherProject.getEvents().equals(getEvents())
                && otherProject.getTodos().equals(getTodos())
                && otherProject.getDeadlines().equals(getDeadlines())
                && otherProject.getParticipants().equals(getParticipants());
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName, events, todos, deadlines, participants);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getProjectName());

        List<Event> events = getEvents().getEvents();
        if (!events.isEmpty()) {
            builder.append("; Events: ");
            events.forEach(builder::append);
        }

        List<CompletableTodo> todos = getTodos().getTodos();
        if (!todos.isEmpty()) {
            builder.append("; Todos: ");
            todos.forEach(builder::append);
        }

        List<CompletableDeadline> deadlines = getDeadlines().getDeadlines();
        if (!deadlines.isEmpty()) {
            builder.append("; Deadlines: ");
            deadlines.forEach(builder::append);
        }

        List<Person> participants = getParticipants().getParticipants();
        if (!participants.isEmpty()) {
            builder.append("; Participants: ");
            participants.forEach(builder::append);
        }

        return builder.toString();
    }
}
