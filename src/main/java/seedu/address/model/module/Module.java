package seedu.address.model.module;

import java.time.LocalDateTime;
import java.util.Objects;

public class Module {
    private Title title;
    private AssignmentList assignments;
    private Exam exam;
    public Module(Title title) {
        this.title = title;
        this.assignments = new AssignmentList();
        this.exam = null;
    }

    public Title getTitle() {
        return title;
    }

    public AssignmentList getAssignments() {
        return assignments;
    }

    public Assignment getAssignment(int index) {
        return assignments.get(index);
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public void addAssignment(Assignment assignment) {
        this.assignments.add(assignment);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameModule(Module otherModule) {
        if (otherModule == this) {
            return true;
        }

        return otherModule != null
                && otherModule.getTitle().equals(getTitle());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Module)) {
            return false;
        }

        Module otherModule = (Module) other;
        return otherModule.getTitle().equals(getTitle())
                && otherModule.getAssignments().equals(getAssignments())
                && otherModule.getExam().equals(getExam());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, assignments, exam);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("; Assignments: ")
                .append(getAssignments())
                .append("; Exam Date: ")
                .append(getExam());

        return builder.toString();
    }
}
