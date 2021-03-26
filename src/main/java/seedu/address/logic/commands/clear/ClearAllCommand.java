package seedu.address.logic.commands.clear;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AppointmentBook;
import seedu.address.model.Model;
import seedu.address.model.PropertyBook;

/**
 * Clears all of the data in both the property and appointment book.
 */
public class ClearAllCommand extends Command {

    public static final String COMMAND_WORD = "clear all";
    public static final String MESSAGE_SUCCESS = "Both Property and Appointment book have been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setPropertyBook(new PropertyBook());
        model.setAppointmentBook(new AppointmentBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

