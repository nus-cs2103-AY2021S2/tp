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

    /** List should be shown to the user */
    private final boolean hasEndpointToShow;

    /** Toggle should be done. */
    private final String toggleTheme;

    /** API endpoint to be consumed by the UI for displaying response. */
    private final Endpoint endpoint;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * This is the primary private constructor of CommandResult to be used in other factor constructors.
     */
    private CommandResult(String feedbackToUser, String toggleTheme, Endpoint endpoint,
                          boolean showHelp, boolean exit, boolean isList,
                          boolean isApiResponse, boolean hasEndpointToShow) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.isApiResponse = isApiResponse;
        this.isList = isList;
        this.hasEndpointToShow = hasEndpointToShow;
        this.toggleTheme = toggleTheme;
        this.endpoint = endpoint;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value. This is the convenient constructor for commands with a string
     * feedback.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, null,
                null, false, false, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields. This is the constructor for request related
     * commands such send & run.
     */
    public CommandResult(String feedbackToUser, Endpoint endpoint) {
        this(feedbackToUser, null, endpoint,
                false, false, false, true, true);
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields. This is the constructor for request related
     * commands such show, add and edit and remove.
     */
    public CommandResult(String feedbackToUser, Endpoint endpoint, Boolean hasEndpointToShow) {
        this(feedbackToUser, null, endpoint,
                false, false, false, false, hasEndpointToShow);
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields, including the newly added theme to toggle.
     */
    public CommandResult(String feedbackToUser, String themeToToggle) {
        this(feedbackToUser, themeToToggle, null, false, false, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} for exit command.
     */
    public static CommandResult exitCommandResult(String feedbackToUser) {
        return new CommandResult(feedbackToUser, null, null,
                false, true, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} for help command.
     */
    public static CommandResult helpCommandResult(String feedbackToUser) {
        return new CommandResult(feedbackToUser, null, null,
                true, false, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} for list command.
     */
    public static CommandResult listCommandResult(String feedbackToUser) {
        return new CommandResult(feedbackToUser, null, null,
                false, false, true, false, false);
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

    public boolean hasEndpointToShow() {
        return this.hasEndpointToShow;
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
                && hasEndpointToShow == otherCommandResult.hasEndpointToShow
                && endpoint == otherCommandResult.endpoint;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, isList);
    }


}
