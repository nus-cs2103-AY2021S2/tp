package seedu.address.logic.commands.room;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_OCCUPANCY_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_TYPE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ROOMS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.room.IsOccupied;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomNumber;
import seedu.address.model.room.RoomType;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing room in SunRez.
 */
public class EditRoomCommand extends Command {

    public static final String COMMAND_WORD = "oedit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the room identified "
            + "by the index number used in the displayed room list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_ROOM_NUMBER + "NAME] "
            + "[" + PREFIX_ROOM_TYPE + "TYPE] "
            + "[" + PREFIX_ROOM_OCCUPANCY_STATUS + "OCCUPANCY STATUS] "
            + "[" + PREFIX_ROOM_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ROOM_NUMBER + "12-100 "
            + PREFIX_ROOM_OCCUPANCY_STATUS + "y";

    public static final String MESSAGE_EDIT_ROOM_SUCCESS = "Edited Room: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ROOM = "This room already exists in the address book.";

    private final Index index;
    private final EditRoomDescriptor editRoomDescriptor;

    /**
     * @param index              of the room in the filtered room list to edit
     * @param editRoomDescriptor details to edit the room with
     */
    public EditRoomCommand(Index index, EditRoomDescriptor editRoomDescriptor) {
        requireNonNull(index);
        requireNonNull(editRoomDescriptor);

        this.index = index;
        this.editRoomDescriptor = new EditRoomDescriptor(editRoomDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Room> lastShownList = model.getFilteredRoomList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
        }

        Room roomToEdit = lastShownList.get(index.getZeroBased());
        Room editedRoom = createEditedRoom(roomToEdit, editRoomDescriptor);

        if (!roomToEdit.isSameRoom(editedRoom) && model.hasRoom(editedRoom)) {
            throw new CommandException(MESSAGE_DUPLICATE_ROOM);
        }

        model.setRoom(roomToEdit, editedRoom);
        model.updateFilteredRoomList(PREDICATE_SHOW_ALL_ROOMS);
        return new CommandResult(String.format(MESSAGE_EDIT_ROOM_SUCCESS, editedRoom));
    }

    /**
     * Creates and returns a {@code Room} with the details of {@code roomToEdit}
     * edited with {@code editRoomDescriptor}.
     */
    private static Room createEditedRoom(Room roomToEdit, EditRoomDescriptor editRoomDescriptor) {
        assert roomToEdit != null;

        RoomNumber updatedRoomNumber = editRoomDescriptor.getRoomNumber().orElse(roomToEdit.getRoomNumber());
        RoomType updatedRoomType = editRoomDescriptor.getRoomType().orElse(roomToEdit.getRoomType());
        IsOccupied updatedIsOccupied = editRoomDescriptor.getIsOccupied().orElse(roomToEdit.isOccupied());
        Set<Tag> updatedTags = editRoomDescriptor.getTags().orElse(roomToEdit.getTags());

        return new Room(updatedRoomNumber, updatedRoomType, updatedIsOccupied, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditRoomCommand)) {
            return false;
        }

        // state check
        EditRoomCommand e = (EditRoomCommand) other;
        return index.equals(e.index)
                && editRoomDescriptor.equals(e.editRoomDescriptor);
    }

    /**
     * Stores the details to edit the room with. Each non-empty field value will replace the
     * corresponding field value of the room.
     */
    public static class EditRoomDescriptor {
        private RoomNumber roomNumber;
        private RoomType roomType;
        private IsOccupied isOccupied;
        private Set<Tag> tags;

        public EditRoomDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditRoomDescriptor(EditRoomDescriptor toCopy) {
            setRoomNumber(toCopy.roomNumber);
            setRoomType(toCopy.roomType);
            setIsOccupied(toCopy.isOccupied);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(roomNumber, roomType, isOccupied, tags);
        }

        public void setRoomNumber(RoomNumber roomNumber) {
            this.roomNumber = roomNumber;
        }

        public Optional<RoomNumber> getRoomNumber() {
            return Optional.ofNullable(roomNumber);
        }

        public void setRoomType(RoomType roomType) {
            this.roomType = roomType;
        }

        public Optional<RoomType> getRoomType() {
            return Optional.ofNullable(roomType);
        }

        public void setIsOccupied(IsOccupied isOccupied) {
            this.isOccupied = isOccupied;
        }

        public Optional<IsOccupied> getIsOccupied() {
            return Optional.ofNullable(isOccupied);
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
            if (!(other instanceof EditRoomDescriptor)) {
                return false;
            }

            // state check
            EditRoomDescriptor e = (EditRoomDescriptor) other;

            return getRoomNumber().equals(e.getRoomNumber())
                    && getRoomType().equals(e.getRoomType())
                    && getIsOccupied().equals(e.getIsOccupied())
                    && getTags().equals(e.getTags());
        }
    }
}
