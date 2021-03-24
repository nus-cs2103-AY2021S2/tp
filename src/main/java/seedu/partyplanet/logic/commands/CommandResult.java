package seedu.partyplanet.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** The application should exit. */
    private final boolean exit;

    /** The application should switch to this theme */
    private final List<String> theme;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.exit = exit;
        theme = null;
    }

    /**
     * Constructs a {@code CommandResult} including the theme.
     */
    public CommandResult(String feedbackToUser, boolean exit, List<String> theme) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.exit = exit;
        this.theme = theme;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isToggleTheme() {
        return theme != null;
    }

    public List<String> getTheme() {
        return theme;
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
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, exit);
    }

}
