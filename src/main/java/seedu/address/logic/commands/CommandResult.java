package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.model.flashcard.Statistics;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {
    private static final int DEFAULT_INDEX = -1;
    private static final Statistics DEFAULT_STATS = new Statistics();
    private static final Optional<Index> DEFAULT_STATS_INDEX = Optional.empty();
    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Turn on the view mode.
     */
    private final boolean showView;

    /**
     * Show statistics of the flash card(s).
     */
    private final boolean showStats;

    /**
     * Get the index of the card to show to the user.
     */
    private final int index;

    /**
     * Statistics of the flash card(s).
     */
    private final Statistics stats;

    /**
     * Either contain an index of a flash card, or does not contain any index.
     */
    private final Optional<Index> statsIndex;

    /**
     * The application enters review mode.
     */
    private final boolean reviewMode;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean reviewMode) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.showView = false;
        this.showStats = false;
        this.index = DEFAULT_INDEX;
        this.stats = DEFAULT_STATS;
        this.statsIndex = DEFAULT_STATS_INDEX;
        this.reviewMode = reviewMode;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields {@code feedbackToUser, index},
     * and other fields set to their default value.
     * Uses for View Command.
     */
    public CommandResult(String feedbackToUser, int index) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.showView = true;
        this.showStats = false;
        this.index = index;
        this.stats = DEFAULT_STATS;
        this.statsIndex = DEFAULT_STATS_INDEX;
        this.reviewMode = false;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields {@code feedbackToUser, statsIndex, stats},
     * and other fields set to their default value.
     * Used for the Stats Command.
     */
    public CommandResult(String feedbackToUser, Optional<Index> statsIndex, Statistics stats) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.showView = false;
        this.showStats = true;
        this.index = DEFAULT_INDEX;
        this.stats = stats;
        this.statsIndex = statsIndex;
        this.reviewMode = false;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public int getIndex() {
        return index;
    }

    public Statistics getStats() {
        return stats;
    }

    public Optional<Index> getStatsIndex() {
        return statsIndex;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isShowView() {
        return showView;
    }

    public boolean isShowStats() {
        return showStats;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isReviewMode() {
        return reviewMode;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

}
