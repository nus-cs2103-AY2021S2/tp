package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.model.task.CompletableDeadline;

/**
 * Represents a list of Deadlines.
 */
public class DeadlineList {

    private final List<CompletableDeadline> deadlines = new ArrayList<>();

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

    public List<CompletableDeadline> getCompletableTasks() {
        return this.deadlines;
    }

    /**
     * Returns a sequential stream with this {@code DeadlineList} as its source.
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
