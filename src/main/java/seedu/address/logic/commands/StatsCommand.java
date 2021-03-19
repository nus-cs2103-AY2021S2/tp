package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.flashcard.Flashcard;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class StatsCommand extends Command {
    public static final String COMMAND_WORD = "stats";
    public static final String MESSAGE_SHOW_CARD_STATS_SUCCESS = "Statistics of the flash card is shown:\n%1$s";
    public static final String MESSAGE_SHOW_LIST_STATS_SUCCESS = "Statistics of all flashcard in list is shown:"
            + "\nTotal Review count: %d"
            + "\nTotal Correct Count: %d"
            + "\nOverall Correct Rate: %.1f%%";
    public static final String MESSAGE_USAGE = "Display statistics of the flashcard identified by the "
            + "index number used in the displayed flashcard list. "
            + "\n" + "Parameters: INDEX (must be a positive integer)"
            + "\n" + "Example: " + COMMAND_WORD + " 1"
            + "\n" + "The index can be omitted to show the statistics of the entire card list";

    private final Optional<Index> cardIndex;

    public StatsCommand(Optional<Index> cardIndex) {
        this.cardIndex = cardIndex;
    }

    private int computeTotalReviewCount(List<Flashcard> cardList) {
        int totalReviewCount = 0;
        for (Flashcard card : cardList) {
            totalReviewCount += card.getStats().getReviewCount();
        }
        return totalReviewCount;
    }

    private int computeTotalCorrectCount(List<Flashcard> cardList) {
        int totalCorrectCount = 0;
        for (Flashcard card : cardList) {
            totalCorrectCount += card.getStats().getCorrectCount();
        }
        return totalCorrectCount;
    }

    private double computeOverallCorrectRate(int totalReviewCount, int totalCorrectCount) {
        return (double) totalCorrectCount / totalReviewCount * 100;
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
            return new CommandResult(String.format(MESSAGE_SHOW_CARD_STATS_SUCCESS, shownCard.getStats()),
                    cardIndex);
        } else {
            int totalReviewCount = computeTotalReviewCount(cardList);
            int totalCorrectCount = computeTotalCorrectCount(cardList);
            double overallCorrectRate = computeOverallCorrectRate(totalReviewCount, totalCorrectCount);
            return new CommandResult(String.format(MESSAGE_SHOW_LIST_STATS_SUCCESS, totalReviewCount,
                    totalCorrectCount, overallCorrectRate), cardIndex);
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
