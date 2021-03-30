package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.flashcard.Statistics;

/**
 * Displays a flashcard's statistics using it's displayed index from the FlashBack,
 * or display the statistics of all flashcards in the current flash card list.
 */

public class StatsCommand extends Command {
    public static final String COMMAND_WORD = "stats";
    public static final String MESSAGE_SHOW_CARD_STATS_SUCCESS = "Statistics of the flash card is shown";
    public static final String MESSAGE_SHOW_LIST_STATS_SUCCESS = "Statistics of all flashcard(s) in list is shown";
    public static final String MESSAGE_USAGE = "Display statistics of the flashcard identified by the "
            + "index number used in the displayed flashcard list. "
            + "\n" + "Parameters: INDEX (must be a positive integer)"
            + "\n" + "Example: " + COMMAND_WORD + " 1"
            + "\n" + "The index can be omitted to show the statistics of the entire card list";

    private final Optional<Index> cardIndex;

    /**
     * Constructs a {@code StatsCommand} to display the statistics of the flashcard(s).
     *
     * @param cardIndex An optional index, depending on whether the user wants to show statistics of a specific card,
     *                  or show statistics of the entire card list.
     */
    public StatsCommand(Optional<Index> cardIndex) {
        this.cardIndex = cardIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Flashcard> cardList = model.getFilteredFlashcardList();
        if (cardIndex.isPresent()) {
            Index idx = cardIndex.get();
            if (idx.getZeroBased() >= cardList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
            }
            Flashcard shownCard = cardList.get(idx.getZeroBased());
            Statistics cardStats = shownCard.getStats();
            assert(cardStats != null);
            assert(Statistics.isValidStats(cardStats));
            return new CommandResult(MESSAGE_SHOW_CARD_STATS_SUCCESS, cardIndex, cardStats);
        } else {
            Statistics listStats = new Statistics(cardList);
            return new CommandResult(MESSAGE_SHOW_LIST_STATS_SUCCESS, cardIndex, listStats);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof StatsCommand)) {
            return false;
        } else {
            StatsCommand other = (StatsCommand) obj;
            return this.cardIndex.equals(other.cardIndex);
        }
    }
}
