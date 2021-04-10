package seedu.address.logic.parser.addcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENERAL_EVENT_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENERAL_EVENT_DESCRIPTION_1;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENERAL_EVENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalRemindMe.VALID_GENERAL_EVENT_1;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.addcommand.AddEventCommand;
import seedu.address.model.event.GeneralEvent;

public class AddEventCommandParserTest {
    private AddEventCommandParser parser = new AddEventCommandParser();

    @Test
    public void parse_allFieldPresent_success() {
        String userInput = " " + PREFIX_GENERAL_EVENT + VALID_GENERAL_EVENT_DESCRIPTION_1 + " "
                + PREFIX_DATE + VALID_GENERAL_EVENT_DATE_1;
        assertParseSuccess(parser, userInput , new AddEventCommand(VALID_GENERAL_EVENT_1));
    }

    @Test
    public void parse_missingValue_failure() {
        //missing event description
        String userInput1 = " " + PREFIX_GENERAL_EVENT + " " + PREFIX_DATE + VALID_GENERAL_EVENT_DATE_1;
        assertParseFailure(parser, userInput1, GeneralEvent.DESCRIPTION_CONSTRAINT);

        //missing date
        String userInput2 = " " + PREFIX_GENERAL_EVENT + VALID_GENERAL_EVENT_DESCRIPTION_1 + " " + PREFIX_DATE;
        assertParseFailure(parser, userInput2, GeneralEvent.DATE_CONSTRAINT);

        //missing date
        String userInput3 = " " + PREFIX_GENERAL_EVENT + VALID_GENERAL_EVENT_DESCRIPTION_1 + " " + PREFIX_DATE + "  ";
        assertParseFailure(parser, userInput3, GeneralEvent.DATE_CONSTRAINT);
    }

    @Test
    public void parse_wrongValue_failure() {
        //wrong values for date
        String userInput1 = " " + PREFIX_GENERAL_EVENT + VALID_GENERAL_EVENT_DESCRIPTION_1 + " " + PREFIX_DATE + "hi";
        assertParseFailure(parser, userInput1, GeneralEvent.DATE_CONSTRAINT);

        String userInput2 = " " + PREFIX_GENERAL_EVENT + VALID_GENERAL_EVENT_DESCRIPTION_1 + " " + PREFIX_DATE
                + "01/01/1998";
        assertParseFailure(parser, userInput2, GeneralEvent.DATE_CONSTRAINT);

        String userInput3 = " " + PREFIX_GENERAL_EVENT + VALID_GENERAL_EVENT_DESCRIPTION_1 + " " + PREFIX_DATE
                + "1200";
        assertParseFailure(parser, userInput3, GeneralEvent.DATE_CONSTRAINT);
    }

    @Test
    public void parse_missingPrefix_failure() {
        //missing general event prefix
        String userInput1 = " " + VALID_GENERAL_EVENT_DESCRIPTION_1 + " " + PREFIX_DATE + VALID_GENERAL_EVENT_DATE_1;
        assertParseFailure(parser, userInput1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));

        //missing date prefix
        String userInput2 = " " + PREFIX_GENERAL_EVENT + VALID_GENERAL_EVENT_DESCRIPTION_1 + " "
                + VALID_GENERAL_EVENT_DATE_1;
        assertParseFailure(parser, userInput2,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
    }
}
