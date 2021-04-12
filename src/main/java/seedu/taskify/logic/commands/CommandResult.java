package seedu.taskify.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private static ArrayList<Boolean> showTabBoolean = new ArrayList<>(Arrays.asList(true, false, false, false));
    private static final int HOME = 0;
    private static final int EXPIRED = 1;
    private static final int COMPLETED = 2;
    private static final int UNCOMPLETED = 3;
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
     * Set all showTab attributes to false
     */

    public static void resetShowTabToFalse() {
        assert showTabBoolean != null;
        showTabBoolean = new ArrayList<>(Arrays.asList(false, false, false, false));
    }

    public static String inSameTabErrorMessage(String tab) {
        return "You are already in " + tab + " tab!";
    }

    public static ArrayList<Boolean> getTabBoolean() {
        return CommandResult.showTabBoolean;
    }

    public static boolean isHomeTab() {
        return showTabBoolean.get(HOME);
    }

    /**
     * Command Result for the user to switch to home tab
     * @param feedbackToUser
     * @return commandResult
     */
    public static CommandResult switchToHome(String feedbackToUser) {
        CommandResult newCommand = new CommandResult(feedbackToUser);
        if (showTabBoolean.get(HOME)) {
            return new CommandResult(CommandResult.inSameTabErrorMessage("home"));
        }
        resetShowTabToFalse();
        showTabBoolean.set(HOME, true);
        return newCommand;
    }

    /**
     * Command Result for the user to switch to expired task tab
     * @param feedbackToUser
     * @return commandResult
     */
    public static CommandResult switchToExpired(String feedbackToUser) {
        CommandResult newCommand = new CommandResult(feedbackToUser);
        if (showTabBoolean.get(EXPIRED)) {
            return new CommandResult(CommandResult.inSameTabErrorMessage("expired"));
        }
        resetShowTabToFalse();
        showTabBoolean.set(EXPIRED, true);
        return newCommand;
    }

    /**
     * Command Result for the user to switch to completed task tab
     * @param feedbackToUser
     * @return commandResult
     */
    public static CommandResult switchToCompleted(String feedbackToUser) {
        CommandResult newCommand = new CommandResult(feedbackToUser);
        if (showTabBoolean.get(COMPLETED)) {
            return new CommandResult(CommandResult.inSameTabErrorMessage("completed"));
        }
        resetShowTabToFalse();
        showTabBoolean.set(COMPLETED, true);
        return newCommand;
    }

    /**
     * Command Result for the user to switch to uncompleted task tab
     * @param feedbackToUser
     * @return commandResult
     */

    public static CommandResult switchToUncompleted(String feedbackToUser) {
        CommandResult newCommand = new CommandResult(feedbackToUser);
        if (showTabBoolean.get(UNCOMPLETED)) {
            return new CommandResult(CommandResult.inSameTabErrorMessage("uncompleted"));
        }
        resetShowTabToFalse();
        showTabBoolean.set(UNCOMPLETED, true);
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
        showTabBoolean.set(HOME, true);
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
