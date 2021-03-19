package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {
    private static final int DEFAULT_INDEX = -1;

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Turn on the view mode.
     */
    private final boolean showView;

    private final boolean showStats;

    /**
     * Get the index of the card to show to the user.
     */
    private final int index;

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
        this.reviewMode = false;
    }

    public CommandResult(String feedbackToUser, Optional<Index> index) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.showView = false;
        this.showStats = true;
        this.index = index.isPresent() ? index.get().getZeroBased() : DEFAULT_INDEX;
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

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isShowView()  {
        return showView;
    }

    public boolean isShowStats() {
        return showStats;
    }

    public boolean isExit() {
        return exit;
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
