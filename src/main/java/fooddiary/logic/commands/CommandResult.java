package fooddiary.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Objects;

import fooddiary.model.entry.Entry;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** A view of the specified entry should be shown to the user. */
    private final boolean viewEntry;

    private final Entry entry;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields,
     * used for help command
     */
    public CommandResult(Entry entry, String feedbackToUser, boolean showHelp,
                         boolean viewEntry, boolean exit) {
        this.entry = entry;
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.viewEntry = viewEntry;
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(null, feedbackToUser, false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isViewEntry() {
        return viewEntry;
    }

    public Entry getEntry() {
        return entry;
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
