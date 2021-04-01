package seedu.address.logic.parser.residentroom;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROOM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.ROOM_NUMBER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ROOM_NUMBER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.resident.EditResidentCommand;
import seedu.address.logic.commands.residentroom.AllocateResidentRoomCommand;
import seedu.address.logic.commands.room.EditRoomCommand;
import seedu.address.model.resident.Name;
import seedu.address.model.residentroom.ResidentRoom;
import seedu.address.model.room.IsOccupied;
import seedu.address.model.room.RoomNumber;
import seedu.address.testutil.resident.EditResidentDescriptorBuilder;
import seedu.address.testutil.room.EditRoomDescriptorBuilder;

public class AllocateResidentRoomParserTest {
    private AllocateResidentRoomCommandParser parser = new AllocateResidentRoomCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Name name = new Name(VALID_NAME_BOB);
        RoomNumber roomNumber = new RoomNumber(VALID_ROOM_NUMBER_BOB);
        ResidentRoom expectedResidentRoom = new ResidentRoom(name, roomNumber);

        EditResidentCommand.EditResidentDescriptor residentDescriptor =
                new EditResidentDescriptorBuilder().withRoom(VALID_ROOM_NUMBER_BOB).build();
        EditRoomCommand.EditRoomDescriptor roomDescriptor =
                new EditRoomDescriptorBuilder().withOccupancy(IsOccupied.OCCUPIED).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + ROOM_NUMBER_DESC_BOB,
                new AllocateResidentRoomCommand(expectedResidentRoom, residentDescriptor, roomDescriptor));


        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + ROOM_NUMBER_DESC_BOB,
                new AllocateResidentRoomCommand(expectedResidentRoom, residentDescriptor, roomDescriptor));

        // multiple roomNumber - last roomNumber accepted
        assertParseSuccess(parser, NAME_DESC_BOB + ROOM_NUMBER_DESC_AMY
                + ROOM_NUMBER_DESC_BOB,
                new AllocateResidentRoomCommand(expectedResidentRoom, residentDescriptor, roomDescriptor));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AllocateResidentRoomCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + ROOM_NUMBER_DESC_BOB, expectedMessage);

        // missing roomNumber prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_ROOM_NUMBER_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_ROOM_NUMBER_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + ROOM_NUMBER_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid roomNumber
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_ROOM_DESC, RoomNumber.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_ROOM_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + ROOM_NUMBER_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AllocateResidentRoomCommand.MESSAGE_USAGE));
    }
}
