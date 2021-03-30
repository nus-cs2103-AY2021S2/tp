package fooddiary.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import fooddiary.commons.core.index.Index;
import fooddiary.model.entry.Entry;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    private final Entry entry;

    private final Index index;

    /** A view of the specified entry should be shown to the user. */
    private final boolean enableView;

    /** A window for revision of the specified entry should be shown to the user. */
    private final boolean enableRevise;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields,
     * used for help, view, revise, exit
     */
    public CommandResult(Entry entry, Index index, String feedbackToUser, boolean showHelp,
                         boolean enableView, boolean enableRevise, boolean exit) {
        this.entry = entry;
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.enableView = enableView;
        this.index = index;
        this.enableRevise = enableRevise;
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(null, null, feedbackToUser, false, false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isEnableView() {
        return enableView;
    }

    public boolean isReviseEntry() {
        return enableRevise;
    }

    public Entry getEntry() {
        return entry;
    }

    public Index getIndex() {
        return index;
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
        return entry == otherCommandResult.entry
                && index == otherCommandResult.index
                && feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && enableView == otherCommandResult.enableView
                && enableRevise == otherCommandResult.enableRevise
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(entry, index, feedbackToUser, showHelp, enableView, enableRevise, exit);
    }

}
