package fooddiary.logic.commands;

import static fooddiary.model.Model.PREDICATE_SHOW_ALL_ENTRIES;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import fooddiary.commons.core.LogsCenter;
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
    public static final String MESSAGE_NOT_ADDED_ON = "At least one field to add-on must be provided.";
    public static final String MESSAGE_DUPLICATE_ENTRY = "This person already exists in the address book.";

    private static final Logger logger = LogsCenter.getLogger(AddOnCommand.class);
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

        Name updatedName = entryToAddOn.getName(); //cannot add on name
        Rating updatedRating = entryToAddOn.getRating();
        Price updatedPrice = entryToAddOn.getPrice();
        List<Review> updatedReviews = new ArrayList<>();
        updatedReviews.addAll(entryToAddOn.getReviews());
        addOnToEntryDescriptor.getReviews().ifPresent(r -> updatedReviews.addAll(r));
//        List<Review> updatedReviews = addOnToEntryDescriptor.getReviews().orElse(entryToAddOn.getReviews());
        logger.fine("Added additional Review");


        Address updatedAddress = entryToAddOn.getAddress();
        Set<Tag> updatedTags = entryToAddOn.getTags();

        return new Entry(updatedName, updatedRating, updatedPrice, updatedReviews, updatedAddress, updatedTags);
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
        private List<Review> reviews;

        public AddOnToEntryDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public AddOnToEntryDescriptor(AddOnToEntryDescriptor toCopy) {
            setReviews(toCopy.reviews);
        }

        /**
         * Returns true if at least one field is updated with more details (eg. additional reviews).
         */
        public boolean isAnyFieldAddedOn() {
            return CollectionUtil.isAnyNonNull(reviews);
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

            return getReviews().equals(e.getReviews());
        }
    }
}
