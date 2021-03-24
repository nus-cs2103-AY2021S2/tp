package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.appointment.AppointmentContainsKeywordsPredicate;
import seedu.address.model.property.PropertyClientNamePredicate;

import static java.util.Objects.requireNonNull;

/**
 * For some keywords, find and list all appointments with names containing those
 * keywords, and all properties whose clients' names contain them as well.
 * Keyword matching is case insensitive.
 */
public class FindClientCommand extends Command {

    public static final String COMMAND_WORD = "find client";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all properties containing any of "
            + "the specified keywords (case-insensitive) and/or by giving a price and search for properties "
            + "with asking prices above or below that amount, then displaying them as a list with index "
            + "numbers.\n"
            + "Parameters: [KEYWORD]... [pl/UPPER_PRICE_LIMIT] [pm/LOWER_PRICE_LIMIT] \n"
            + "Price limits are inclusive. \n"
            + "Example: " + COMMAND_WORD + " jurong\n"
            + COMMAND_WORD + " pl/1000000";
    //TODO change this

    private final PropertyClientNamePredicate clientPredicate;
    private final AppointmentContainsKeywordsPredicate appointmentPredicate;

    public FindClientCommand(PropertyClientNamePredicate clientPredicate, AppointmentContainsKeywordsPredicate appointmentPredicate) {
        this.appointmentPredicate = appointmentPredicate;
        this.clientPredicate = clientPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPropertyList(clientPredicate);
        model.updateFilteredAppointmentList(appointmentPredicate);

        int propertyListSize = model.getFilteredPropertyList().size();
        return new CommandResult(
                String.format(propertyListSize > 1
                        ? Messages.MESSAGE_PROPERTIES_LISTED_OVERVIEW
                        : Messages.MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, propertyListSize));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindClientCommand // instanceof handles nulls
                && clientPredicate.equals(((FindClientCommand) other).clientPredicate)
                && appointmentPredicate.equals(((FindClientCommand) other).appointmentPredicate)); // state check
    }
}
