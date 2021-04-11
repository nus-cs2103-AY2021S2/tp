package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.garment.Garment;
import seedu.address.model.garment.Type;

/**
 * Views garments identified using displayed indexes
 * from the wardrobe
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the garments (must be of different Types and only one of each Type)\n"
            + "identified by the index numbers used in the displayed garment list. \n"
            + "Parameters: INDEX INDEX INDEX (must be a positive integers)\n"
            + "Example: " + COMMAND_WORD + " 1 2 3";

    public static final String MESSAGE_VIEW_GARMENT_SUCCESS = "Viewing Garments";
    public static final String MESSAGE_DUPLICATE_TYPE = "Duplicate garment types used";
    public static final String MESSAGE_DIFFERENT_DRESSCODE = "Dress code differs between garments being viewed";

    private final List<Index> indexes;

    public ViewCommand(List<Index> indexes) {
        this.indexes = indexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Garment> lastShownList = model.getFilteredGarmentList();

        List<Garment> viewList = new ArrayList<>();
        boolean upperPresent = false;
        boolean lowerPresent = false;
        boolean footwearPresent = false;
        for (Index index : this.indexes) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_GARMENT_DISPLAYED_INDEX);
            }
            Garment garmentToView = lastShownList.get(index.getZeroBased());
            Type garmentType = garmentToView.getType();
            if (garmentType.value.equals("upper") && !upperPresent) {
                upperPresent = true;
            } else if (garmentType.value.equals("lower") && !lowerPresent) {
                lowerPresent = true;
            } else if (garmentType.value.equals("footwear") && !footwearPresent) {
                footwearPresent = true;
            } else {
                throw new CommandException(MESSAGE_DUPLICATE_TYPE);
            }
            viewList.add(garmentToView);
        }
        for (Garment g : viewList) {
            if (!g.getDressCode().equals(viewList.get(0).getDressCode())) {
                throw new CommandException(MESSAGE_DIFFERENT_DRESSCODE);
            }
        }
        Predicate<Garment> predicateViewGarments = garment -> viewList.contains(garment);
        model.updateFilteredGarmentList(predicateViewGarments);
        return new CommandResult(MESSAGE_VIEW_GARMENT_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && this.indexes.containsAll(((ViewCommand) other).indexes));
    }
}
