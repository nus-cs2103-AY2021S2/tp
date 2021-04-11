package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** The action UI should take.*/
    private final UiAction uiAction;

    /** The option of the action UI.*/
    private final UiActionOption uiActionOption;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, UiAction uiAction, UiActionOption uiActionOption) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.uiAction = uiAction;
        this.uiActionOption = uiActionOption;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, UiAction.NONE, UiActionOption.NONE);
    }


    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}. {@code UiAction},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, UiAction uiAction) {
        this(feedbackToUser, uiAction, UiActionOption.NONE);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public UiAction getUiAction() {
        return uiAction;
    }

    public UiActionOption getUiActionOption() {
        return uiActionOption;
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
        return feedbackToUser.equals(otherCommandResult.feedbackToUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, uiAction, uiActionOption);
    }
}
