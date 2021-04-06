package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Clears all of the data in both the property and appointment book.
 */
public class ClearAllCommand extends Command {

    public static final String COMMAND_WORD = "clear all";
    public static final String MESSAGE_SUCCESS = "Both Property and Appointment book have been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearAppointmentBook();
        model.clearPropertyBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

