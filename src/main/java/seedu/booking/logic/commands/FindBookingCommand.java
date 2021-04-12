package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.booking.model.Model.PREDICATE_SHOW_ALL_BOOKINGS;

import java.util.List;
import java.util.function.Predicate;

import seedu.booking.commons.core.Messages;
import seedu.booking.model.Model;
import seedu.booking.model.booking.Booking;

/**
 * Finds and lists the bookings in the booking system who match the specified fields.
 */
public class FindBookingCommand extends Command {

    public static final String COMMAND_WORD = "find_booking";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all bookings who match the given fields.\n"
            + "At least one field must be provided.\n"
            + "Parameters: " + COMMAND_WORD + " "
            + "[" + PREFIX_EMAIL + "BOOKER_EMAIL] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_VENUE + "VENUE_NAME] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_TAG + "TAG]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EMAIL + "jane@gmail.com "
            + PREFIX_DATE + "2020-02-12 "
            + PREFIX_TAG + "Student ";

    private final List<Predicate<Booking>> predicateList;

    public FindBookingCommand(List<Predicate<Booking>> predicateList) {
        this.predicateList = predicateList;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Booking> predicate = combineBookingPredicates(predicateList);
        model.updateFilteredBookingList(predicate);
        if (model.getFilteredBookingList().size() == 0) {
            return new CommandResult(Messages.MESSAGE_NO_BOOKINGS_FOUND);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_BOOKING_DISPLAYED, model.getFilteredBookingList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindBookingCommand // instanceof handles nulls
                && predicateList.equals(((FindBookingCommand) other).predicateList)); // state check
    }

    /**
     * Returns a composition of the predicates in the given booking predicate list.
     */
    private static Predicate<Booking> combineBookingPredicates(List<Predicate<Booking>> predicateList) {
        return predicateList.stream().reduce(Predicate::and).orElse(PREDICATE_SHOW_ALL_BOOKINGS);
    }
}

