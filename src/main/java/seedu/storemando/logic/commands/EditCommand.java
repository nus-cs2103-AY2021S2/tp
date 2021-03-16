package seedu.storemando.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_EXPIRYDATE;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.storemando.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.storemando.commons.core.Messages;
import seedu.storemando.commons.core.index.Index;
import seedu.storemando.commons.util.CollectionUtil;
import seedu.storemando.logic.commands.exceptions.CommandException;
import seedu.storemando.model.Model;
import seedu.storemando.model.expirydate.ExpiryDate;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.item.ItemName;
import seedu.storemando.model.item.Location;
import seedu.storemando.model.item.Quantity;
import seedu.storemando.model.tag.Tag;

/**
 * Edits the details of an existing item in the storemando.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the item identified "
        + "by the index number used in the displayed item list. "
        + "Existing values will be overwritten by the input values as long as input values are "
        + "not all the same as existing values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_NAME + "ITEM NAME] "
        + "[" + PREFIX_QUANTITY + "QUANTITY] "
        + "[" + PREFIX_EXPIRYDATE + "EXPIRYDATE] "
        + "[" + PREFIX_LOCATION + "LOCATION] "
        + "[" + PREFIX_TAG + "TAG]...\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_QUANTITY + "5 "
        + PREFIX_EXPIRYDATE + "2018-10-10";

    public static final String MESSAGE_EDIT_ITEM_SUCCESS = "Edited Item: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ITEM = "This item already exists in storemando.";
    public static final String MESSAGE_NO_CHANGE = "Item not edited! Specified change in item details same as "
        + "original.";
    public static final String MESSAGE_ITEM_EXPIRED_WARNING = "\nWarning: Item has already expired!";

    private final Index index;
    private final EditItemDescriptor editItemDescriptor;

    /**
     * @param index              of the item in the filtered item list to edit
     * @param editItemDescriptor details to edit the item with
     */
    public EditCommand(Index index, EditItemDescriptor editItemDescriptor) {
        requireNonNull(index);
        requireNonNull(editItemDescriptor);

        this.index = index;
        this.editItemDescriptor = new EditItemDescriptor(editItemDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Item> lastShownList = model.getFilteredItemList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        }

        Item itemToEdit = lastShownList.get(index.getZeroBased());
        Item editedItem = createEditedItem(itemToEdit, editItemDescriptor);

        if (!itemToEdit.isSameItem(editedItem) && model.hasItem(editedItem)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }
        if (itemToEdit.equals(editedItem)) { //if edited item has the same fields as original item
            throw new CommandException(MESSAGE_NO_CHANGE);
        }
        model.setItem(itemToEdit, editedItem);
        model.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);

        String feedback = String.format(MESSAGE_EDIT_ITEM_SUCCESS, editedItem);
        if (editedItem.isExpired()) {
            feedback += MESSAGE_ITEM_EXPIRED_WARNING;
        }

        return new CommandResult(feedback);
    }

    /**
     * Creates and returns a {@code Item} with the details of {@code itemToEdit}
     * edited with {@code editItemDescriptor}.
     */
    private static Item createEditedItem(Item itemToEdit, EditItemDescriptor editItemDescriptor) {
        assert itemToEdit != null;

        ItemName updatedName = editItemDescriptor.getItemName().orElse(itemToEdit.getItemName());
        Quantity updatedQuantity = editItemDescriptor.getQuantity().orElse(itemToEdit.getQuantity());
        ExpiryDate updatedExpiryDate = editItemDescriptor.getExpiryDate().orElse(itemToEdit.getExpiryDate());
        Location updatedLocation = editItemDescriptor.getLocation().orElse(itemToEdit.getLocation());
        Set<Tag> updatedTags = editItemDescriptor.getTags().orElse(itemToEdit.getTags());

        return new Item(updatedName, updatedQuantity, updatedExpiryDate, updatedLocation, updatedTags);
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
            && editItemDescriptor.equals(e.editItemDescriptor);
    }

    /**
     * Stores the details to edit the item with. Each non-empty field value will replace the
     * corresponding field value of the item.
     */
    public static class EditItemDescriptor {
        private ItemName name;
        private Quantity quantity;
        private ExpiryDate expiryDate;
        private Location location;
        private Set<Tag> tags;

        public EditItemDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditItemDescriptor(EditItemDescriptor toCopy) {
            setItemName(toCopy.name);
            setQuantity(toCopy.quantity);
            setExpiryDate(toCopy.expiryDate);
            setLocation(toCopy.location);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, quantity, expiryDate, location, tags);
        }

        public void setItemName(ItemName name) {
            this.name = name;
        }

        public Optional<ItemName> getItemName() {
            return Optional.ofNullable(name);
        }

        public void setQuantity(Quantity quantity) {
            this.quantity = quantity;
        }

        public Optional<Quantity> getQuantity() {
            return Optional.ofNullable(quantity);
        }

        public void setExpiryDate(ExpiryDate expiryDate) {
            this.expiryDate = expiryDate;
        }

        public Optional<ExpiryDate> getExpiryDate() {
            return Optional.ofNullable(expiryDate);
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);
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
            if (!(other instanceof EditItemDescriptor)) {
                return false;
            }

            // state check
            EditItemDescriptor e = (EditItemDescriptor) other;

            return getItemName().equals(e.getItemName())
                && getQuantity().equals(e.getQuantity())
                && getExpiryDate().equals(e.getExpiryDate())
                && getLocation().equals(e.getLocation())
                && getTags().equals(e.getTags());
        }
    }
}
