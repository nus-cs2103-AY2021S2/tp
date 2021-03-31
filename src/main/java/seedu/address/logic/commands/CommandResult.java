package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /**
     * Help information should be shown to the user.
     */
    private final boolean shouldShowHelp;

    /**
     * The application should exit.
     */
    private final boolean shouldExit;

    /**
     * Constructs a simpler CommandResult which assumes that the command is neither a resident nor room command.
     * The command therefore can only be a help or exit command.
     *
     * @param feedbackToUser Feedback string to return to user.
     * @param shouldShowHelp Indication if command is a help command.
     * @param shouldExit Indication if command is an exit command.
     */
    public CommandResult(String feedbackToUser, boolean shouldShowHelp, boolean shouldExit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.shouldShowHelp = shouldShowHelp;
        this.shouldExit = shouldExit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return shouldShowHelp;
    }

    public boolean isExit() {
        return shouldExit;
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
                && shouldShowHelp == otherCommandResult.shouldShowHelp
                && shouldExit == otherCommandResult.shouldExit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, shouldShowHelp, shouldExit);
    }

}
