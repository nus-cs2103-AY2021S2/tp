package seedu.taskify.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private static boolean showHome = true;
    private static boolean showExpired = false;
    private static boolean showCompleted = false;
    private static boolean showTodays = true;
    private static boolean showUncompleted = false;
    private static final String ALREADY_IN_HOMETAB = "You are already in home tab!";
    private static final String ALREADY_IN_EXPIREDTAB = "You are already in expired tab!";
    private static final String ALREADY_IN_COMPLETEDTAB = "You are already in completed tab!";
    private static final String ALREADY_IN_UNCOMPLETEDTAB = "You are already in uncompleted tab!";

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
        if (CommandResult.showHome) {
            return new CommandResult(ALREADY_IN_HOMETAB);
        }
        CommandResult.showHome = true;
        CommandResult.showExpired = false;
        CommandResult.showCompleted = false;
        CommandResult.showUncompleted = false;
        return newCommand;
    }

    /**
     * Command Result for the user to switch to expired task tab
     * @param feedbackToUser
     * @return commandResult
     */
    public static CommandResult switchToExpired(String feedbackToUser) {
        CommandResult newCommand = new CommandResult(feedbackToUser);
        if (CommandResult.showExpired) {
            return new CommandResult(ALREADY_IN_EXPIREDTAB);
        }
        CommandResult.showHome = false;
        CommandResult.showExpired = true;
        CommandResult.showCompleted = false;
        CommandResult.showUncompleted = false;
        return newCommand;
    }

    /**
     * Command Result for the user to switch to completed task tab
     * @param feedbackToUser
     * @return commandResult
     */
    public static CommandResult switchToCompleted(String feedbackToUser) {
        CommandResult newCommand = new CommandResult(feedbackToUser);
        if (CommandResult.showCompleted) {
            return new CommandResult(ALREADY_IN_COMPLETEDTAB);
        }
        CommandResult.showHome = false;
        CommandResult.showExpired = false;
        CommandResult.showCompleted = true;
        CommandResult.showUncompleted = false;
        return newCommand;
    }

    /**
     * Command Result for the user to switch to uncompleted task tab
     * @param feedbackToUser
     * @return commandResult
     */

    public static CommandResult switchToUncompleted(String feedbackToUser) {
        CommandResult newCommand = new CommandResult(feedbackToUser);
        if (CommandResult.showUncompleted) {
            return new CommandResult(ALREADY_IN_UNCOMPLETEDTAB);
        }
        CommandResult.showHome = false;
        CommandResult.showExpired = false;
        CommandResult.showCompleted = false;
        CommandResult.showUncompleted = true;
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

    public static void setHomeTab() {
        showHome = true;
    }

    public static boolean isHomeTab() {
        return CommandResult.showHome;
    }

    public static boolean isExpiredTab() {
        return CommandResult.showExpired;
    }

    public static boolean isCompletedTab() {
        return CommandResult.showCompleted;
    }

    public static boolean isUncompletedTab() {
        return CommandResult.showUncompleted;
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
