package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.model.person.Person;
import seedu.address.model.task.Completable;
import seedu.address.model.task.repeatable.Event;

/**
 * Represents a Project in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Project {

    // Identity fields
    private final ProjectName projectName;

    // Data fields
    private final EventList events;
    private final CompletableTaskList completableTasks;
    private final ParticipantList participants;

    /**
     * Constructs an empty {@code Project}.
     * Every field must be present and not null.
     */
    public Project(ProjectName projectName) {
        requireAllNonNull(projectName);

        this.projectName = projectName;
        this.events = new EventList();
        this.completableTasks = new CompletableTaskList();
        this.participants = new ParticipantList();
    }

    /**
     * Constructs a {@code Project}
     * Every field must be present and not null.
     */
    public Project(ProjectName projectName, EventList events, CompletableTaskList completableTasks,
                   ParticipantList participants) {
        requireAllNonNull(projectName, events, completableTasks, participants);
        this.projectName = projectName;
        this.events = events;
        this.completableTasks = completableTasks;
        this.participants = participants;
    }

    public ProjectName getProjectName() {
        return projectName;
    }

    public EventList getEvents() {
        return events;
    }

    public CompletableTaskList getCompletableTasks() {
        return completableTasks;
    }

    public ParticipantList getParticipants() {
        return participants;
    }

    /**
     * Adds a participant to the {@code ParticipantList}.
     *
     * @param person {@code Person} to add.
     */
    public Project addParticipant(Person person) {
        requireNonNull(person);
        ParticipantList participants = this.participants.addParticipant(person);
        return new Project(this.getProjectName(), this.getEvents(),
                this.getCompletableTasks(), participants);
    }

    /**
     * Returns true if a participant with the same identity as {@code person} exists
     * in this {@code Peroject}'s {@code participants}.
     *
     * @param person the {@code Person} to compare.
     * @return true if a participant with the same identity as {@code person} exists under this {@code Peroject}.
     */
    public boolean hasParticipant(Person person) {
        return participants.contains(person);
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
                && otherProject.getCompletableTasks().equals(getCompletableTasks())
                && otherProject.getParticipants().equals(getParticipants());
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName, events, completableTasks, participants);
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

        List<Completable> completableTasks = getCompletableTasks().getCompletableTasks();
        if (!completableTasks.isEmpty()) {
            builder.append("; Completable Tasks: ");
            completableTasks.forEach(builder::append);
        }

        List<Person> participants = getParticipants().getParticipants();
        if (!participants.isEmpty()) {
            builder.append("; Participants: ");
            participants.forEach(builder::append);
        }

        return builder.toString();
    }

}
