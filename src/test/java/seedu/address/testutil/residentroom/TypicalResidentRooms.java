package seedu.address.testutil.residentroom;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.residentroom.ResidentRoom;

/**
 * A utility class containing a list of {@code Resident} objects to be used in tests.
 */
public class TypicalResidentRooms {

    public static final ResidentRoom ALICE_ROOM_NUMBER = new ResidentRoomBuilder().withName("Alice Pauline")
            .withRoomNumber("01-100")
            .build();
    public static final ResidentRoom BENSON_ROOM_NUMBER = new ResidentRoomBuilder().withName("Benson Meier")
            .withRoomNumber("01-101")
            .build();
    public static final ResidentRoom CARL_ROOM_NUMBER = new ResidentRoomBuilder().withName("Carl Kurz")
            .withRoomNumber("01-102").build();
    public static final ResidentRoom DANIEL_ROOM_NUMBER = new ResidentRoomBuilder().withName("Daniel Meier")
            .withRoomNumber("01-103")
            .build();
    public static final ResidentRoom ELLE_ROOM_NUMBER = new ResidentRoomBuilder().withName("Elle Meyer")
            .withRoomNumber("01-104")
            .build();
    public static final ResidentRoom FIONA_ROOM_NUMBER = new ResidentRoomBuilder().withName("Fiona Kunz")
            .withRoomNumber("01-105")
            .build();
    public static final ResidentRoom GEORGE_ROOM_NUMBER = new ResidentRoomBuilder().withName("George Best")
            .withRoomNumber("01-106")
            .build();

    // Manually added
    public static final ResidentRoom HOON_ROOM_NUMBER = new ResidentRoomBuilder().withName("Hoon Meier")
            .withRoomNumber("01-107")
            .build();
    public static final ResidentRoom IDA_ROOM_NUMBER = new ResidentRoomBuilder().withName("Ida Mueller")
            .withRoomNumber("01-108")
            .build();

    // Manually added - ResidentRoom's details found in {@code CommandTestUtil}
    public static final ResidentRoom AMY_ROOM_NUMBER = new ResidentRoomBuilder().withName(VALID_NAME_AMY)
            .withRoomNumber(VALID_ROOM_NUMBER_AMY)
            .build();
    public static final ResidentRoom BOB_ROOM_NUMBER = new ResidentRoomBuilder().withName(VALID_NAME_BOB)
            .withRoomNumber(VALID_ROOM_NUMBER_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    // First Typical ResidentRoom
    public static final ResidentRoom AARON_ROOM_NUMBER = new ResidentRoomBuilder().withName("Aaron Ang")
            .withRoomNumber("01-000").build();


    private TypicalResidentRooms() {
    } // prevents instantiation

    public static List<ResidentRoom> getTypicalResidentRooms() {
        return new ArrayList<>(Arrays.asList(ALICE_ROOM_NUMBER, BENSON_ROOM_NUMBER, CARL_ROOM_NUMBER,
                DANIEL_ROOM_NUMBER, ELLE_ROOM_NUMBER, FIONA_ROOM_NUMBER, GEORGE_ROOM_NUMBER));
    }
}
