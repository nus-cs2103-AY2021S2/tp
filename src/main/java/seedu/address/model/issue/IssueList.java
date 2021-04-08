package seedu.address.model.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.issue.exceptions.DuplicateIssueException;
import seedu.address.model.issue.exceptions.IssueNotFoundException;
import seedu.address.model.room.Room;

/**
 * A list of issues that does not allow nulls.
 *
 * Supports a minimal set of list operations.
 */
public class IssueList implements Iterable<Issue> {

    private final Logger logger = LogsCenter.getLogger(IssueList.class);
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
        assert internalList != null;
        requireNonNull(toAdd);

        if (contains(toAdd)) {
            logger.warning("Attempted to add duplicate issue");
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
     *
     * @param target      Issue to be replaced.
     * @param editedIssue The new issue to replace {@code target}.
     * @throws NullPointerException If {@code target} or {@editedIssue} is null.
     */
    public void setIssue(Issue target, Issue editedIssue) {
        assert internalList != null;
        requireAllNonNull(target, editedIssue);

        int index = internalList.indexOf(target);
        if (index == -1) {
            logger.warning("Failed to find target issue to be replaced");
            throw new IssueNotFoundException();
        }

        if (!target.equals(editedIssue) && internalList.contains(editedIssue)) {
            logger.warning("Attempted to add duplicate issue");
            throw new DuplicateIssueException();
        }

        internalList.set(index, editedIssue);
        FXCollections.sort(internalList);
    }

    /**
     * Removes the equivalent issue from the list.
     * The issue must exist in the list.
     *
     * @param toRemove Issue to be removed.
     * @throws NullPointerException If {@code toRemove} is null.
     */
    public void remove(Issue toRemove) {
        assert internalList != null;
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            logger.warning("Failed to find target issue to be removed");
            throw new IssueNotFoundException();
        }
    }

    /**
     * Replaces the existing issues with the issues from {@code replacement}.
     *
     * @param replacement The {@IssueList} to be replaced with.
     * @throws NullPointerException If {@code replacement} is null.
     */
    public void setIssues(IssueList replacement) {
        assert internalList != null;
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        FXCollections.sort(internalList);
    }

    /**
     * Replaces the contents of this list with {@code issues}.
     * {@code issues} must not contain duplicate issues.
     *
     * @param issues List of issues to be replaced with.
     * @throws DuplicateIssueException If {@code issues} contains duplicate issues.
     * @throws NullPointerException    If {@code replacement} is null.
     */
    public void setIssues(List<Issue> issues) {
        assert internalList != null;
        requireAllNonNull(issues);

        if (!issuesAreUnique(issues)) {
            logger.warning("List of issues provided contain duplicate issues");
            throw new DuplicateIssueException();
        }

        internalList.setAll(issues);
        FXCollections.sort(internalList);
    }

    /**
     * Checks if any issues have the given room associated with it.
     *
     * @param target Room to check if it has issues associated with it.
     * @return {@code True} if there are issues with the given room associated with it.
     */
    public boolean containsRoom(Room target) {
        assert internalList != null;
        return internalList.stream()
                .anyMatch(issue -> issue.getRoomNumber().value.equals(target.getRoomNumber().roomNumber));
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Issue> asUnmodifiableObservableList() {
        assert internalUnmodifiableList != null;
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Issue> iterator() {
        assert internalList != null;
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
        assert internalList != null;
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code issues} contains only unique issues.
     */
    private boolean issuesAreUnique(List<Issue> issues) {
        assert internalList != null;
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
