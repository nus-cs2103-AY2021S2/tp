package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.core.DetailsPanelTab;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /**
     * Help information should be shown to the user.
     */
    private final boolean showHelp;

    /**
     * The application should exit.
     */
    private final boolean exit;

    /**
     * The new tab to be displayed on the {@code DetailsPanel}.
     */
    private final DetailsPanelTab tab;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    private CommandResult(String feedbackToUser, boolean showHelp, boolean exit, DetailsPanelTab tab) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.tab = tab;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, showHelp, exit, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, DetailsPanelTab tab) {
        this(feedbackToUser, false, false, tab);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, null);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public DetailsPanelTab getNewTab() {
        return tab;
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
                && exit == otherCommandResult.exit
                && tab == otherCommandResult.tab;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, tab);
    }

    @Override
    public String toString() {
        return "CommandResult{"
                + "feedbackToUser='" + feedbackToUser + "'"
                + ", showHelp=" + showHelp
                + ", exit=" + exit
                + ", tab=" + tab
                + "}";
    }
}
