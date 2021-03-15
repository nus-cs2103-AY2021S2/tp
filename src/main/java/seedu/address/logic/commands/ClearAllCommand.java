package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AppointmentBook;
import seedu.address.model.Model;
import seedu.address.model.PropertyBook;

/**
 * Clears the sample data in the appointment and property book.
 */
public class ClearAllCommand extends Command {

    public static final String COMMAND_WORD = "clear all";
    public static final String MESSAGE_SUCCESS = "Appointment and Property book have been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setPropertyBook(new PropertyBook());
        model.setAppointmentBook(new AppointmentBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

