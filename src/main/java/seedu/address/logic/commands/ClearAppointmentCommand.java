package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentBook;
import seedu.address.model.Model;

/**
 * Clears the sample data in the appointment book.
 */
public class ClearAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "clear appointment";
    public static final String MESSAGE_SUCCESS = "Appointment book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAppointmentBook(new AppointmentBook());
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
