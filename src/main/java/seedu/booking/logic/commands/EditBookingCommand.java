package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.logic.commands.CommandShowType.COMMAND_SHOW_BOOKINGS;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_BOOKING_END;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_BOOKING_START;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.booking.model.Model.PREDICATE_SHOW_ALL_BOOKINGS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.booking.commons.core.Messages;
import seedu.booking.commons.core.index.Index;
import seedu.booking.commons.util.CollectionUtil;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.Tag;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.Description;
import seedu.booking.model.booking.EndTime;
import seedu.booking.model.booking.StartTime;
import seedu.booking.model.person.Email;
import seedu.booking.model.venue.VenueName;

public class EditBookingCommand extends Command {
    public static final String COMMAND_WORD = "edit_booking";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the booking identified "
            + "by the index number used in the displayed booking list.\n"
            + "At least one parameter to be changed must be specified. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_EMAIL + "BOOKER EMAIL] "
            + "[" + PREFIX_VENUE + "VENUE NAME] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_BOOKING_START + "DATETIME] "
            + "[" + PREFIX_BOOKING_END + "DATETIME] "
            + "[" + PREFIX_TAG + "TAG]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_EMAIL + "example@gmail.com "
            + PREFIX_VENUE + "Hall "
            + PREFIX_DESCRIPTION + "For FYP meeting. "
            + PREFIX_BOOKING_START + "2012-01-31 22:59 "
            + PREFIX_BOOKING_END + "2012-01-31 23:59 "
            + PREFIX_TAG + "meeting";

    public static final String MESSAGE_EDIT_BOOKING_SUCCESS = "Edited booking: %1$s";
    public static final String MESSAGE_DUPLICATE_BOOKING = "This booking already exists in the booking system.";
    public static final String MESSAGE_OVERLAPPING_BOOKING = "This slot has been booked already";
    public static final String MESSAGE_SUCCESS = "New booking added: %1$s";
    public static final String MESSAGE_INVALID_TIME =
            "This booking's starting time is not earlier than the ending time.";
    public static final String MESSAGE_INVALID_VENUE = "This venue does not exist in the system.";
    public static final String MESSAGE_INVALID_PERSON = "This booker does not exist in the system.";
    private final Index index;
    private final EditBookingDescriptor editBookingDescriptor;

    /**
     * @param index of the booking in the filtered booking list to edit.
     * @param editBookingDescriptor details to edit the booking with.
     */
    public EditBookingCommand(Index index, EditBookingDescriptor editBookingDescriptor) {
        requireNonNull(index);
        requireNonNull(editBookingDescriptor);

        this.index = index;
        this.editBookingDescriptor = new EditBookingDescriptor(editBookingDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Booking> lastShownList = model.getFilteredBookingList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
        }

        Booking bookingToEdit = lastShownList.get(index.getZeroBased());
        Booking editedBooking = createEditedBooking(bookingToEdit, editBookingDescriptor);

        if (!bookingToEdit.isSameBooking(editedBooking) && model.hasBooking(editedBooking)) {
            throw new CommandException(MESSAGE_DUPLICATE_BOOKING);
        }

        if (!model.hasPersonWithEmail(editedBooking.getBookerEmail())) {
            throw new CommandException(MESSAGE_INVALID_PERSON);
        }

        if (!model.hasVenueWithVenueName(editedBooking.getVenueName())) {
            throw new CommandException(MESSAGE_INVALID_VENUE);
        }

        if (model.hasMoreThanOneOverlappedBooking(editedBooking)) {
            throw new CommandException(MESSAGE_OVERLAPPING_BOOKING);
        }

        if (!editedBooking.isValidTime()) {
            throw new CommandException(MESSAGE_INVALID_TIME);
        }

        model.setBooking(bookingToEdit, editedBooking);
        model.updateFilteredBookingList(PREDICATE_SHOW_ALL_BOOKINGS);
        return new CommandResult(String.format(MESSAGE_EDIT_BOOKING_SUCCESS, editedBooking), COMMAND_SHOW_BOOKINGS);
    }


    /**
     * Creates and returns a {@code Booking} with the details of {@code bookingToEdit}
     * edited with {@code editBookingDescriptor}.
     */
    private static Booking createEditedBooking(Booking bookingToEdit, EditBookingDescriptor editBookingDescriptor) {
        assert bookingToEdit != null;

        Email updatedBooker = editBookingDescriptor.getBookerEmail().orElse(bookingToEdit.getBookerEmail());
        VenueName updatedVenue = editBookingDescriptor.getVenueName().orElse(bookingToEdit.getVenueName());
        Description updatedDescription = editBookingDescriptor.getDescription().orElse(bookingToEdit.getDescription());
        StartTime updatedBookingStart = editBookingDescriptor.getBookingStart().orElse(bookingToEdit.getBookingStart());
        EndTime updatedBookingEnd = editBookingDescriptor.getBookingEnd().orElse(bookingToEdit.getBookingEnd());
        Set<Tag> updatedTags = editBookingDescriptor.getTags().orElse(bookingToEdit.getTags());
        return new Booking(updatedBooker, updatedVenue, updatedDescription,
                updatedBookingStart, updatedBookingEnd, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditBookingCommand)) {
            return false;
        }

        // state check
        EditBookingCommand e = (EditBookingCommand) other;
        return index.equals(e.index)
                && editBookingDescriptor.equals(e.editBookingDescriptor);
    }

    /**
     * Stores the details to edit the booking with. Each non-empty field value will replace the
     * corresponding field value of the booking.
     */
    public static class EditBookingDescriptor {
        private Email bookerEmail;
        private VenueName venueName;
        private Description description;
        private StartTime bookingStart;
        private EndTime bookingEnd;
        private Set<Tag> tags;

        public EditBookingDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditBookingDescriptor(EditBookingDescriptor toCopy) {
            setBookerEmail(toCopy.bookerEmail);
            setVenueName(toCopy.venueName);
            setDescription(toCopy.description);
            setBookingStart(toCopy.bookingStart);
            setBookingEnd(toCopy.bookingEnd);
            setTags(toCopy.tags);
        }


        public void setBookingEnd(EndTime bookingEnd) {
            this.bookingEnd = bookingEnd;
        }

        public Optional<EndTime> getBookingEnd() {
            return Optional.ofNullable(bookingEnd);
        }

        public void setBookingStart(StartTime bookingStart) {
            this.bookingStart = bookingStart;
        }

        public Optional<StartTime> getBookingStart() {
            return Optional.ofNullable(bookingStart);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setVenueName(VenueName venueName) {
            this.venueName = venueName;
        }

        public Optional<VenueName> getVenueName() {
            return Optional.ofNullable(venueName);
        }

        public void setBookerEmail(Email booker) {
            this.bookerEmail = booker;
        }

        public Optional<Email> getBookerEmail() {
            return Optional.ofNullable(bookerEmail);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(bookerEmail, venueName, description, bookingStart, bookingEnd, tags);
        }


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditBookingDescriptor)) {
                return false;
            }

            // state check
            EditBookingDescriptor e = (EditBookingDescriptor) other;

            return getBookerEmail().equals(e.getBookerEmail())
                    && getVenueName().equals(e.getVenueName())
                    && getDescription().equals(e.getDescription())
                    && getBookingStart().equals(e.getBookingStart())
                    && getBookingEnd().equals(e.getBookingEnd());
        }
    }
}
