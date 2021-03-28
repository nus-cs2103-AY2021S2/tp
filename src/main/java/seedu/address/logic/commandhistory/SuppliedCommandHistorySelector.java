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
     */
    public SuppliedCommandHistorySelector(Supplier<ReadOnlyCommandHistory> commandHistorySupplier) {
        requireNonNull(commandHistorySupplier);
        this.commandHistorySupplier = commandHistorySupplier;
    }

    /**
     * Selects the most recent command text and returns it, if any. This is stateful.
     *
     * @return The most recent command text, if any.
     */
    @Override
    public Optional<String> selectLast() {
        final int last = commandHistorySupplier.get().size();
        return selectAt(last);
    }

    /**
     * Selects the next command text and returns it, if any. This is stateful.
     *
     * @return The next command text, if any.
     */
    @Override
    public Optional<String> selectNext() {
        return selectAt(commandHistoryIndex + 1);
    }

    /**
     * Selects the previous command text and returns it, if any. This is stateful.
     *
     * @return The previous command text, if any.
     */
    @Override
    public Optional<String> selectPrevious() {
        return selectAt(commandHistoryIndex - 1);
    }

    private Optional<String> selectAt(int index) {
        final ReadOnlyCommandHistory history = commandHistorySupplier.get();
        final int size = history.size();
        commandHistoryIndex = MathUtil.clamp(index, 0, size);
        if (commandHistoryIndex < 0 || commandHistoryIndex >= size) {
            return Optional.empty();
        }

        return Optional.of(history.get(commandHistoryIndex).value);
    }
}
