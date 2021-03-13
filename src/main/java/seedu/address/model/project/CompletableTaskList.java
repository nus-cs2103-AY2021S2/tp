package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.model.task.Completable;

public class CompletableTaskList {

    private final List<Completable> completableTasks = new ArrayList<>();

    /**
     * Constructs a empty {@code CompletableTaskList}.
     */
    public CompletableTaskList() {}

    /**
     * Constructs a {@code CompletableTaskList}.
     *
     * @param completableTasks A list of {@code Tasks}.
     */
    public CompletableTaskList(List<Completable> completableTasks) {
        requireNonNull(completableTasks);

        this.completableTasks.addAll(completableTasks);
    }

    public List<Completable> getCompletableTasks() {
        return completableTasks;
    }

    /**
     * Returns a sequential stream with this {@code CompletableTaskList} as its source.
     * @return a sequential Stream over the completables in this {@code CompletableTaskList}.
     */
    public Stream<Completable> stream() {
        return completableTasks.stream();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompletableTaskList // instanceof handles nulls
                && completableTasks.equals(((CompletableTaskList) other).completableTasks)); // state check
    }

    @Override
    public int hashCode() {
        return completableTasks.hashCode();
    }
}
