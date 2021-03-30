package seedu.address.model.task.repeatable;

import java.util.Objects;

import seedu.address.commons.util.DateUtil;
import seedu.address.commons.util.TimeUtil;
import seedu.address.model.project.ProjectName;
import seedu.address.model.task.Repeatable;

/**
 * An event with an associated project name.
 */
public class EventWithProject extends Repeatable {
    private final ProjectName projectName;

    /**
     * Constructor for EventWithProject.
     *
     * @param repeatable The {@code Repeatable}.
     * @param projectName The project name to be associated with this event.
     */
    public EventWithProject(Repeatable repeatable, ProjectName projectName) {
        super(repeatable.getDescription(), repeatable.getDate(), repeatable.getTime(), repeatable.getIsWeekly());
        this.projectName = projectName;
    }

    /**
     * Returns the isWeekly status of the EventWithProject.
     *
     * @return A Boolean representing the EventWithProject's isWeekly status.
     */
    public Boolean getIsWeekly() {
        assert this.isWeekly != null;
        return this.isWeekly;
    }

    public ProjectName getProjectName() {
        return projectName;
    }

    /**
     * Checks if an instance of a EventWithProject is equal to another Object.
     *
     * @param other Object to be compared with.
     * @return True if both objects are equal. Else return false.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EventWithProject)) {
            return false;
        }

        EventWithProject otherEvent = (EventWithProject) other;
        return otherEvent.getDescription().equals(getDescription())
                && otherEvent.getIsWeekly().equals(getIsWeekly())
                && otherEvent.getTime().equals(getTime())
                && otherEvent.getDate().equals(getDate());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, isWeekly, date, time, projectName);
    }

    /**
     * Returns a String representation of the EventWithProject.
     *
     * @return A String representation of the EventWithProject.
     */
    @Override
    public String toString() {
        if (!getIsWeekly()) {
            return this.description + " (on: " + DateUtil.decodeDate(date) + " at:" + TimeUtil.decodeTime(time)
                    + ") with project " + projectName.toString();
        }

        return this.description + " (every " + DateUtil.decodeDateIntoDay(date) + " at:" + TimeUtil.decodeTime(time)
                + ") with project " + projectName.toString();
    }
}
