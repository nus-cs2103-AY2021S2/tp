package seedu.address.logic.commands.doctor;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearDoctorCommand extends Command {

    public static final String COMMAND_WORD = "clear-doctor";
    public static final String MESSAGE_SUCCESS = "Doctor records has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setPatientRecords(new AddressBook<>());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}