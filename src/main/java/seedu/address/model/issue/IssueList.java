package seedu.address.model.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.issue.exceptions.DuplicateIssueException;
import seedu.address.model.issue.exceptions.IssueNotFoundException;
import seedu.address.model.room.Room;

/**
 * A list of issues that does not allow nulls.
 * <p>
 * Supports a minimal set of list operations.
 */
public class IssueList implements Iterable<Issue> {

    private final ObservableList<Issue> internalList = FXCollections.observableArrayList();
    private final ObservableList<Issue> internalUnmodifiableList = FXCollections
            .unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent issue as the given argument.
     */
    public boolean contains(Issue toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a issue to the list.
     * The issue must not already exist in the list.
     */
    public void add(Issue toAdd) {
        requireNonNull(toAdd);

        if (contains(toAdd)) {
            throw new DuplicateIssueException();
        }

        internalList.add(toAdd);
        FXCollections.sort(internalList);
    }

    /**
     * Replaces the issue {@code target} in the list with {@code editedIssue}.
     * {@code target} must exist in the list.
     * The issue identity of {@code editedIssue} must not be the same as another
     * existing issue in the list.
     */
    public void setIssue(Issue target, Issue editedIssue) {
        requireAllNonNull(target, editedIssue);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new IssueNotFoundException();
        }

        if (!target.equals(editedIssue) && internalList.contains(editedIssue)) {
            throw new DuplicateIssueException();
        }

        internalList.set(index, editedIssue);
        FXCollections.sort(internalList);
    }

    /**
     * Removes the equivalent issue from the list.
     * The issue must exist in the list.
     */
    public void remove(Issue toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new IssueNotFoundException();
        }
    }

    public void setIssues(IssueList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        FXCollections.sort(internalList);
    }

    /**
     * Replaces the contents of this list with {@code issues}.
     * {@code issues} must not contain duplicate issues.
     */
    public void setIssues(List<Issue> issues) {
        requireAllNonNull(issues);

        if (!issuesAreUnique(issues)) {
            throw new DuplicateIssueException();
        }

        internalList.setAll(issues);
        FXCollections.sort(internalList);
    }

    /**
     * Checks if any issues have the given room associated with it
     *
     * @param target Room to check if it has issues associated with it.
     * @return True if there are issues with the given room associated with it.
     */
    public boolean containsRoom(Room target) {
        return internalList.stream().anyMatch(issue ->
                issue.getRoomNumber().value.equals(target.getRoomNumber().roomNumber));
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Issue> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Issue> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IssueList // instanceof handles nulls
                && internalList.equals(((IssueList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code issues} contains only unique issues.
     */
    private boolean issuesAreUnique(List<Issue> issues) {
        for (int i = 0; i < issues.size() - 1; i++) {
            for (int j = i + 1; j < issues.size(); j++) {
                if (issues.get(i).equals(issues.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
