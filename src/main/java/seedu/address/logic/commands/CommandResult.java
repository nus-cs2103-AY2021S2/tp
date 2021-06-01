package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.logic.uicommands.UiCommand;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Information on which UI command to execute **/
    private final UiCommand uiCommand;

    /** Setting this boolean flag to true will ignore history **/
    private boolean ignoreHistory = false;

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code uiCommand}.
     * All other fields are set to their default value.
     */
    public CommandResult (String feedbackToUser, UiCommand uiCommand) {
        requireNonNull(feedbackToUser);
        this.feedbackToUser = feedbackToUser;
        this.uiCommand = uiCommand;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}.
     * All other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        this.feedbackToUser = feedbackToUser;
        this.uiCommand = null;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public UiCommand getUiCommand() {
        return uiCommand;
    }

    /**
     * Returns true if there is a UiCommand.
     *
     * @return true if UiCommand is not null and false otherwise.
     */
    public boolean hasUiCommand() {
        return getUiCommand() != null;
    }

    public boolean isIgnoreHistory() {
        return ignoreHistory;
    }

    /**
     * Sets ignore history flag.
     *
     * @param ignoreHistory new state of ignore history flag.
     * @return this, for method chaining.
     */
    public CommandResult setIgnoreHistory(boolean ignoreHistory) {
        this.ignoreHistory = ignoreHistory;
        return this;
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

        if (uiCommand == null || otherCommandResult.getUiCommand() == null) {
            return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                    && uiCommand == otherCommandResult.getUiCommand();
        } else {
            return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                    && uiCommand.equals(otherCommandResult.getUiCommand());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, uiCommand);
    }

}
