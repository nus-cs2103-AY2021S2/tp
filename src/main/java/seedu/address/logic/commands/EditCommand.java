package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRESSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIZE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GARMENTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.description.Description;
import seedu.address.model.garment.Colour;
import seedu.address.model.garment.DressCode;
import seedu.address.model.garment.Garment;
import seedu.address.model.garment.Name;
import seedu.address.model.garment.Size;
import seedu.address.model.garment.Type;

/**
 * Edits the details of an existing garment in the wardrobe.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the garment identified "
            + "by the index number used in the displayed garment list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_SIZE + "SIZE] "
            + "[" + PREFIX_COLOUR + "COLOUR] "
            + "[" + PREFIX_DRESSCODE + "DRESSCODE] "
            + "[" + PREFIX_TYPE + "TYPE] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_SIZE + "36 "
            + PREFIX_COLOUR + "blue";

    public static final String MESSAGE_EDIT_GARMENT_SUCCESS = "Edited Garment: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_GARMENT = "This garment already exists in the wardrobe.";

    private final Index index;
    private final EditGarmentDescriptor editGarmentDescriptor;

    /**
     * @param index of the garment in the filtered garment list to edit
     * @param editGarmentDescriptor details to edit the garment with
     */
    public EditCommand(Index index, EditGarmentDescriptor editGarmentDescriptor) {
        requireNonNull(index);
        requireNonNull(editGarmentDescriptor);

        this.index = index;
        this.editGarmentDescriptor = new EditGarmentDescriptor(editGarmentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Garment> lastShownList = model.getFilteredGarmentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GARMENT_DISPLAYED_INDEX);
        }

        Garment garmentToEdit = lastShownList.get(index.getZeroBased());
        Garment editedGarment = createEditedGarment(garmentToEdit, editGarmentDescriptor);

        if (!garmentToEdit.isSameGarment(editedGarment) && model.hasGarment(editedGarment)) {
            throw new CommandException(MESSAGE_DUPLICATE_GARMENT);
        }

        model.setGarment(garmentToEdit, editedGarment);
        model.updateFilteredGarmentList(PREDICATE_SHOW_ALL_GARMENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_GARMENT_SUCCESS, editedGarment));
    }

    /**
     * Creates and returns a {@code Garment} with the details of {@code garmentToEdit}
     * edited with {@code editGarmentDescriptor}.
     */
    private static Garment createEditedGarment(Garment garmentToEdit, EditGarmentDescriptor editGarmentDescriptor) {
        assert garmentToEdit != null;

        Name updatedName = editGarmentDescriptor.getName().orElse(garmentToEdit.getName());
        Size updatedSize = editGarmentDescriptor.getSize().orElse(garmentToEdit.getSize());
        Colour updatedColour = editGarmentDescriptor.getColour().orElse(garmentToEdit.getColour());
        DressCode updatedDressCode = editGarmentDescriptor.getDressCode().orElse(garmentToEdit.getDressCode());
        Type updatedType = editGarmentDescriptor.getType().orElse(garmentToEdit.getType());
        Set<Description> updatedDescriptions = editGarmentDescriptor.getDescriptions()
                .orElse(garmentToEdit.getDescriptions());

        return new Garment(updatedName, updatedSize, updatedColour, updatedDressCode, updatedType,
                updatedDescriptions, garmentToEdit.getLastUse()); //editing does not change last used
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
                && editGarmentDescriptor.equals(e.editGarmentDescriptor);
    }

    /**
     * Stores the details to edit the garment with.
     * Each non-empty field value will replace the
     * corresponding field value of the garment.
     */
    public static class EditGarmentDescriptor {
        private Name name;
        private Size size;
        private Colour colour;
        private DressCode dresscode;
        private Type type;
        private Set<Description> descriptions;

        public EditGarmentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code descriptions} is used internally.
         */
        public EditGarmentDescriptor(EditGarmentDescriptor toCopy) {
            setName(toCopy.name);
            setSize(toCopy.size);
            setColour(toCopy.colour);
            setDressCode(toCopy.dresscode);
            setType(toCopy.type);
            setDescriptions(toCopy.descriptions);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, size, colour, dresscode, type, descriptions);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setSize(Size size) {
            this.size = size;
        }

        public Optional<Size> getSize() {
            return Optional.ofNullable(size);
        }

        public void setColour(Colour colour) {
            this.colour = colour;
        }

        public Optional<Colour> getColour() {
            return Optional.ofNullable(colour);
        }

        public void setDressCode(DressCode dresscode) {
            this.dresscode = dresscode;
        }

        public Optional<DressCode> getDressCode() {
            return Optional.ofNullable(dresscode);
        }

        public void setType(Type type) {
            this.type = type;
        }

        public Optional<Type> getType() {
            return Optional.ofNullable(type);
        }

        /**
         * Sets {@code descriptions} to this object's {@code descriptions}.
         * A defensive copy of {@code descriptions} is used internally.
         */
        public void setDescriptions(Set<Description> descriptions) {
            this.descriptions = (descriptions != null) ? new HashSet<>(descriptions) : null;
        }

        /**
         * Returns an unmodifiable description set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code descriptions} is null.
         */
        public Optional<Set<Description>> getDescriptions() {
            return (descriptions != null) ? Optional.of(Collections.unmodifiableSet(descriptions)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditGarmentDescriptor)) {
                return false;
            }

            // state check
            EditGarmentDescriptor e = (EditGarmentDescriptor) other;

            return getName().equals(e.getName())
                    && getSize().equals(e.getSize())
                    && getColour().equals(e.getColour())
                    && getDressCode().equals(e.getDressCode())
                    && getType().equals(e.getType())
                    && getDescriptions().equals(e.getDescriptions());
        }
    }
}
