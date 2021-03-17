package seedu.address.model.module;

import java.util.ArrayList;
import java.util.List;

public class AssignmentList {
    public static final String NO_ASSIGNMENTS_OUTPUT = "You have no assignments~  \n";
    private List<Assignment> assignments;

    /**
     * Constructs an {@code AssignmentList} to store {@code Assignments}
     */
    public AssignmentList() {
        this.assignments = new ArrayList<>();
    }

    /**
     * Constructs an {@code AssignmentList} to store {@code Assignments} with given {@code ArrayList<Assignment>};
     *
     * @param assignments assignments to construct the AssignmentList.
     */
    public AssignmentList(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    /**
     * Get the assignment at the index from the list.
     *
     * @param index Index of the assignment.
     * @return Assignment at index.
     */
    public Assignment get(int index) {
        return assignments.get(index);
    }

    /**
     * Gets the index of the {@code assignment} in the assignment list.
     * {@code assignment} must exist in the assignment list.
     */
    public int getIndex(Assignment assignment) {
        int index = -1;
        for (int i = 0; i < assignments.size(); i++) {
            if (assignments.get(i).equals(assignment)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Adds an assignment to the list.
     *
     * @param assignment Assignment to be added.
     */
    public void add(Assignment assignment) {
        assignments.add(assignment);
    }

    /**
     * Removes the assignment at the index from the list.
     *
     * @param index Index of assignment to be removed.
     * @return Removed assignment.
     */
    public Assignment delete(int index) {
        Assignment deleted = assignments.remove(index);
        return deleted;
    }

    /**
     * Delete the {@code assignment} from assignment list.
     * {@code assignment} must exist in the assignment list.
     */
    public Assignment delete(Assignment assignment) {
        int index = getIndex(assignment);
        return delete(index);
    }

    /**
     * Checks if the assignment list contains the given assignment.
     *
     * @param assignment Assignment to check.
     * @return Boolean.
     */
    public boolean contains(Assignment assignment) {
        boolean hasAssignment = false;
        for (int i = 0; i < assignments.size() && !hasAssignment; i++) {
            if (assignments.get(i).equals(assignment)) {
                hasAssignment = true;
            }
        }
        return hasAssignment;
    }

    public List<Assignment> getAssignments() {
        return new ArrayList<>(assignments);
    }

    /**
     * Gets the size of the list.
     *
     * @return List size.
     */
    public int size() {
        return assignments.size();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AssignmentList)) {
            return false;
        }

        AssignmentList otherList = (AssignmentList) other;
        if (assignments.size() != otherList.size()) {
            return false;
        } else {
            for (int i = 0; i < otherList.size(); i++) {
                if (!assignments.get(i).equals(otherList.assignments.get(i))) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        if (size() == 0) {
            return NO_ASSIGNMENTS_OUTPUT;
        }
        builder.append("Assignment: \n");
        for (int i = 0; i < size(); i++) {
            builder.append(i + 1).append(". ")
                    .append(get(i)).append("\n");
        }
        return builder.toString();
    }
}
