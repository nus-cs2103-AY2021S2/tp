package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.Command.TabName;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** Timetable shown to the user. */
    private final boolean showTimetable;

    /** To allow toggling of tabs via commands */
    private final Optional<TabName> tabName;

    /**
     * Default Constructor: Constructs a {@code CommandResult} with the specified fields.
     */

    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.showTimetable = false;
        this.tabName = Optional.empty();
    }

    /**
     * Constructs a {@code CommandResult} to allows specifying of {@code showTimetable}.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean showTimetable) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.showTimetable = showTimetable;
        this.tabName = Optional.empty();
    }

    /**
     * Constructs a {@code CommandResult} to allows specifying of {@code showTimetable}.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, TabName tabName) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.showTimetable = false;
        this.tabName = Optional.of(tabName);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code tabName},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, TabName tabName) {
        this(feedbackToUser, false, false, tabName);
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

    public boolean isShowTimetable() {
        return showTimetable;
    }

    public Optional<TabName> getTabName() {
        return tabName;
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
                && showTimetable == otherCommandResult.showTimetable;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, showTimetable);
    }

}
