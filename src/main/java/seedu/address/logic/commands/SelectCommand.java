package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.garment.Garment;
import seedu.address.model.garment.LastUse;

/**
 * Updates the selected garment's lastuse attribute
 */
public class SelectCommand extends Command {
    public static final String COMMAND_WORD = "select";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Selects the garment identified by the index number used in the displayed garment list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_SELECT_GARMENT_SUCCESS = "Selected Garment: %1$s";

    private final Index targetIndex;

    public SelectCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Garment> lastShownList = model.getFilteredGarmentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GARMENT_DISPLAYED_INDEX);
        }
        Garment garmentToSelect = lastShownList.get(targetIndex.getZeroBased());
        Garment garmentChanged = new Garment(
                garmentToSelect.getName(),
                garmentToSelect.getSize(),
                garmentToSelect.getColour(),
                garmentToSelect.getDressCode(),
                garmentToSelect.getType(),
                garmentToSelect.getDescriptions(),
                new LastUse(LocalDate.now()));
        //delete the original garment
        DeleteCommand deleteCommand = new DeleteCommand(targetIndex);
        AddCommand addCommand = new AddCommand(garmentChanged);
        deleteCommand.execute(model);
        addCommand.execute(model);
        return new CommandResult(String.format(MESSAGE_SELECT_GARMENT_SUCCESS, garmentChanged));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectCommand // instanceof handles nulls
                && targetIndex.equals(((SelectCommand) other).targetIndex)); // state check
    }

}
