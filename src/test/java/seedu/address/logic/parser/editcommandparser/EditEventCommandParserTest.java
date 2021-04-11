package seedu.address.logic.parser.editcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENERAL_EVENT_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENERAL_EVENT_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENERAL_EVENT_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENERAL_EVENT_DESCRIPTION_1;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENERAL_EVENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX_1;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX_2;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.LocalDateTimeUtil;
import seedu.address.logic.commands.editcommand.EditEventCommand;
import seedu.address.model.event.GeneralEvent;
import seedu.address.model.module.Description;

public class EditEventCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditEventCommand.MESSAGE_USAGE);

    private EditEventCommandParser parser = new EditEventCommandParser();

    @Test
    public void parse_missingParts_failure() {

        // no field specified
        String userInput3 = "1 " + PREFIX_GENERAL_EVENT.getPrefix();
        assertParseFailure(parser, userInput3, Description.MESSAGE_CONSTRAINTS); //empty description

        String userInput4 = "1 " + PREFIX_DATE.getPrefix();
        assertParseFailure(parser, userInput4, GeneralEvent.DATE_CONSTRAINT); //empty date
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        String userInput1 = "-5 " + PREFIX_DATE.getPrefix() + VALID_GENERAL_EVENT_DATE_2;
        assertParseFailure(parser, userInput1 ,
                MESSAGE_INVALID_INDEX_1 + "g/" + MESSAGE_INVALID_INDEX_2);

        // zero index
        String userInput2 = "0 " + PREFIX_DATE.getPrefix() + VALID_GENERAL_EVENT_DATE_2;
        assertParseFailure(parser, userInput2 ,
                            MESSAGE_INVALID_INDEX_1 + "g/" + MESSAGE_INVALID_INDEX_2);

        // invalid arguments being parsed as preamble
        String userInput3 = "words " + PREFIX_DATE.getPrefix() + VALID_GENERAL_EVENT_DATE_2;
        assertParseFailure(parser, userInput3 ,
                MESSAGE_INVALID_INDEX_1 + "g/" + MESSAGE_INVALID_INDEX_2);

        // invalid prefix being parsed as preamble
        String userInput4 = "i/ " + PREFIX_DATE.getPrefix() + VALID_GENERAL_EVENT_DATE_2;
        assertParseFailure(parser, userInput4 ,
                MESSAGE_INVALID_INDEX_1 + "g/" + MESSAGE_INVALID_INDEX_2);
    }

    @Test
    public void parse_invalidValue_failure() {
        String userInput = "1 " + PREFIX_DATE.getPrefix() + INVALID_GENERAL_EVENT_DATE_1;
        assertParseFailure(parser, userInput, GeneralEvent.DATE_CONSTRAINT); // invalid date format
    }

    @Test
    public void parse_allFieldsSpecified_failure() {
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_GENERAL_EVENT.getPrefix()
                            + VALID_GENERAL_EVENT_DESCRIPTION_1 + " " + PREFIX_DATE + VALID_GENERAL_EVENT_DATE_1;
        Description eventEdit = new Description(VALID_GENERAL_EVENT_DESCRIPTION_1);
        LocalDateTime dateEdit = LocalDateTime.parse(VALID_GENERAL_EVENT_DATE_1, LocalDateTimeUtil.DATETIME_FORMATTER);
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex.getOneBased(), eventEdit, dateEdit);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput1 = targetIndex.getOneBased() + " " + PREFIX_GENERAL_EVENT + VALID_GENERAL_EVENT_DESCRIPTION_1;
        String userInput2 = targetIndex.getOneBased() + " " + PREFIX_DATE + VALID_GENERAL_EVENT_DATE_1;

        Description eventEdit = new Description(VALID_GENERAL_EVENT_DESCRIPTION_1);
        EditEventCommand expectedCommand1 = new EditEventCommand(targetIndex.getOneBased(), eventEdit, null);
        assertParseSuccess(parser, userInput1, expectedCommand1);

        LocalDateTime dateEdit = LocalDateTime.parse(VALID_GENERAL_EVENT_DATE_1, LocalDateTimeUtil.DATETIME_FORMATTER);
        EditEventCommand expectedCommand2 = new EditEventCommand(targetIndex.getOneBased(), null, dateEdit);
        assertParseSuccess(parser, userInput2, expectedCommand2);
    }
}
