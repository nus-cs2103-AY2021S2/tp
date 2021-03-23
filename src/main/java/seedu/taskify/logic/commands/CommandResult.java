package seedu.taskify.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private static List<Boolean> showTab = Arrays.asList(false, false, false);
    private static int showHome = 0;
    private static int showExpired = 1;
    private static int showCompleted = 2;

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;



    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Command Result for the user to switch to home tab
     * @param feedbackToUser
     * @return commandResult
     */
    public static CommandResult switchToHome(String feedbackToUser) {
        CommandResult newCommand = new CommandResult(feedbackToUser);
        CommandResult.showTab = Arrays.asList(true, false, false);
        return newCommand;
    }

    /**
     * Command Result for the user to switch to expired task tab
     * @param feedbackToUser
     * @return commandResult
     */
    public static CommandResult switchToExpired(String feedbackToUser) {
        CommandResult newCommand = new CommandResult(feedbackToUser);
        CommandResult.showTab = Arrays.asList(false, true, false);
        return newCommand;
    }

    /**
     * Command Result for the user to switch to Completed task tab
     * @param feedbackToUser
     * @return commandResult
     */
    public static CommandResult switchToCompleted(String feedbackToUser) {
        CommandResult newCommand = new CommandResult(feedbackToUser);
        CommandResult.showTab = Arrays.asList(false, false, true);
        return newCommand;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public static boolean isHomeTab() {
        return CommandResult.showTab.get(showHome);
    }

    public static boolean isExpiredTab() {
        return CommandResult.showTab.get(showExpired);
    }

    public static boolean isCompletedTab() {
        return CommandResult.showTab.get(showCompleted);
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
