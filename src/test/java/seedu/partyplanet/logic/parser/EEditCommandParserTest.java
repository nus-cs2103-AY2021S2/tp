package seedu.partyplanet.logic.parser;

import static seedu.partyplanet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.partyplanet.logic.commands.CommandTestUtil.DATE_DESC_CNY;
import static seedu.partyplanet.logic.commands.CommandTestUtil.DATE_DESC_EASTER;
import static seedu.partyplanet.logic.commands.CommandTestUtil.INVALID_EVENT_DATE_DESC;
import static seedu.partyplanet.logic.commands.CommandTestUtil.INVALID_EVENT_NAME_DESC;
import static seedu.partyplanet.logic.commands.CommandTestUtil.INVALID_EVENT_REMARK_DESC;
import static seedu.partyplanet.logic.commands.CommandTestUtil.NAME_DESC_CNY;
import static seedu.partyplanet.logic.commands.CommandTestUtil.NAME_DESC_EASTER;
import static seedu.partyplanet.logic.commands.CommandTestUtil.REMARK_DESC_CNY;
import static seedu.partyplanet.logic.commands.CommandTestUtil.REMARK_DESC_EASTER;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_DATE_CNY;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_DATE_EASTER;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_NAME_CNY;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_NAME_EASTER;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_REMARK_CNY;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_REMARK_EASTER;
import static seedu.partyplanet.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.partyplanet.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.partyplanet.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.partyplanet.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static seedu.partyplanet.testutil.TypicalIndexes.INDEX_THIRD_EVENT;

import org.junit.jupiter.api.Test;

import seedu.partyplanet.commons.core.index.Index;
import seedu.partyplanet.logic.commands.EEditCommand;
import seedu.partyplanet.logic.commands.EEditCommand.EditEventDescriptor;
import seedu.partyplanet.model.event.EventDate;
import seedu.partyplanet.model.name.Name;
import seedu.partyplanet.model.remark.Remark;
import seedu.partyplanet.testutil.EditEventDescriptorBuilder;

public class EEditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EEditCommand.MESSAGE_USAGE);

    private EEditCommandParser parser = new EEditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_EASTER, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EEditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_CNY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_CNY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_EVENT_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_EVENT_DATE_DESC, EventDate.MESSAGE_CONSTRAINTS); // invalid event date
        assertParseFailure(parser, "1" + INVALID_EVENT_REMARK_DESC, Remark.MESSAGE_CONSTRAINTS); // invalid empty remark

        // invalid date followed by valid remark
        assertParseFailure(parser, "1" + INVALID_EVENT_DATE_DESC + REMARK_DESC_CNY, EventDate.MESSAGE_CONSTRAINTS);

        // valid date followed by invalid remark. The test case for invalid date followed by valid remark
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + DATE_DESC_EASTER + INVALID_EVENT_REMARK_DESC, Remark.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_EVENT_NAME_DESC + INVALID_EVENT_DATE_DESC + VALID_REMARK_EASTER,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_EVENT;
        String userInput = targetIndex.getOneBased() + DATE_DESC_CNY + REMARK_DESC_CNY + NAME_DESC_CNY;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withName(VALID_NAME_CNY)
                .withDate(VALID_DATE_CNY)
                .withRemark(VALID_REMARK_CNY)
                .build();
        EEditCommand expectedCommand = new EEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + REMARK_DESC_EASTER;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withRemark(VALID_REMARK_EASTER)
                .build();
        EEditCommand expectedCommand = new EEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_EVENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_CNY;
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withName(VALID_NAME_CNY)
                .build();
        EEditCommand expectedCommand = new EEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + DATE_DESC_CNY;
        descriptor = new EditEventDescriptorBuilder()
                .withDate(VALID_DATE_CNY)
                .build();
        expectedCommand = new EEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // remark
        userInput = targetIndex.getOneBased() + REMARK_DESC_CNY;
        descriptor = new EditEventDescriptorBuilder()
                .withRemark(VALID_REMARK_CNY)
                .build();
        expectedCommand = new EEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + DATE_DESC_CNY + REMARK_DESC_CNY + DATE_DESC_EASTER
                + NAME_DESC_EASTER + DATE_DESC_EASTER + REMARK_DESC_EASTER + NAME_DESC_CNY;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withName(VALID_NAME_CNY)
                .withDate(VALID_DATE_EASTER).withRemark(VALID_REMARK_EASTER)
                .build();
        EEditCommand expectedCommand = new EEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + INVALID_EVENT_DATE_DESC + DATE_DESC_EASTER;
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withDate(VALID_DATE_EASTER)
                .build();
        EEditCommand expectedCommand = new EEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + REMARK_DESC_EASTER + INVALID_EVENT_DATE_DESC + REMARK_DESC_CNY
                + DATE_DESC_EASTER;
        descriptor = new EditEventDescriptorBuilder()
                .withDate(VALID_DATE_EASTER)
                .withRemark(VALID_REMARK_CNY)
                .build();
        expectedCommand = new EEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
