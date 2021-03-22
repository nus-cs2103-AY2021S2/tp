package fooddiary.logic.commands;

import static fooddiary.model.Model.PREDICATE_SHOW_ALL_ENTRIES;
import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import fooddiary.commons.core.Messages;
import fooddiary.commons.core.index.Index;
import fooddiary.commons.util.CollectionUtil;
import fooddiary.logic.commands.exceptions.CommandException;
import fooddiary.logic.parser.CliSyntax;
import fooddiary.model.Model;
import fooddiary.model.entry.Address;
import fooddiary.model.entry.Entry;
import fooddiary.model.entry.Name;
import fooddiary.model.entry.Rating;
import fooddiary.model.entry.Review;
import fooddiary.model.tag.Tag;


/**
 * Add-on details to an existing entry in the FoodDiary.
 */
public class AddOnCommand extends Command {

    public static final String COMMAND_WORD = "addon";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds-on details of the entry identified "
            + "by the index number used in the displayed entry list. "
            + "Existing values will be added on to the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + CliSyntax.PREFIX_REVIEW + "REVIEW] "
            + "Example: " + COMMAND_WORD + " 1 "
            + CliSyntax.PREFIX_REVIEW + "I like this food a lot!";

    public static final String MESSAGE_ADDON_TO_ENTRY_SUCCESS = "Added on to entry: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ENTRY = "This person already exists in the address book.";

    private final Index index;
    private final AddOnToEntryDescriptor addOnToEntryDescriptor;

    /**
     * @param index of the entry in the filtered entry list to edit
     * @param addOnToEntryDescriptor details to add on to entry with
     */
    public AddOnCommand(Index index, AddOnToEntryDescriptor addOnToEntryDescriptor) {
        requireNonNull(index);
        requireNonNull(addOnToEntryDescriptor);

        this.index = index;
        this.addOnToEntryDescriptor = new AddOnToEntryDescriptor(addOnToEntryDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Entry> lastShownList = model.getFilteredEntryList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
        }

        Entry entryToAddOn = lastShownList.get(index.getZeroBased());
        Entry updatedEntry = createUpdatedEntry(entryToAddOn, addOnToEntryDescriptor);

        if (!entryToAddOn.isSameEntry(updatedEntry) && model.hasEntry(updatedEntry)) {
            throw new CommandException(MESSAGE_DUPLICATE_ENTRY);
        }

        model.setEntry(entryToAddOn, updatedEntry);
        model.updateFilteredEntryList(PREDICATE_SHOW_ALL_ENTRIES);
        return new CommandResult(String.format(MESSAGE_ADDON_TO_ENTRY_SUCCESS, updatedEntry));
    }

    /**
     * Creates and returns a {@code Entry} with the details of {@code entryToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Entry createUpdatedEntry(Entry entryToAddOn, AddOnToEntryDescriptor addOnToEntryDescriptor) {
        assert entryToAddOn != null;

        Name updatedName = addOnToEntryDescriptor.getName().orElse(entryToAddOn.getName());
        Rating updatedRating = addOnToEntryDescriptor.getRating().orElse(entryToAddOn.getRating());

        Review updatedReview = entryToAddOn.getReview();
        addOnToEntryDescriptor.getReview().ifPresent(review -> {
            updatedReview.addReview(review.value);
        });

        Address updatedAddress = addOnToEntryDescriptor.getAddress().orElse(entryToAddOn.getAddress());
        Set<Tag> updatedTags = addOnToEntryDescriptor.getTags().orElse(entryToAddOn.getTags());

        return new Entry(updatedName, updatedRating, updatedReview, updatedAddress, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        AddOnCommand e = (AddOnCommand) other;
        return index.equals(e.index)
                && addOnToEntryDescriptor.equals(e.addOnToEntryDescriptor);
    }

    /**
     * Stores the details to edit the entry with. Each non-empty field value will replace the
     * corresponding field value of the entry.
     */
    public static class AddOnToEntryDescriptor {
        private Name name;
        private Rating rating;
        private Review review;
        private Address address;
        private Set<Tag> tags;

        public AddOnToEntryDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public AddOnToEntryDescriptor(AddOnToEntryDescriptor toCopy) {
            setName(toCopy.name);
            setRating(toCopy.rating);
            setReview(toCopy.review);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is updated with more details (eg. additional reviews).
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, rating, review, address, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setRating(Rating rating) {
            this.rating = rating;
        }

        public Optional<Rating> getRating() {
            return Optional.ofNullable(rating);
        }

        public void setReview(Review review) {
            this.review = review;
        }

        public Optional<Review> getReview() {
            return Optional.ofNullable(review);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
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
            if (!(other instanceof AddOnToEntryDescriptor)) {
                return false;
            }

            // state check
            AddOnToEntryDescriptor e = (AddOnToEntryDescriptor) other;

            return getName().equals(e.getName())
                    && getRating().equals(e.getRating())
                    && getReview().equals(e.getReview())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }
    }
}
