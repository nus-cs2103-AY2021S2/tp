package seedu.address.testutil.room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.room.Room;

public class TypicalRooms {
    public static final Room ROOM_CORRIDOR_NON_AC_OCCUPIED = new RoomBuilder()
            .withRoomNumber("09-100")
            .withRoomType("corridor_non_ac")
            .withOccupancyStatus("y")
            .build();
    public static final Room ROOM_CORRIDOR_NON_AC_NOT_OCCUPIED = new RoomBuilder()
            .withRoomNumber("09-110")
            .withRoomType("corridor_non_ac")
            .withOccupancyStatus("n")
            .build();
    public static final Room ROOM_CORRIDOR_AC_OCCUPIED = new RoomBuilder()
            .withRoomNumber("10-100")
            .withRoomType("corridor_ac")
            .withOccupancyStatus("y")
            .build();
    public static final Room ROOM_CORRIDOR_AC_NOT_OCCUPIED = new RoomBuilder()
            .withRoomNumber("10-110")
            .withRoomType("corridor_ac")
            .withOccupancyStatus("n")
            .build();
    public static final Room ROOM_SUITE_NON_AC_OCCUPIED = new RoomBuilder()
            .withRoomNumber("11-100")
            .withRoomType("suite_non_ac")
            .withOccupancyStatus("y")
            .build();
    public static final Room ROOM_SUITE_NON_AC_NOT_OCCUPIED = new RoomBuilder()
            .withRoomNumber("11-110")
            .withRoomType("suite_non_ac")
            .withOccupancyStatus("n")
            .build();
    public static final Room ROOM_SUITE_AC_OCCUPIED = new RoomBuilder()
            .withRoomNumber("12-100")
            .withRoomType("suite_ac")
            .withOccupancyStatus("y")
            .build();
    public static final Room ROOM_SUITE_AC_NOT_OCCUPIED = new RoomBuilder()
            .withRoomNumber("12-110")
            .withRoomType("suite_ac")
            .withOccupancyStatus("n")
            .build();

    public static final Room MANUAL_ROOM = new RoomBuilder()
            .withRoomNumber("13-110")
            .withRoomType("suite_ac")
            .withOccupancyStatus("n")
            .build();


    private TypicalRooms() {
    }

    public static List<Room> getTypicalRooms() {
        return new ArrayList<>(Arrays.asList(
                ROOM_CORRIDOR_NON_AC_OCCUPIED,
                ROOM_CORRIDOR_NON_AC_NOT_OCCUPIED,
                ROOM_CORRIDOR_AC_OCCUPIED,
                ROOM_CORRIDOR_AC_NOT_OCCUPIED,
                ROOM_SUITE_NON_AC_OCCUPIED,
                ROOM_SUITE_NON_AC_NOT_OCCUPIED,
                ROOM_SUITE_AC_OCCUPIED,
                ROOM_SUITE_AC_NOT_OCCUPIED
        ));
    }
}
