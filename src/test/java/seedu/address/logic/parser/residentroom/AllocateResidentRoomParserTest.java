package seedu.address.logic.parser.residentroom;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROOM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESIDENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.residentroom.AllocateResidentRoomCommand;

public class AllocateResidentRoomParserTest {
    private static final String VALID_RESIDENT_INDEX_DESC = " " + PREFIX_RESIDENT_INDEX + " " + "1";
    private static final String VALID_RESIDENT_INDEX_DESC_2 = " " + PREFIX_RESIDENT_INDEX + " " + "2";
    private static final String VALID_ROOM_INDEX_DESC = " " + PREFIX_ROOM_INDEX + " " + "1";
    private static final String VALID_ROOM_INDEX_DESC_2 = " " + PREFIX_ROOM_INDEX + " " + "2";
    private static final String INVALID_RESIDENT_INDEX_DESC = " " + PREFIX_RESIDENT_INDEX + " " + "a";
    private static final String INVALID_ROOM_INDEX_DESC = " " + PREFIX_ROOM_INDEX + " " + "a";


    private AllocateResidentRoomCommandParser parser = new AllocateResidentRoomCommandParser();


    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_RESIDENT_INDEX_DESC + VALID_ROOM_INDEX_DESC,
                new AllocateResidentRoomCommand(INDEX_FIRST, INDEX_FIRST));


        // multiple resident index - last index accepted
        assertParseSuccess(parser, VALID_RESIDENT_INDEX_DESC_2 + VALID_RESIDENT_INDEX_DESC
                        + VALID_ROOM_INDEX_DESC, new AllocateResidentRoomCommand(INDEX_FIRST, INDEX_FIRST));

        // multiple room index - last index accepted
        assertParseSuccess(parser, VALID_RESIDENT_INDEX_DESC + VALID_ROOM_INDEX_DESC_2 + VALID_ROOM_INDEX_DESC,
                new AllocateResidentRoomCommand(INDEX_FIRST, INDEX_FIRST));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AllocateResidentRoomCommand.MESSAGE_USAGE);

        // missing resident index prefix
        assertParseFailure(parser, INDEX_FIRST + VALID_ROOM_INDEX_DESC, expectedMessage);

        // missing room index prefix
        assertParseFailure(parser, VALID_RESIDENT_INDEX_DESC + " " + INDEX_FIRST, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, INDEX_FIRST + " " + INDEX_FIRST, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid resident index
        assertParseFailure(parser, INVALID_RESIDENT_INDEX_DESC + VALID_ROOM_INDEX_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AllocateResidentRoomCommand.MESSAGE_USAGE));

        // invalid room index
        assertParseFailure(parser, VALID_RESIDENT_INDEX_DESC + INVALID_ROOM_INDEX_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AllocateResidentRoomCommand.MESSAGE_USAGE));

        // two invalid values, invalid command error
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_ROOM_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AllocateResidentRoomCommand.MESSAGE_USAGE));

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VALID_RESIDENT_INDEX_DESC + VALID_ROOM_INDEX_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AllocateResidentRoomCommand.MESSAGE_USAGE));
    }
}
