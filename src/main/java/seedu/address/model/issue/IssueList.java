package seedu.address.model.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.issue.exceptions.IssueNotFoundException;

/**
 * A list of issues that does not allow nulls.
 *
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

        internalList.setAll(issues);
        FXCollections.sort(internalList);
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

}
