package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.model.task.CompletableTodo;

public class CompletableTaskList {

    private final List<CompletableTodo> completableTodoTasks = new ArrayList<>();

    /**
     * Constructs a empty {@code CompletableTaskList}.
     */
    public CompletableTaskList() {}

    /**
     * Constructs a {@code CompletableTaskList}.
     *
     * @param completableTodoTasks A list of {@code Tasks}.
     */
    public CompletableTaskList(List<CompletableTodo> completableTodoTasks) {
        requireNonNull(completableTodoTasks);

        this.completableTodoTasks.addAll(completableTodoTasks);
    }

    public List<CompletableTodo> getCompletableTasks() {
        return completableTodoTasks;
    }

    /**
     * Returns a sequential stream with this {@code CompletableTaskList} as its source.
     * @return a sequential Stream over the completables in this {@code CompletableTaskList}.
     */
    public Stream<CompletableTodo> stream() {
        return completableTodoTasks.stream();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompletableTaskList // instanceof handles nulls
                && completableTodoTasks.equals(((CompletableTaskList) other).completableTodoTasks)); // state check
    }

    @Override
    public int hashCode() {
        return completableTodoTasks.hashCode();
    }
}
