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
    private final UiAction uiAction;
    private final UiActionOption uiActionOption;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     *
     * @param feedbackToUser feedback to user.
     * @param uiAction UI action for UI.
     * @param uiActionOption UI action option for UI.
     */
    public CommandResult(String feedbackToUser, UiAction uiAction, UiActionOption uiActionOption) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.uiAction = uiAction;
        this.uiActionOption = uiActionOption;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * and other fields set to their default value.
     *
     * @param feedbackToUser feedback to user.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, UiAction.NONE, UiActionOption.NONE);
    }


    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * and other fields set to their default value.
     *
     * @param feedbackToUser feedback to user.
     * @param uiAction UI action for UI.
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
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
            && uiAction.equals(otherCommandResult.uiAction)
            && uiActionOption.equals(otherCommandResult.uiActionOption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, uiAction, uiActionOption);
    }
}
