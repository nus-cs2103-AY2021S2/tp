package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_CAPACITY;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.booking.model.Model.PREDICATE_SHOW_ALL_VENUES;

import java.util.List;
import java.util.Optional;

import seedu.booking.commons.core.Messages;
import seedu.booking.commons.util.CollectionUtil;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
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
            + "[" + PREFIX_VENUE + "VENUE NAME] "
            + "[" + PREFIX_CAPACITY + "CAPACITY] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "Example: " + COMMAND_WORD + " vo/Victoria Hall "
            + PREFIX_VENUE + "Victorias Hall "
            + PREFIX_DESCRIPTION + "Cool concert place";

    public static final String MESSAGE_EDIT_VENUE_SUCCESS = "Edited Venue: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
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

        if (lastShownList.stream().noneMatch(venue -> venue.getVenueName().equals(venueName))) {
            throw new CommandException(Messages.MESSAGE_INVALID_VENUE_NAME);
        }

        Venue venueToEdit = getVenueByVenueName(venueName, lastShownList);
        Venue editedVenue = createEditedVenue(venueToEdit, editVenueDescriptor);

        if (!venueToEdit.isSameVenue(editedVenue) && model.hasVenue(editedVenue)) {
            throw new CommandException(MESSAGE_DUPLICATE_VENUE);
        }

        model.setVenue(venueToEdit, editedVenue);
        model.updateFilteredVenueList(PREDICATE_SHOW_ALL_VENUES);
        return new CommandResult(String.format(MESSAGE_EDIT_VENUE_SUCCESS, editedVenue));
    }

    private static Venue getVenueByVenueName(VenueName venueName, List<Venue> venueList) {
        return venueList.stream()
                .filter(venue -> venue.getVenueName().equals(venueName)).findFirst().orElse(null);
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

        return new Venue(updatedVenueName, updatedCapacity, updatedDescription);
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

        public EditVenueDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditVenueDescriptor(EditVenueDescriptor toCopy) {
            setVenueName(toCopy.name);
            setCapacity(toCopy.capacity);
            setDescription(toCopy.description);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, capacity);
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
                    && getDescription().equals(e.getDescription());
        }
    }
}
