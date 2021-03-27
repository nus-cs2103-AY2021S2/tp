package seedu.us.among.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.us.among.model.endpoint.Endpoint;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** API response should be shown to the user. */
    private final boolean isApiResponse;

    /** List should be shown to the user */
    private final boolean isList;

    /** Toggle should be done. */
    private final String toggleTheme;

    /** API endpoint to be consumed by the UI for displaying response. */
    private final Endpoint endpoint;


    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean isList) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.isApiResponse = false;
        this.isList = isList;
        this.toggleTheme = null;
        this.endpoint = null;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields, including the newly added isApiResponse.
     */
    public CommandResult(String feedbackToUser, Endpoint endpoint, boolean showHelp, boolean exit,
                         boolean isApiResponse) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.isApiResponse = isApiResponse;
        this.isList = false;
        this.toggleTheme = null;
        this.endpoint = endpoint;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields, including the newly added theme to toggle.
     */
    public CommandResult(String feedbackToUser, String themeToToggle) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = false;
        this.exit = false;
        this.isApiResponse = false;
        this.isList = false;
        this.toggleTheme = themeToToggle;
        this.endpoint = null;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public Endpoint getEndpoint() {
        return endpoint;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isApiResponse() {
        return isApiResponse;
    }

    public boolean isList() {
        return isList;
    }

    public String getToggleTheme() {
        return this.toggleTheme;
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
                && isApiResponse == otherCommandResult.isApiResponse
                && isList == otherCommandResult.isList
                && endpoint == otherCommandResult.endpoint;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, isList);
    }

}
