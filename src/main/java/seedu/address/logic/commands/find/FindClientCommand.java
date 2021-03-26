package seedu.address.logic.commands.find;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.appointment.AppointmentContainsKeywordsPredicate;
import seedu.address.model.property.PropertyClientNamePredicate;

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

    /**
     * Creates a {@code FindClientCommand} which combines a {@code PropertyClientNamePredicate}
     * and a {@code AppointmentContainsKeywordsPredicate} to filter both the appointments and
     * the properties at the same time.
     * @param clientPredicate Checks for keywords in properties' client names
     * @param appointmentPredicate Checks for keywords in appointment names
     */
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

        return new CommandResult(Messages.getClientFindSuccessMessage(propertyListSize, appointmentListSize));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindClientCommand // instanceof handles nulls
                && clientPredicate.equals(((FindClientCommand) other).clientPredicate)
                && appointmentPredicate.equals(((FindClientCommand) other).appointmentPredicate)); // state check
    }
}
