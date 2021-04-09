package seedu.address.logic.commands;

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
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
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
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isViewPerson() {
        return feedbackToUser.equals(ViewPersonCommand.MESSAGE_SUCCESS);
    }

    public boolean isViewSession() {
        return feedbackToUser.equals(ViewSessionCommand.MESSAGE_SUCCESS);
    }

    public boolean isAddSession() {
        return feedbackToUser.contains(AddSessionCommand.MESSAGE_SUCCESS);
    }

    public boolean isEditSession() {
        return feedbackToUser.contains(EditSessionCommand.MESSAGE_EDIT_SESSION_SUCCESS);
    }

    public boolean isDeleteSession() {
        return feedbackToUser.contains(DeleteSessionCommand.MESSAGE_DELETE_SESSION_SUCCESS);
    }

    /**
     * Checks if feedback is equivalent to either feedback for listing empty session list or for non-empty session list.
     */
    public boolean isListSession() {
        boolean nonEmptySessionList = feedbackToUser.equals(ListCommand.MESSAGE_SUCCESS_SESSIONS);
        boolean emptySessionList = feedbackToUser.equals(ListCommand.MESSAGE_EMPTY_SESSION_LIST);
        return nonEmptySessionList || emptySessionList;
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
