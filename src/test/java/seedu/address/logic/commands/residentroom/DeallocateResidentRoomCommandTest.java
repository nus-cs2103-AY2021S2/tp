package seedu.address.logic.commands.residentroom;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.resident.Room.UNALLOCATED_REGEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.resident.TypicalResidents.AARON;
import static seedu.address.testutil.residentroom.TypicalResidentRooms.AARON_ROOM_NUMBER;
import static seedu.address.testutil.room.TypicalRooms.FIRST_ROOM;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.resident.Resident;
import seedu.address.model.residentroom.ResidentRoom;
import seedu.address.model.room.IsOccupied;
import seedu.address.model.room.Room;
import seedu.address.testutil.resident.ResidentBuilder;
import seedu.address.testutil.residentroom.ResidentRoomBuilder;
import seedu.address.testutil.room.RoomBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteResidentCommand}.
 */
public class DeallocateResidentRoomCommandTest {

    private Model model = new ModelManager();
    private ResidentRoom residentRoomToDelete = new ResidentRoomBuilder(AARON_ROOM_NUMBER).build();
    private Room roomBeforeEdit = FIRST_ROOM;
    private Room roomAfterEdit = new RoomBuilder(FIRST_ROOM).withOccupancyStatus(IsOccupied.UNOCCUPIED).build();
    private Resident residentBeforeEdit = AARON;
    private Resident residentAfterEdit = new ResidentBuilder(AARON).withRoom(UNALLOCATED_REGEX).build();

    @Test
    public void execute_validIndex_success() {
        // Setup the model before
        model.addResidentRoom(residentRoomToDelete);
        model.addRoom(roomBeforeEdit);
        model.addResident(residentBeforeEdit);

        //Setup the model after
        String expectedMessage = String.format(DeallocateResidentRoomCommand.MESSAGE_SUCCESS, residentRoomToDelete);
        ModelManager expectedModel = new ModelManager();
        expectedModel.addResident(residentAfterEdit);
        expectedModel.addRoom(roomAfterEdit);

        DeallocateResidentRoomCommand deallocateResidentRoomCommand = new DeallocateResidentRoomCommand(INDEX_FIRST);

        assertCommandSuccess(deallocateResidentRoomCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredResidentList().size() + 1);
        DeallocateResidentRoomCommand deallocateResidentRoomCommand =
                new DeallocateResidentRoomCommand(outOfBoundIndex);

        assertCommandFailure(deallocateResidentRoomCommand, model, Messages.MESSAGE_INVALID_RESIDENT_DISPLAYED_INDEX);
    }
}
