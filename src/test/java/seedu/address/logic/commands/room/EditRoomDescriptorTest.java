package seedu.address.logic.commands.room;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.room.RoomCommandTestUtil.VALID_ROOM_DESCRIPTOR_ONE;
import static seedu.address.logic.commands.room.RoomCommandTestUtil.VALID_ROOM_DESCRIPTOR_TWO;
import static seedu.address.logic.commands.room.RoomCommandTestUtil.VALID_ROOM_TAGS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.room.EditRoomCommand.EditRoomDescriptor;
import seedu.address.testutil.room.EditRoomDescriptorBuilder;

public class EditRoomDescriptorTest {
    @Test
    public void equals() {
        // Same values -> return true
        EditRoomDescriptor descriptorWithSameValues = new EditRoomDescriptor(VALID_ROOM_DESCRIPTOR_ONE);
        assertTrue(VALID_ROOM_DESCRIPTOR_ONE.equals(descriptorWithSameValues));

        // Same object -> return true
        assertTrue(VALID_ROOM_DESCRIPTOR_ONE.equals(VALID_ROOM_DESCRIPTOR_ONE));

        // null -> return false
        assertFalse(VALID_ROOM_DESCRIPTOR_ONE.equals(null));

        // different types -> return false
        assertFalse(VALID_ROOM_DESCRIPTOR_ONE.equals(5));

        // different values -> return false
        assertFalse(VALID_ROOM_DESCRIPTOR_ONE.equals(VALID_ROOM_DESCRIPTOR_TWO));

        // tests for each of the different fields
        EditRoomDescriptor editedOne;

        // different room number -> return false
        editedOne = new EditRoomDescriptorBuilder(VALID_ROOM_DESCRIPTOR_ONE)
                .withRoomNumber("08-121")
                .build();
        assertFalse(VALID_ROOM_DESCRIPTOR_ONE.equals(editedOne));

        // different room type -> return false
        editedOne = new EditRoomDescriptorBuilder(VALID_ROOM_DESCRIPTOR_ONE)
                .withRoomType("suite_non_ac")
                .build();
        assertFalse(VALID_ROOM_DESCRIPTOR_ONE.equals(editedOne));

        // different tags -> return false
        editedOne = new EditRoomDescriptorBuilder(VALID_ROOM_DESCRIPTOR_ONE)
                .withTags(VALID_ROOM_TAGS)
                .build();
        assertFalse(VALID_ROOM_DESCRIPTOR_ONE.equals(editedOne));


    }
}
