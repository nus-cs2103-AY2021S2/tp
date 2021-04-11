package fooddiary.logic.commands;

import static fooddiary.model.Model.PREDICATE_SHOW_ALL_ENTRIES;
import static fooddiary.model.entry.Price.PRICE_RANGE_DASH;
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
import fooddiary.model.tag.TagCategory;
import fooddiary.model.tag.TagSchool;


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
            + "[" + CliSyntax.PREFIX_PRICE + "PRICE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + CliSyntax.PREFIX_REVIEW + "I like this food a lot! "
            + CliSyntax.PREFIX_PRICE + "5";

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

        if (index.getZeroBased() >= lastShownList.size() && lastShownList.size() == 1) {
            throw new CommandException(Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX_SINGULAR);
        } else if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX_PLURAL,
                    lastShownList.size()));
        }

        Entry entryToAddOn = lastShownList.get(index.getZeroBased());
        Entry updatedEntry = createUpdatedEntry(entryToAddOn, addOnToEntryDescriptor);

        if (!addOnToEntryDescriptor.isAnyFieldAddedOn()) {
            throw new CommandException(MESSAGE_NOT_ADDED_ON);
        }

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
        if (addOnToEntryDescriptor.getPrice().isPresent()) {
            updatedPrice = updatePrice(updatedPrice, addOnToEntryDescriptor.getPrice().get());
        }

        List<Review> updatedReviews = new ArrayList<>();
        updatedReviews.addAll(entryToAddOn.getReviews());
        addOnToEntryDescriptor.getReviews().ifPresent(r -> updatedReviews.addAll(r));
        logger.fine("Added additional Review");

        Address updatedAddress = entryToAddOn.getAddress();
        Set<TagCategory> updatedTagCategories = entryToAddOn.getTagCategories();
        Set<TagSchool> updatedTagSchools = entryToAddOn.getTagSchools();

        return new Entry(updatedName, updatedRating, updatedPrice, updatedReviews,
                updatedAddress, updatedTagCategories, updatedTagSchools);
    }

    private static Price updatePrice(Price currentPrice, Price priceToAddOn) {
        String[] priceValues = currentPrice.value.split(PRICE_RANGE_DASH);
        assert priceValues.length == 2 || priceValues.length == 1
                : "Expected Price values to contain minimum 1 value or maximum two values";
        String updatedPriceValue;

        if (priceValues.length == 1) {
            updatedPriceValue = createPriceRange(priceValues, priceToAddOn.value);
        } else {
            updatedPriceValue = updatePriceRange(priceValues, priceToAddOn.value);
        }

        return new Price(updatedPriceValue);
    }

    //TODO add function to format price range
    private static String createPriceRange(String[] priceValues, String priceValueToAddOn) {
        String priceRange;
        assert priceValues.length == 1;
        if (Integer.parseInt(priceValues[0]) == Integer.parseInt(priceValueToAddOn)) {
            priceRange = priceValueToAddOn;
        } else if (Integer.parseInt(priceValues[0]) < Integer.parseInt(priceValueToAddOn)) {
            priceRange = priceValues[0] + PRICE_RANGE_DASH + priceValueToAddOn;
        } else {
            priceRange = priceValueToAddOn + PRICE_RANGE_DASH + priceValues[0];
        }
        return priceRange;
    }

    private static String updatePriceRange(String[] priceValues, String priceValueToAddOn) {
        String priceRange;
        assert priceValues.length == 2;
        String firstPriceValue = priceValues[0];
        String secondPriceValue = priceValues[1];
        if (Integer.parseInt(priceValueToAddOn) < Integer.parseInt(firstPriceValue)) {
            priceRange = priceValueToAddOn + PRICE_RANGE_DASH + Integer.parseInt(secondPriceValue);
        } else if (Integer.parseInt(priceValueToAddOn) > Integer.parseInt(secondPriceValue)) {
            priceRange = Integer.parseInt(firstPriceValue) + PRICE_RANGE_DASH + priceValueToAddOn;
        } else {
            priceRange = firstPriceValue + PRICE_RANGE_DASH + secondPriceValue;
        }
        return priceRange;
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
        private Price price;
        private List<Review> reviews;

        public AddOnToEntryDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public AddOnToEntryDescriptor(AddOnToEntryDescriptor toCopy) {
            setReviews(toCopy.reviews);
            setPrice(toCopy.price);
        }

        /**
         * Returns true if at least one field is updated with more details (eg. additional reviews).
         */
        public boolean isAnyFieldAddedOn() {
            return CollectionUtil.isAnyNonNull(reviews, price);
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

        public void setPrice(Price price) {
            this.price = price;
        }

        public Optional<Price> getPrice() {
            return Optional.ofNullable(price);
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

            return getReviews().equals(e.getReviews())
                    && getPrice().equals((e.getPrice()));
        }
    }
}
