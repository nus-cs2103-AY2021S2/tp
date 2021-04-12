package seedu.address.logic.commands.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomNumberOrTagsContainsKeywordsPredicate;
import seedu.address.testutil.room.EditRoomDescriptorBuilder;

/**
 * A utility to assist in testing of {@code Room} and {@code UniqueRoomList}
 */
public class RoomCommandTestUtil {
    public static final String VALID_ROOM_NUMBER_ONE = "05-123";
    public static final String VALID_ROOM_NUMBER_TWO = "15-123";
    public static final String VALID_ROOM_NUMBER_THREE = "22-123";
    public static final List<String> VALID_ROOM_TYPES = new ArrayList<>(
            Arrays.asList("corridor_non_ac", "corridor_ac", "suite_non_ac", "suite_ac"));
    public static final List<String> VALID_ROOM_OCCUPANCIES = new ArrayList<>(Arrays.asList("y", "n"));
    public static final String[] VALID_ROOM_TAGS = new String[]{"tag1", "tag2"};

    public static final List<String> INVALID_VALID_ROOM_NUMBERS = new ArrayList<>(Arrays.asList(
            "00-100", // Room numbers cannot begin with 001
            "a15-312", // non number characters not allowed in room number
            "123-123", "12-12", "1-1", "123-12", "12 123" // room number format is dd-ddd
    ));
    public static final List<String> INVALID_ROOM_TYPES = new ArrayList<>(
            Arrays.asList("corridor_no_ac", "not aircon", "suite non ac", "123123", "something else altogether"));
    public static final List<String> INVALID_ROOM_OCCUPANCIES = new ArrayList<>(
            Arrays.asList("yes", "no", "true", "false", "something else altogether"));

    public static final EditRoomCommand.EditRoomDescriptor VALID_ROOM_DESCRIPTOR_ONE;
    public static final EditRoomCommand.EditRoomDescriptor VALID_ROOM_DESCRIPTOR_TWO;
    public static final EditRoomCommand.EditRoomDescriptor VALID_ROOM_DESCRIPTOR_RANDOM;

    static {
        Random random = new Random();
        // 15-312, corridor non ac, not occupied
        VALID_ROOM_DESCRIPTOR_ONE = new EditRoomDescriptorBuilder().withRoomNumber(VALID_ROOM_NUMBER_ONE)
                .withRoomType(VALID_ROOM_TYPES.get(0))
                .build();

        // 12-322, corridor ac, occupied
        VALID_ROOM_DESCRIPTOR_TWO = new EditRoomDescriptorBuilder().withRoomNumber(VALID_ROOM_NUMBER_TWO)
                .withRoomType(VALID_ROOM_TYPES.get(1))
                .build();

        // Random build based on valid parameters
        VALID_ROOM_DESCRIPTOR_RANDOM = new EditRoomDescriptorBuilder().withRoomNumber(VALID_ROOM_NUMBER_THREE)
                // Pick a random valid room type, there are only 4
                .withRoomType(VALID_ROOM_TYPES.get(random.nextInt(VALID_ROOM_TYPES.size())))
                .build();
    }

    /**
     * Updates {@code model}'s filtered list to show only the room at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showRoomAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredResidentList().size());

        Room room = model.getFilteredRoomList().get(targetIndex.getZeroBased());
        final String[] splitName = room.getRoomNumber().roomNumber.split("\\s+");
        model.updateFilteredRoomList(new RoomNumberOrTagsContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredRoomList().size());
    }
}
