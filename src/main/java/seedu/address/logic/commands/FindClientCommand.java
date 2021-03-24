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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all properties and appointments involving "
        + "the person whose name contains the keywords. \ni.e. properties' clients whose names contain the keywords "
        + "and the appointments with names containing them. \n"
        + "Example: " + COMMAND_WORD + " alice";

    private final PropertyClientNamePredicate clientPredicate;
    private final AppointmentContainsKeywordsPredicate appointmentPredicate;

    public FindClientCommand(PropertyClientNamePredicate clientPredicate,
         AppointmentContainsKeywordsPredicate appointmentPredicate) {
        this.appointmentPredicate = appointmentPredicate;
        this.clientPredicate = clientPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPropertyList(clientPredicate);
        model.updateFilteredAppointmentList(appointmentPredicate);

        int propertyListSize = model.getFilteredPropertyList().size();
        int appointmentListSize = model.getFilteredAppointmentList().size();

        String propertyMsg = propertyListSize > 1
            ? Messages.MESSAGE_PROPERTIES_LISTED_OVERVIEW
            : Messages.MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR;

        String appointmentMsg = appointmentListSize > 1
            ? Messages.MESSAGE_APPOINTMENT_LISTED_OVERVIEW
            : Messages.MESSAGE_APPOINTMENT_LISTED_OVERVIEW_SINGULAR;

        propertyMsg = String.format(propertyMsg, propertyListSize);
        appointmentMsg = String.format(appointmentMsg, appointmentListSize);

        return new CommandResult(appointmentMsg + "\n" + propertyMsg);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindClientCommand // instanceof handles nulls
                && clientPredicate.equals(((FindClientCommand) other).clientPredicate)
                && appointmentPredicate.equals(((FindClientCommand) other).appointmentPredicate)); // state check
    }
}
