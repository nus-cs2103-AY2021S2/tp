package seedu.address.logic.commands.room;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_TYPE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ROOMS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.residentroom.ResidentRoom;
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
            + "[" + PREFIX_ROOM_NUMBER + "ROOM_NUMBER] "
            + "[" + PREFIX_ROOM_TYPE + "TYPE] "
            + "[" + PREFIX_ROOM_TAG + "TAG]...\n"
            + "At least one of the above optional parameters must be provided\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ROOM_NUMBER + "12-100 ";

    public static final String MESSAGE_EDIT_ROOM_SUCCESS = "Edited Room: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ROOM = "This room already exists in SunRez.";
    public static final String MESSAGE_ROOM_ALLOCATED_FAILURE = "The room has already been allocated to a resident so"
            + " you cannot change the room number. Please deallocate the room before editing the room number.";
    public static final String MESSAGE_ROOM_HAS_ISSUES = "This room still has issues assigned to it and so you cannot "
            + "change the room number. If you wish to edit the room number, please delete all corresponding issues "
            + "before editing the room.";
    private static final Logger logger = LogsCenter.getLogger(EditRoomCommand.class);

    private final Index index;
    private final EditRoomDescriptor editRoomDescriptor;

    /**
     * Creates an EditRoomCommand to edit the room at the specified {@code Index}.
     *
     * @param index              {@code Index} of the room in the filtered room list to edit.
     * @param editRoomDescriptor Details to edit the room with.
     */
    public EditRoomCommand(Index index, EditRoomDescriptor editRoomDescriptor) {
        requireNonNull(index);
        requireNonNull(editRoomDescriptor);

        this.index = index;
        this.editRoomDescriptor = new EditRoomDescriptor(editRoomDescriptor);
    }

    /**
     * @param model {@code Model} which the command should operate on.
     * @return A {@code CommandResult} with the {@code Room} that was edited.
     * @throws CommandException If the room being edited at the specified {@code Index} does not exist.
     * @throws CommandException If the {@code Room} being edited is being at the specified {@code Index} will have its
     *                          {@code RoomNumber} edited but the {@code Room} has been allocated to a {@code Resident}.
     * @throws CommandException If the {@code Room} being edited is being at the specified {@code Index} will have its
     *                          {@code RoomNumber} edited but the {@code Room} has an associated {@code Issue}.
     * @throws NullPointerException If {@code model} is null.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Room> lastShownList = model.getFilteredRoomList();

        if (lastShownList.size() == 0) {
            throw new CommandException(Messages.MESSAGE_NO_ROOMS);
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX, lastShownList.size()));
        }

        Room roomToEdit = lastShownList.get(index.getZeroBased());
        Room editedRoom = createEditedRoom(roomToEdit, editRoomDescriptor);

        assert roomToEdit != null;
        assert editedRoom != null;

        if (!roomToEdit.isSameRoom(editedRoom) && model.hasRoom(editedRoom)) {
            throw new CommandException(MESSAGE_DUPLICATE_ROOM);
        }

        // Block editing of the room number if the room is allocated to a resident.
        // Check 2 things. If they are both true, block the edit
        // 1. There is a resident allocated to the room
        // 2. The room's number will change in the editing process
        if (model.hasEitherResidentRoom(new ResidentRoom(null, roomToEdit.getRoomNumber()))
                && !roomToEdit.getRoomNumber().equals(editedRoom.getRoomNumber())) {
            throw new CommandException(MESSAGE_ROOM_ALLOCATED_FAILURE);
        }

        // Block editing of the room number if the room has issues attached to it.
        // Check 2 things. If they are both true, block the edit
        // 1. There is an issue that contains the room we are trying to edit
        // 2. The room's number will change in the editing process
        if (model.issuesContainRoom(roomToEdit)
                && !roomToEdit.getRoomNumber().equals(editedRoom.getRoomNumber())) {
            throw new CommandException(MESSAGE_ROOM_HAS_ISSUES);
        }

        model.setRoom(roomToEdit, editedRoom);
        model.updateFilteredRoomList(PREDICATE_SHOW_ALL_ROOMS);
        model.commitAddressBook();

        logger.info("EditRoomCommand successfully updated the model");
        return new CommandResult(String.format(MESSAGE_EDIT_ROOM_SUCCESS, editedRoom));
    }

    /**
     * Creates and returns a {@code Room} with the details of {@code roomToEdit} edited with {@code editRoomDescriptor}.
     *
     * @param roomToEdit         The {@code Room} that is being edited
     * @param editRoomDescriptor The {@code EditRoomDescriptor} that contains the details to edit the {@code Room} with.
     * @return The {@code Room} object after {@code roomToEdit} is updated by {@code editRoomDescriptor}.
     */
    public static Room createEditedRoom(Room roomToEdit, EditRoomDescriptor editRoomDescriptor) {
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
            return CollectionUtil.isAnyNonNull(roomNumber, roomType, tags);
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
