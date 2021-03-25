package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    private final boolean isResidentCommand;
    private final boolean isRoomCommand;
    private final boolean isResidentRoomCommand;

    /**
     * Help information should be shown to the user.
     */
    private final boolean showHelp;

    /**
     * The application should exit.
     */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean isResidentCommand, boolean isRoomCommand,
            boolean isResidentRoomCommand, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isResidentCommand = isResidentCommand;
        this.isRoomCommand = isRoomCommand;
        this.isResidentRoomCommand = isResidentRoomCommand;
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a simpler CommandResult which assumes that the command is neither a resident nor room command.
     * The command therefore can only be a help or exit command.
     *
     * @param feedbackToUser Feedback string to return to user.
     * @param showHelp Indication if command is a help command.
     * @param exit Indication if command is an exit command.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isResidentCommand = false;
        this.isRoomCommand = false;
        this.isResidentRoomCommand = false;
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * Sets the state of this command result to indicate that it is a Resident command
     *
     * @return CommandResult object indicating the result of the executed command
     */
    public CommandResult setResidentCommand() {
        return new CommandResult(this.feedbackToUser, true, false, false, this.showHelp, this.exit);
    }

    /**
     * Indicates if this CommandResult was the result of a Resident command
     *
     * @return CommandResult object indicating the result of the executed command
     */
    public boolean isResidentCommand() {
        return this.isResidentCommand;
    }

    /**
     * Sets the state of this command result to indicate that it is a Room command
     *
     * @return CommandResult object indicating the result of the executed command
     */
    public CommandResult setRoomCommand() {
        return new CommandResult(this.feedbackToUser, false, true, false, this.showHelp, this.exit);
    }

    /**
     * Indicates if this CommandResult was the result of a Room command
     *
     * @return CommandResult object indicating the result of the executed command
     */
    public boolean isRoomCommand() {
        return this.isRoomCommand;
    }

    /**
     * Sets the state of this command result to indicate that it is a Room command
     *
     * @return CommandResult object indicating the result of the executed command
     */
    public CommandResult setResidentRoomCommand() {
        return new CommandResult(this.feedbackToUser, false, false, true, this.showHelp, this.exit);
    }

    /**
     * Indicates if this CommandResult was the result of a Room command
     *
     * @return CommandResult object indicating the result of the executed command
     */
    public boolean isResidentRoomCommand() {
        return this.isResidentRoomCommand;
    }

    public boolean isShowHelp() {
        return showHelp;
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
