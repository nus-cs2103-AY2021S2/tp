package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.model.groupmate.Groupmate;
import seedu.address.model.task.CompletableDeadline;
import seedu.address.model.task.CompletableTodo;
import seedu.address.model.task.deadline.Deadline;
import seedu.address.model.task.repeatable.Event;
import seedu.address.model.task.todo.Todo;

/**
 * Represents a Project in CoLAB.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Project {

    // Identity fields
    private final ProjectName projectName;

    // Data fields
    private final EventList events;
    private final TodoList todos;
    private final DeadlineList deadlines;
    private final GroupmateList groupmates;

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
        this.groupmates = new GroupmateList();
    }

    /**
     * Constructs a {@code Project}
     * Every field must be present and not null.
     */
    public Project(ProjectName projectName, EventList events, TodoList todos, DeadlineList deadlines,
                   GroupmateList groupmates) {
        requireAllNonNull(projectName, events, todos, deadlines, groupmates);

        this.projectName = projectName;
        this.events = events;
        this.todos = todos;
        this.deadlines = deadlines;
        this.groupmates = groupmates;
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

    public GroupmateList getGroupmates() {
        assert groupmates != null;
        return groupmates;
    }

    /**
     * Returns all {@code Events} that fall on a specific {@code LocalDate}
     *
     * @param dateOfEvent The {@code LocalDate} which the events occur on.
     * @return A {@code FilteredList<Event>}
     */
    public FilteredList<Event> getEventsOnDate(LocalDate dateOfEvent) {
        requireNonNull(dateOfEvent);
        return events.getEventsOnDate(dateOfEvent);
    }

    /**
     * Returns all {@code CompletableDeadline} that fall on a specific {@code LocalDate}
     *
     * @param dateOfEvent The {@code LocalDate} which the deadlines occur on.
     * @return A {@code FilteredList<CompletableDeadline>}
     */
    public FilteredList<CompletableDeadline> getDeadlinesOnDate(LocalDate dateOfEvent) {
        requireNonNull(dateOfEvent);
        return deadlines.getDeadlinesOnDate(dateOfEvent);
    }

    /**
     * Adds a groupmate to the {@code GroupmateList}.
     *
     * @param groupmate {@code Groupmate} to add.
     */
    public void addGroupmate(Groupmate groupmate) {
        requireNonNull(groupmate);
        this.groupmates.addGroupmate(groupmate);
    }

    /**
     * Returns true if a {@code Groupmate} with the same identity as {@code groupmate} exists
     * in this {@code Project}'s {@code Groupmate}s.
     *
     * @param groupmate the {@code Groupmate} to compare.
     * @return true if a groupmate with the same identity as {@code groupmate} exists under this {@code Project}.
     */
    public boolean hasGroupmate(Groupmate groupmate) {
        requireNonNull(groupmate);
        return groupmates.contains(groupmate);
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
     *  Returns the {@code Person}. at the specified position in this {@code Project}'s {@code GroupmateList}.
     *
     * @return the {@code Person}. at the specified position in this {@code Project}'s {@code GroupmateList}.
     */
    public Groupmate getGroupmate(Integer i) {
        requireNonNull(i);
        return groupmates.get(i);
    }

    /**
     * Set the {@code Event} specified by index with a new {@code Event}.
     *
     * @param i index number specifies the target {@code Event}.
     * @param event new {@code Event} for this index.
     */
    public void setEvent(Integer i, Event event) {
        requireAllNonNull(event, i);

        this.events.setEvent(i, event);
    }

    /**
     * Set the {@code Groupmate} specified by index with a new {@code Groupmate}.
     *
     * @param i index number specifies the target {@code Groupmate}.
     * @param groupmate new {@code Groupmate} for this index.
     */
    public void setGroupmate(Integer i, Groupmate groupmate) {
        requireAllNonNull(groupmate, i);

        this.groupmates.setGroupmate(i, groupmate);
    }

    /**
     *  Deletes a groupmate from {@code groupmates} field of this {@code Project}.
     *
     * @param i Index of {@code Person} to be deleted.
     */
    public void deleteGroupmate(Integer i) {
        requireNonNull(i);
        this.groupmates.delete(i);
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
     * Returns a new copy of this project.
     *
     * @return a copy of this project.
     */
    public Project getCopy() {
        EventList eventList = this.events.getCopy();
        DeadlineList deadlineList = this.deadlines.getCopy();
        TodoList todoList = this.todos.getCopy();
        GroupmateList groupmateList = this.groupmates.getCopy();

        return new Project(this.getProjectName(), eventList, todoList, deadlineList, groupmateList);
    }

    /**
     * Returns {@code deadlines} as an {@code SortedList<CompletableDeadline>}
     *
     * @return A {@code SortedList<CompletableDeadline>}
     */
    public SortedList<CompletableDeadline> getSortedDeadlines() {
        return deadlines.getSortedDeadlineList();
    }

    /**
     * Returns {@code events} as an {@code SortedList<Event>}
     *
     * @return A {@code SortedList<Event>}
     */
    public SortedList<Event> getSortedEvents() {
        return events.getSortedEventList();
    }

    /**
     * Returns {@code todos} as a {@code SortedList<CompletableTodo>}
     *
     * @return A {@code SortedList<CompletableTodo>}
     */
    public SortedList<CompletableTodo> getSortedTodos() {
        return todos.getSortedTodos();
    }

    /**
     * Returns {@code groupmates} as a {@code SortedList<Groupmate>}
     *
     * @return A {@code SortedList<Groupmate>}
     */
    public SortedList<Groupmate> getSortedGroupmates() {
        return groupmates.getSortedGroupmates();
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
                && otherProject.getGroupmates().equals(getGroupmates());
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName, events, todos, deadlines, groupmates);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getProjectName());

        List<Event> events = getEvents().getSortedEventList();
        if (!events.isEmpty()) {
            builder.append("; Events: ");
            events.forEach(builder::append);
        }

        List<CompletableTodo> todos = getTodos().getSortedTodos();
        if (!todos.isEmpty()) {
            builder.append("; Todos: ");
            todos.forEach(builder::append);
        }

        List<CompletableDeadline> deadlines = getDeadlines().getSortedDeadlineList();
        if (!deadlines.isEmpty()) {
            builder.append("; Deadlines: ");
            deadlines.forEach(builder::append);
        }

        List<Groupmate> groupmates = getGroupmates().getSortedGroupmates();
        if (!groupmates.isEmpty()) {
            builder.append("; Groupmates: ");
            groupmates.forEach(builder::append);
        }

        return builder.toString();
    }

}
