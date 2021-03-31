package seedu.smartlib.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     *
     * @param feedbackToUser a String containing the feedback that will be provided to the user.
     * @param showHelp true if the command is a HelpCommand, and false otherwise.
     * @param exit true if the command is an ExitCommand, and false otherwise.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     *
     * @param feedbackToUser the feedback to be provided to the user.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    /**
     * Returns the feedback to be provided to the user.
     *
     * @return the feedback to be provided to the user.
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * Indicates whether the command is a HelpCommand.
     *
     * @return true if the command is a HelpCommand, and false otherwise.
     */
    public boolean isShowHelp() {
        return showHelp;
    }

    /**
     * Indicates whether the command is an ExitCommand.
     *
     * @return true if the command is an ExitCommand, and false otherwise.
     */
    public boolean isExit() {
        return exit;
    }

    /**
     * Checks if this CommandResult is equal to another CommandResult.
     *
     * @param other the other CommandResult to be compared.
     * @return true if this CommandResult is equal to the other CommandResult, and false otherwise.
     */
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

    /**
     * Generates a hashcode for this CommandResult.
     *
     * @return the hashcode for this CommandResult.
     */
    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

}
