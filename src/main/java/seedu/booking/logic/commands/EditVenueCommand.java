package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_CAPACITY;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.booking.model.Model.PREDICATE_SHOW_ALL_BOOKINGS;
import static seedu.booking.model.Model.PREDICATE_SHOW_ALL_VENUES;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.booking.commons.core.Messages;
import seedu.booking.commons.util.CollectionUtil;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.Tag;
import seedu.booking.model.venue.Capacity;
import seedu.booking.model.venue.Venue;
import seedu.booking.model.venue.VenueName;

/**
 * Edits the details of an existing venue in the booking system.
 */
public class EditVenueCommand extends Command {

    public static final String COMMAND_WORD = "edit_venue";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the venue identified "
            + "by the venue name used in the displayed venue list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: vo/VENUE "
            + "[" + PREFIX_VENUE + "VENUE_NAME] "
            + "[" + PREFIX_CAPACITY + "CAPACITY] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_TAG + "TAG] \n"
            + "Example: " + COMMAND_WORD + " vo/Victoria Hall "
            + PREFIX_VENUE + "Victorias Hall "
            + PREFIX_CAPACITY + "10"
            + PREFIX_DESCRIPTION + "Cool concert place"
            + PREFIX_TAG + "Central";

    public static final String MESSAGE_EDIT_VENUE_SUCCESS = "Edited Venue: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.\n";
    public static final String MESSAGE_FIELDS = "[" + PREFIX_VENUE + "VENUE_NAME] "
            + "[" + PREFIX_CAPACITY + "CAPACITY] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_TAG + "TAG] \n";
    public static final String MESSAGE_DUPLICATE_VENUE = "This venue already exists in the booking system.";

    private final VenueName venueName;
    private final EditVenueDescriptor editVenueDescriptor;

    /**
     * @param venueName of the venue in the filtered venue list to edit.
     * @param editVenueDescriptor details to edit the venue with.
     */
    public EditVenueCommand(VenueName venueName, EditVenueDescriptor editVenueDescriptor) {
        requireNonNull(venueName);
        requireNonNull(editVenueDescriptor);

        this.venueName = venueName;
        this.editVenueDescriptor = new EditVenueDescriptor(editVenueDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Venue> lastShownList = model.getFilteredVenueList();

        if (!lastShownList.stream().anyMatch(venueName::isSameVenueName)) {
            throw new CommandException(Messages.MESSAGE_INVALID_VENUE_NAME);
        }

        Venue venueToEdit = getVenueByVenueName(venueName, lastShownList);
        Venue editedVenue = createEditedVenue(venueToEdit, editVenueDescriptor);

        if (!venueToEdit.isSameVenue(editedVenue) && model.hasVenue(editedVenue)) {
            throw new CommandException(MESSAGE_DUPLICATE_VENUE);
        }

        model.setVenue(venueToEdit, editedVenue);
        model.updateVenueInBookings(venueToEdit.getVenueName(), editedVenue.getVenueName());
        model.updateFilteredBookingList(PREDICATE_SHOW_ALL_BOOKINGS);
        model.updateFilteredVenueList(PREDICATE_SHOW_ALL_VENUES);
        return new CommandResult(String.format(MESSAGE_EDIT_VENUE_SUCCESS, editedVenue));
    }

    private static Venue getVenueByVenueName(VenueName venueName, List<Venue> venueList) {
        return venueList.stream().filter(venueName::isSameVenueName).findFirst().orElse(null);
    }

    /**
     * Creates and returns a {@code Venue} with the details of {@code venueToEdit}
     * edited with {@code editVenueDescriptor}.
     */
    private static Venue createEditedVenue(Venue venueToEdit, EditVenueDescriptor editVenueDescriptor) {
        assert venueToEdit != null;

        VenueName updatedVenueName = editVenueDescriptor.getVenueName().orElse(venueToEdit.getVenueName());
        Capacity updatedCapacity = editVenueDescriptor.getCapacity().orElse(venueToEdit.getCapacity());
        String updatedDescription = editVenueDescriptor.getDescription().orElse(venueToEdit.getDescription());
        Set<Tag> tags = editVenueDescriptor.getTags().orElse(venueToEdit.getTags());

        return new Venue(updatedVenueName, updatedCapacity, updatedDescription, tags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditVenueCommand)) {
            return false;
        }

        // state check
        EditVenueCommand e = (EditVenueCommand) other;
        return venueName.equals(e.venueName)
                && editVenueDescriptor.equals(e.editVenueDescriptor);
    }

    /**
     * Stores the details to edit the venue with. Each non-empty field value will replace the
     * corresponding field value of the venue.
     */
    public static class EditVenueDescriptor {
        private VenueName name;
        private Capacity capacity;
        private String description;
        private Set<Tag> tags;

        public EditVenueDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditVenueDescriptor(EditVenueDescriptor toCopy) {
            setVenueName(toCopy.name);
            setCapacity(toCopy.capacity);
            setDescription(toCopy.description);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, capacity, description, tags);
        }

        public void setVenueName(VenueName name) {
            this.name = name;
        }

        public Optional<VenueName> getVenueName() {
            return Optional.ofNullable(name);
        }

        public void setCapacity(Capacity capacity) {
            this.capacity = capacity;
        }

        public Optional<Capacity> getCapacity() {
            return Optional.ofNullable(capacity);
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Optional<String> getDescription() {
            return Optional.ofNullable(description);
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


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditVenueDescriptor)) {
                return false;
            }

            // state check
            EditVenueDescriptor e = (EditVenueDescriptor) other;

            return getVenueName().equals(e.getVenueName())
                    && getCapacity().equals(e.getCapacity())
                    && getDescription().equals(e.getDescription())
                    && getTags().equals(e.getTags());
        }
    }
}
