package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;

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
     * @param completableTasks A list of {@code Person}.
     */
    public CompletableTaskList(List<Completable> completableTasks) {
        requireAllNonNull(completableTasks);

        this.completableTasks.addAll(completableTasks);
    }

    public List<Completable> getCompletableTasks() {
        return completableTasks;
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
