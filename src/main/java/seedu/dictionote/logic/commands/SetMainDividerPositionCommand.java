package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;

/**
 * Sets the position of main divider.
 */
public class SetMainDividerPositionCommand extends SetDividerPositionCommand {

    public static final String COMMAND_WORD = "setdividerm";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Set the position of main divider.\n"
            + "Parameters: Position (must be a integer between 1 to 9 inclusively)\n"
            + "Example: " + COMMAND_WORD + " 5";

    public static final String MESSAGE_SET_DIVIDER_SUCCESS = "Main Divider Position Set to ";

    /**
     * Creates an SetMainDividerPositionCommand that set the main divider to a the specified position.
     *
     * @param position position of divider.
     */
    public SetMainDividerPositionCommand(int position) {
        super(position);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (position > 9 || position < 1) {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        model.getGuiSettings().setMainSplitRatio(position / NORMALIZE_RATIO);
        return new CommandResult(String.format(MESSAGE_SET_DIVIDER_SUCCESS + position),
                UiAction.OPEN, UiActionOption.LIST);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetMainDividerPositionCommand // instanceof handles nulls
                && position == (((SetMainDividerPositionCommand) other).position)); // state check
    }
}
