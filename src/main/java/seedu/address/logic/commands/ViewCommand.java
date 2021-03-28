package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.garment.Garment;
import seedu.address.model.garment.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the garments (must be of different Types and only one of each Type) identified by the index numbers used in the displayed garment list. \n"
            + "Parameters: INDEX (must be a positive integer) INDEX (must be a positive integer) INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 2 3";

    public static final String MESSAGE_VIEW_GARMENT_SUCCESS = "Viewing Garments: %1$s";
    public static final String MESSAGE_DUPLICATE_TYPE = "Duplicate garment types used";

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
            Garment garmentToView = lastShownList.get(index.getZeroBased());
            Type garmentType= garmentToView.getType();
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

        Predicate<Garment> PREDICATE_VIEW_GARMENTS = garment -> viewList.contains(garment);
        model.updateFilteredGarmentList(PREDICATE_VIEW_GARMENTS);
        return new CommandResult(MESSAGE_VIEW_GARMENT_SUCCESS);
    }

}
