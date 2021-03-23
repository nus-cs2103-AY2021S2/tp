package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.task.CompletableDeadline;
import seedu.address.model.task.deadline.Deadline;

/**
 * Represents a list of Deadlines.
 */
public class DeadlineList {

    private final ObservableList<CompletableDeadline> deadlines = FXCollections.observableArrayList();

    /**
     * Constructs a empty {@code DeadlineList}.
     */
    public DeadlineList() {}

    /**
     * Constructs a {@code DeadlineList}.
     *
     * @param deadlines A list of {@code deadlines}.
     */
    public DeadlineList(List<CompletableDeadline> deadlines) {
        requireNonNull(deadlines);

        this.deadlines.addAll(deadlines);
    }

    /**
     * Adds a deadline to this {@code DeadlineList}.
     *
     * @param deadline {@code Deadline} to add.
     */
    public void addDeadline(Deadline deadline) {
        requireNonNull(deadline);
        this.deadlines.add(deadline);
    }

    /**
     * Returns {@code deadlines} as an {@code ObservableList<CompletableDeadline>}
     *
     * @return An {@code ObservableList<CompletableDeadline>}
     */
    public ObservableList<CompletableDeadline> getDeadlines() {
        return this.deadlines;
    }

    /**
     * Deletes a deadline from this {@code DeadlineList}
     * .
     * @param i Index of {@code Deadline} to be deleted.
     */
    public void deleteDeadline(Integer i) {
        requireNonNull(i);
        this.deadlines.remove((int) i);
    }

    /**
     * Marks a deadline from this {@code DeadlineList} as done.
     *
     * @param i Index of {@code Deadline} to be marked as done.
     */
    public void markAsDone(Integer i) {
        requireNonNull(i);
        CompletableDeadline deadline = deadlines.get(i);
        deadline.markAsDone();

        // Force observable list to update
        this.deadlines.set(i, deadline);
    }

    /**
     * Returns a copy of this {@code DeadLineList}
     *
     * @return A copy of this {@code DeadlineList}
     */
    public DeadlineList getCopy() {
        return new DeadlineList(getDeadlines());
    }

    /**
     * Returns all {@code CompletableDeadline} that fall on a specific {@code LocalDate}
     *
     * @param dateOfEvent The {@code LocalDate} which the deadlines occur on.
     * @return A {@code FilteredList<CompletableDeadline>}
     */
    public FilteredList<CompletableDeadline> getDeadlinesOnDate(LocalDate dateOfEvent) {
        Predicate<CompletableDeadline> predicate = deadline -> deadline.getBy().isEqual(dateOfEvent);
        return deadlines.filtered(predicate);
    }

    /**
     * Returns a sequential stream with this {@code DeadlineList} as its source.
     *
     * @return a sequential Stream over the completables in this {@code DeadlineList}.
     */
    public Stream<CompletableDeadline> stream() {
        return deadlines.stream();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeadlineList // instanceof handles nulls
                && deadlines.equals(((DeadlineList) other).deadlines)); // state check
    }

    @Override
    public int hashCode() {
        return deadlines.hashCode();
    }
}
