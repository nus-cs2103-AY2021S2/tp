package seedu.address.logic.commandhistory;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.function.Supplier;

import seedu.address.commons.util.MathUtil;
import seedu.address.model.commandhistory.ReadOnlyCommandHistory;

/**
 * An implementation of {@code CommandHistorySelector} that relies on a supplier of command history.
 */
public class SuppliedCommandHistorySelector implements CommandHistorySelector {
    private final Supplier<ReadOnlyCommandHistory> commandHistorySupplier;
    private int commandHistoryIndex;

    /**
     * Constructs a {@code SuppliedCommandHistorySelector} that uses the input command history supplier
     * for selection.
     *
     * @param commandHistorySupplier The supplier of command history.
     * @throws NullPointerException If {@code commandHistorySupplier} is null.
     */
    public SuppliedCommandHistorySelector(Supplier<ReadOnlyCommandHistory> commandHistorySupplier) {
        requireNonNull(commandHistorySupplier);
        this.commandHistorySupplier = commandHistorySupplier;
    }

    /**
     * Sets the currently 'selected' entry to one past the most recent entry.
     * A subsequent call to {@code selectPrevious} will select the last entry.
     */
    @Override
    public void navigateToOnePastLast() {
        assert commandHistorySupplier != null && commandHistorySupplier.get() != null;
        final int onePastLast = commandHistorySupplier.get().size();
        selectAt(onePastLast);
    }

    /**
     * Selects the next command text and returns it, if any. If the last entry is selected before
     * this method is called, then {@code Optional.empty()} will be returned. The one-past-last 'element'
     * will be the current entry after this call.
     *
     * @return The next command text, if any.
     */
    @Override
    public Optional<String> selectNextUntilOnePastLast() {
        return selectAt(commandHistoryIndex + 1);
    }

    /**
     * Selects the previous command text and returns it, if any. If the first entry is selected before this
     * method is called, then {@code Optional.of} the first entry will be returned, and remain selected
     * after this call.
     *
     * @return The previous command text, if any.
     */
    @Override
    public Optional<String> selectPreviousUntilFirst() {
        return selectAt(commandHistoryIndex - 1);
    }

    /**
     * Selects the command text at the given index and returns it, if any. The selection index will be
     * bound to the range [0, history size]. Only indices in the range [0, history size - 1] will return
     * valid entries. Outside that range, {@code Optional.empty()} is returned.
     *
     * @param index The index to try to select.
     * @return The entry at the index, if any.
     */
    private Optional<String> selectAt(int index) {
        assert commandHistorySupplier != null && commandHistorySupplier.get() != null
                && commandHistorySupplier.get().size() >= 0;
        final ReadOnlyCommandHistory history = commandHistorySupplier.get();
        final int size = history.size();
        commandHistoryIndex = MathUtil.clamp(index, 0, size);
        if (commandHistoryIndex >= size) {
            return Optional.empty();
        }

        return Optional.of(history.get(commandHistoryIndex).value);
    }
}
