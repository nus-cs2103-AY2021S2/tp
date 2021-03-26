package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.find.FindClientCommand;
import seedu.address.model.appointment.AppointmentContainsKeywordsPredicate;
import seedu.address.model.property.PropertyClientNamePredicate;

public class FindClientCommandParserTest {

    private FindClientCommandParser parser = new FindClientCommandParser();

    @Test
    public void parseEmptyTest() {
        assertParseFailure(parser, "  ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseValidKeywordsTest() {
        PropertyClientNamePredicate propertyClientNamePredicate =
                new PropertyClientNamePredicate(Collections.singletonList("bob"));
        AppointmentContainsKeywordsPredicate appointmentContainsKeywordsPredicate =
                new AppointmentContainsKeywordsPredicate(Collections.singletonList("bob"));
        FindClientCommand expectedFindCommand =
                new FindClientCommand(propertyClientNamePredicate, appointmentContainsKeywordsPredicate);

        assertParseSuccess(parser, "bob", expectedFindCommand);
    }
}
