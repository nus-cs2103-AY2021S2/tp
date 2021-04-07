package fooddiary.logic.commands;

import static fooddiary.model.Model.PREDICATE_SHOW_ALL_ENTRIES;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
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
import fooddiary.model.entry.Price;
import fooddiary.model.entry.Rating;
import fooddiary.model.entry.Review;
import fooddiary.model.tag.TagCategory;
import fooddiary.model.tag.TagSchool;


/**
 * Edits the details of an existing entry in the food diary.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the entry identified "
            + "by the index number used in the displayed entry list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + CliSyntax.PREFIX_NAME + "NAME] "
            + "[" + CliSyntax.PREFIX_RATING + "RATING] "
            + "[" + CliSyntax.PREFIX_PRICE + "PRICE] "
            + "[" + CliSyntax.PREFIX_REVIEW + "REVIEW] "
            + "[" + CliSyntax.PREFIX_ADDRESS + "ADDRESS] "
            + "[" + CliSyntax.PREFIX_TAG_CATEGORY + "CATEGORIES]..."
            + "[" + CliSyntax.PREFIX_TAG_SCHOOL + "SCHOOLS]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + CliSyntax.PREFIX_RATING + "5 "
            + CliSyntax.PREFIX_REVIEW + "I like this food a lot!";

    public static final String MESSAGE_EDIT_ENTRY_SUCCESS = "Edited Entry: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ENTRY = "This entry already exists in the food diary.";

    private final Index index;
    private final EditEntryDescriptor editEntryDescriptor;

    /**
     * @param index of the entry in the filtered entry list to edit
     * @param editEntryDescriptor details to edit the entry with
     */
    public EditCommand(Index index, EditEntryDescriptor editEntryDescriptor) {
        requireNonNull(index);
        requireNonNull(editEntryDescriptor);

        this.index = index;
        this.editEntryDescriptor = new EditEntryDescriptor(editEntryDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Entry> lastShownList = model.getFilteredEntryList();

        if (index.getZeroBased() >= lastShownList.size() && lastShownList.size() == 1) {
            throw new CommandException(Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX_SINGULAR);
        } else if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX_PLURAL,
                    lastShownList.size()));
        }

        Entry entryToEdit = lastShownList.get(index.getZeroBased());
        Entry editedEntry = createEditedEntry(entryToEdit, editEntryDescriptor);

        if (!editEntryDescriptor.isAnyFieldEdited()) {
            throw new CommandException(MESSAGE_NOT_EDITED);
        }

        if (!entryToEdit.isSameEntry(editedEntry) && model.hasEntry(editedEntry)) {
            throw new CommandException(MESSAGE_DUPLICATE_ENTRY);
        }

        model.setEntry(entryToEdit, editedEntry);
        model.updateFilteredEntryList(PREDICATE_SHOW_ALL_ENTRIES);
        return new CommandResult(String.format(MESSAGE_EDIT_ENTRY_SUCCESS, editedEntry));
    }

    /**
     * Creates and returns a {@code Entry} with the details of {@code entryToEdit}
     * edited with {@code editEntryDescriptor}.
     */
    private static Entry createEditedEntry(Entry entryToEdit, EditEntryDescriptor editEntryDescriptor) {
        assert entryToEdit != null;

        Name updatedName = editEntryDescriptor.getName().orElse(entryToEdit.getName());
        Rating updatedRating = editEntryDescriptor.getRating().orElse(entryToEdit.getRating());
        Price updatedPrice = editEntryDescriptor.getPrice().orElse(entryToEdit.getPrice());
        List<Review> updatedReview = editEntryDescriptor.getReviews().orElse(entryToEdit.getReviews());
        Address updatedAddress = editEntryDescriptor.getAddress().orElse(entryToEdit.getAddress());
        Set<TagCategory> updatedTagCategories = editEntryDescriptor.getTagCategories()
                                                    .orElse(entryToEdit.getTagCategories());
        Set<TagSchool> updatedTagSchools = editEntryDescriptor.getTagSchools().orElse(entryToEdit.getTagSchools());

        return new Entry(updatedName, updatedRating, updatedPrice, updatedReview,
                updatedAddress, updatedTagCategories, updatedTagSchools);
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
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editEntryDescriptor.equals(e.editEntryDescriptor);
    }

    /**
     * Stores the details to edit the entry with. Each non-empty field value will replace the
     * corresponding field value of the entry.
     */
    public static class EditEntryDescriptor {
        private Name name;
        private Rating rating;
        private Price price;
        private List<Review> reviews;
        private Address address;
        private Set<TagCategory> tagCategories;
        private Set<TagSchool> tagSchools;


        public EditEntryDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditEntryDescriptor(EditEntryDescriptor toCopy) {
            setName(toCopy.name);
            setRating(toCopy.rating);
            setPrice(toCopy.price);
            setReviews(toCopy.reviews);
            setAddress(toCopy.address);
            setTagCategories(toCopy.tagCategories);
            setTagSchools(toCopy.tagSchools);

        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, rating, price, reviews, address, tagCategories, tagSchools);
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

        public void setPrice(Price price) {
            this.price = price;
        }

        public Optional<Price> getPrice() {
            return Optional.ofNullable(price);
        }

        /**
         * Sets {@code reviews} to this object's {@code reviews}.
         * A defensive copy of {@code reviews} is used internally.
         */
        public void setReviews(List<Review> reviews) {
            this.reviews = (reviews != null) ? new ArrayList<>(reviews) : null;
        }

        /**
         * Returns an unmodifiable review list, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code reviews} is null.
         */
        public Optional<List<Review>> getReviews() {
            return (reviews != null) ? Optional.of(Collections.unmodifiableList(reviews)) : Optional.empty();
        }


        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code tags} to this object's {@code tagCategories}.
         * A defensive copy of {@code tag} is used internally.
         */
        public void setTagCategories(Set<TagCategory> tags) {
            this.tagCategories = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Sets {@code tags} to this object's {@code tagSchools}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTagSchools(Set<TagSchool> tags) {
            this.tagSchools = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<TagCategory>> getTagCategories() {
            return (tagCategories != null) ? Optional.of(Collections.unmodifiableSet(tagCategories)) : Optional.empty();
        }

        public Optional<Set<TagSchool>> getTagSchools() {
            return (tagSchools != null) ? Optional.of(Collections.unmodifiableSet(tagSchools)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditEntryDescriptor)) {
                return false;
            }

            // state check
            EditEntryDescriptor e = (EditEntryDescriptor) other;

            return getName().equals(e.getName())
                    && getRating().equals(e.getRating())
                    && getPrice().equals(e.getPrice())
                    && getReviews().equals(e.getReviews())
                    && getAddress().equals(e.getAddress())
                    && getTagCategories().equals(e.getTagCategories())
                    && getTagSchools().equals(e.getTagSchools());
        }
    }
}
