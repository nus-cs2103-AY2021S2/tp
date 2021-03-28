package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.model.Model;

/**
 * Sets the position of contact divider.
 */
public class SetNoteDividerPositionCommand extends SetDividerPositionCommand {

    public static final String COMMAND_WORD = "setdividern";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Set the position of note divider.\n"
            + "Parameters: Position (must be a integer between 1 to 9 inclusively)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SET_DIVIDER_SUCCESS = "Note Divider Position Set to ";

    /**
     * Creates an SetNoteDividerPositionCommand that set the not divider to a the specified position
     */
    public SetNoteDividerPositionCommand(int position) {
        super(position);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.getGuiSettings().setNoteSplitRatio(position / NORMALIZE_RATIO);
        return new CommandResult(String.format(MESSAGE_SET_DIVIDER_SUCCESS + position),
                UiAction.OPEN, UiActionOption.NOTE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetNoteDividerPositionCommand // instanceof handles nulls
                && position == (((SetNoteDividerPositionCommand) other).position)); // state check
    }
}
