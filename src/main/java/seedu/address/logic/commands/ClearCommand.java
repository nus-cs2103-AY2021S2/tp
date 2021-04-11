package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears DocBob of all patients.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    public static final String MESSAGE_DISPLAYED_IN_VIEW_PATIENT_BOX = "You don't have any patients left, Doc.\n"
                                                        + "Start adding patients with the 'add' command!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS, false, false, null,
                null, null, MESSAGE_DISPLAYED_IN_VIEW_PATIENT_BOX, false);
    }
}
