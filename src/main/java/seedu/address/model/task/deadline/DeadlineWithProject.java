package seedu.address.model.task.deadline;

import java.util.Objects;

import seedu.address.commons.util.DateUtil;
import seedu.address.model.project.ProjectName;
import seedu.address.model.task.CompletableDeadline;

/**
 * A dateline with an associated project name.
 */
public class DeadlineWithProject extends CompletableDeadline {
    private final ProjectName projectName;

    /**
     * Creates a {@code DeadlineWithProject} object.
     *
     * @param deadline The {@code CompletableDeadline}.
     * @param projectName Project name to be associated with deadline.
     */
    public DeadlineWithProject(CompletableDeadline deadline, ProjectName projectName) {
        super(deadline.getDescription(), deadline.getBy(), deadline.getIsDone());
        this.projectName = projectName;
    }

    public ProjectName getProjectName() {
        return projectName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeadlineWithProject)) {
            return false;
        }

        DeadlineWithProject otherDeadline = (DeadlineWithProject) other;
        return otherDeadline.getDescription().equals(getDescription())
                && otherDeadline.getIsDone().equals(getIsDone())
                && otherDeadline.getBy().equals(getBy())
                && otherDeadline.getProjectName().equals(getProjectName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, isDone, by, projectName);
    }

    @Override
    public String toString() {
        return this.description + " (by: " + DateUtil.decodeDate(by) + ") with project " + projectName;
    }
}
