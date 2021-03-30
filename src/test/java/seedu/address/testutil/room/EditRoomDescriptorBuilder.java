package seedu.address.testutil.room;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.room.EditRoomCommand.EditRoomDescriptor;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomNumber;
import seedu.address.model.room.RoomType;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditRoomDescriptor objects.
 */
public class EditRoomDescriptorBuilder {
    private EditRoomDescriptor descriptor;

    public EditRoomDescriptorBuilder() {
        this.descriptor = new EditRoomDescriptor();
    }

    public EditRoomDescriptorBuilder(EditRoomDescriptor descriptor) {
        this.descriptor = new EditRoomDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditRoomDescriptorBuilder} with fields containing {@code room}'s details
     */
    public EditRoomDescriptorBuilder(Room room) {
        descriptor = new EditRoomDescriptor();
        descriptor.setRoomNumber(room.getRoomNumber());
        descriptor.setRoomType(room.getRoomType());
        descriptor.setIsOccupied(room.isOccupied());
        descriptor.setTags(room.getTags());
    }

    /**
     * Sets the {@code RoomNumber} of the {@code EditRoomDescriptorBuilder} that we are building.
     */
    public EditRoomDescriptorBuilder withRoomNumber(String roomNumber) {
        descriptor.setRoomNumber(new RoomNumber(roomNumber));
        return this;
    }

    /**
     * Sets the {@code RoomType} of the {@code EditRoomDescriptorBuilder} that we are building.
     */
    public EditRoomDescriptorBuilder withRoomType(String roomType) {
        descriptor.setRoomType(new RoomType(roomType));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditRoomDescriptorBuilder}
     * that we are building.
     */
    public EditRoomDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Builds the EditRoomDescriptor based on the provided parameters
     *
     * @return EditRoomDescriptor with properties issued through the various
     * methods of the {@code EditRoomDescriptorBuiler} class
     */
    public EditRoomDescriptor build() {
        return descriptor;
    }
}
