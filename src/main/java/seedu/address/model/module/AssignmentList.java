package seedu.address.model.module;

import java.util.ArrayList;

public class AssignmentList {
    private ArrayList<Assignment> assignments;

    public AssignmentList() {
        this.assignments = new ArrayList<>();
    }

    public Assignment get(int index) {
        return assignments.get(index);
    }

    public void add(Assignment assignment) {
        assignments.add(assignment);
    }

    public Assignment delete(int index) {
        Assignment deleted = assignments.remove(index);
        return deleted;
    }

    public int size() {
        return assignments.size();
    }

    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            builder.append(i + 1).append(". ")
                    .append(get(i)).append("\n");
        }
        return builder.toString();
    }
}
