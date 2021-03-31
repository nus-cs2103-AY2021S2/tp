package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.logic.commands.CommandUtil.getBookingById;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.booking.model.Model.PREDICATE_SHOW_ALL_BOOKINGS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.booking.commons.core.Messages;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.Tag;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.Id;

/**
 * Adds a booking to the booking system.
 */
public class AddBookingTagCommand extends Command {

    public static final String COMMAND_WORD = "add_booking_tags";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add booking tags to the booking system. "
            + "Parameters: "
            + PREFIX_TAG + "TAG...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "StudentAffairs";

    public static final String MESSAGE_SUCCESS = "New tags added: ";
    public static final String MESSAGE_DUPLICATE_TAG = "Some tag already exists in the booking, ignoring.\n";
    private final Id id;
    private final Set<Tag> toAddTags;

    /**
     * Creates an CreateBookingCommand to add the specified {@code Booking}
     */
    public AddBookingTagCommand(Id bookingId, Set<Tag> toAddTags) {
        requireNonNull(bookingId);
        id = bookingId;
        this.toAddTags = toAddTags;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Booking> lastShownList = model.getFilteredBookingList();

        if (!lastShownList.stream().anyMatch(booking -> booking.isId(id))) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOKING_ID);
        }

        Booking bookingToEdit = getBookingById(id, lastShownList);

        final StringBuilder message = new StringBuilder();
        if (bookingToEdit.getTags().stream().anyMatch(toAddTags::contains)) {
            message.append(MESSAGE_DUPLICATE_TAG);
        }

        Set<Tag> newTags = new HashSet<>(bookingToEdit.getTags());
        newTags.addAll(toAddTags);

        Booking editedBooking = new Booking(
                bookingToEdit.getBookerEmail(),
                bookingToEdit.getVenueName(),
                bookingToEdit.getDescription(),
                bookingToEdit.getBookingStart(),
                bookingToEdit.getBookingEnd(),
                newTags,
                bookingToEdit.getId());

        model.setBooking(bookingToEdit, editedBooking);
        model.updateFilteredBookingList(PREDICATE_SHOW_ALL_BOOKINGS);

        message.append(MESSAGE_SUCCESS);
        toAddTags.stream().filter(x -> !bookingToEdit.getTags().contains(x)).forEach(message::append);
        return new CommandResult(message.toString());
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddBookingTagCommand // instanceof handles nulls
                && id.equals(((AddBookingTagCommand) other).id)
                && toAddTags.equals(((AddBookingTagCommand) other).toAddTags));
    }
}
