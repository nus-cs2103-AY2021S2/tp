package seedu.address.model.module;

import java.util.ArrayList;

public class AssignmentList {
    private ArrayList<Assignment> assignments;

    /**
     * Constructs an {@code AssignmentList} to store {@code Assignments}
     */
    public AssignmentList() {
        this.assignments = new ArrayList<>();
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
     * Gets the size of the list.
     *
     * @return List size.
     */
    public int size() {
        return assignments.size();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            builder.append(i + 1).append(". ")
                    .append(get(i)).append("\n");
        }
        return builder.toString();
    }
}
