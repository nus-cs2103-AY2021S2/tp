package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_OPTION;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_OPTION;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_OPTION_ARGS;
import static seedu.address.logic.commands.FindAppointmentCommand.MESSAGE_MISSING_ADDRESS_ARGS;
import static seedu.address.logic.commands.FindAppointmentCommand.MESSAGE_MISSING_CHILD_ARGS;
import static seedu.address.logic.commands.FindAppointmentCommand.MESSAGE_MISSING_CONTACT_ARGS;
import static seedu.address.logic.commands.FindAppointmentCommand.MESSAGE_MISSING_DATE_ARGS;
import static seedu.address.logic.commands.FindAppointmentCommand.MESSAGE_MISSING_FIND_APPOINTMENT_OPTION;
import static seedu.address.logic.commands.FindAppointmentCommand.MESSAGE_MISSING_NAME_ARGS;
import static seedu.address.logic.commands.FindAppointmentCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.model.appointment.predicate.ApptAddressContainsKeywordsPredicate;
import seedu.address.model.appointment.predicate.ApptAnyContainsKeywordsPredicate;
import seedu.address.model.appointment.predicate.ApptContactsContainKeywordsPredicate;
import seedu.address.model.appointment.predicate.ApptDateContainsKeywordsPredicate;
import seedu.address.model.appointment.predicate.ApptNameContainsKeywordsPredicate;
import seedu.address.model.appointment.predicate.ApptTagsContainKeywordsPredicate;


public class FindAppointmentCommandParserTest {

    private FindAppointmentCommandParser parser = new FindAppointmentCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_noOptionValidArgs_returnsFindAppointmentCommand() {
        // no leading and trailing whitespaces
        FindAppointmentCommand expectedFindAppointmentCommand =

                new FindAppointmentCommand(
                        new ApptAnyContainsKeywordsPredicate(Arrays.asList("PTM", "meeting")));
        assertParseSuccess(parser, "PTM meeting", expectedFindAppointmentCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n PTM \n \t meeting  \t", expectedFindAppointmentCommand);


    }

    @Test
    public void parse_validOptionValidArgs_returnsFindAppointmentCommand() {

        // option name valid args
        FindAppointmentCommand expectedNameFindAppointmentCommand =
                new FindAppointmentCommand(new ApptNameContainsKeywordsPredicate(Arrays.asList("PTM", "meeting")));
        assertParseSuccess(parser, " o/name PTM meeting", expectedNameFindAppointmentCommand);

        // option name valid args with whitespace
        FindAppointmentCommand expectedNameSpaceFindAppointmentCommand =
                new FindAppointmentCommand(new ApptNameContainsKeywordsPredicate(Arrays.asList("PTM", "meeting")));
        assertParseSuccess(parser, " o/name PTM \n \t \tmeeting", expectedNameSpaceFindAppointmentCommand);

        // option child valid args
        FindAppointmentCommand expectedChildFindAppointmentCommand =
                new FindAppointmentCommand(new ApptTagsContainKeywordsPredicate(Arrays.asList("alice", "bob")));
        assertParseSuccess(parser, " o/child alice bob", expectedChildFindAppointmentCommand);

        // option address valid args
        FindAppointmentCommand expectedAddressFindAppointmentCommand =
                new FindAppointmentCommand(new ApptAddressContainsKeywordsPredicate(Arrays.asList("Town", "17")));
        assertParseSuccess(parser, " o/contact Town 17", expectedAddressFindAppointmentCommand);

        // option date valid args
        FindAppointmentCommand expectedDateFindAppointmentCommand =
                new FindAppointmentCommand(new ApptDateContainsKeywordsPredicate(Arrays.asList("2020", "05")));
        assertParseSuccess(parser, " o/contact 2020 05", expectedDateFindAppointmentCommand);

        // option contact valid args
        FindAppointmentCommand expectedContactFindAppointmentCommand =
                new FindAppointmentCommand(new ApptContactsContainKeywordsPredicate(Arrays.asList("alice", "bob")));
        assertParseSuccess(parser, " o/contact alice bob", expectedContactFindAppointmentCommand);


    }

    @Test
    public void parse_validOptionNoArgs_returnsFindAppointmentCommand() {
        // option name no args with whitespace
        assertParseFailure(parser, " o/\nname\n \t  \n",
                String.format(MESSAGE_MISSING_OPTION_ARGS, MESSAGE_MISSING_NAME_ARGS));

        // option child no args
        assertParseFailure(parser, " o/child",
                String.format(MESSAGE_MISSING_OPTION_ARGS, MESSAGE_MISSING_CHILD_ARGS));

        // option address no args
        assertParseFailure(parser, " o/address",
                String.format(MESSAGE_MISSING_OPTION_ARGS, MESSAGE_MISSING_ADDRESS_ARGS));

        // option date no args
        assertParseFailure(parser, " o/date",
                String.format(MESSAGE_MISSING_OPTION_ARGS, MESSAGE_MISSING_DATE_ARGS));

        // option contact no args
        assertParseFailure(parser, " o/contact",
                String.format(MESSAGE_MISSING_OPTION_ARGS, MESSAGE_MISSING_CONTACT_ARGS));



    }

    @Test
    public void parse_invalidOption_returnsFindAppointmentCommand() {
        // words with whitespace before option prefix but valid option
        assertParseFailure(parser, " invalidWord o/name",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));

        // words before option prefix but valid option
        assertParseFailure(parser, " invalidWordo/name",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));

        // no option after option prefix
        assertParseFailure(parser, " o/",
                String.format(MESSAGE_MISSING_OPTION, MESSAGE_MISSING_FIND_APPOINTMENT_OPTION));

        // no option with whitespaces after option prefix
        assertParseFailure(parser, " o/\n \t   ",
                String.format(MESSAGE_MISSING_OPTION, MESSAGE_MISSING_FIND_APPOINTMENT_OPTION));

        // invalid option valid args
        assertParseFailure(parser, " o/invalidOption dummy args",
                String.format(MESSAGE_INVALID_OPTION, MESSAGE_USAGE));

        // invalid option no args
        assertParseFailure(parser, " o/invalidOption",
                String.format(MESSAGE_INVALID_OPTION, MESSAGE_USAGE));

        // invalid option no args with whitespaces
        assertParseFailure(parser, " o/invalidOption \n  \t",
                String.format(MESSAGE_INVALID_OPTION, MESSAGE_USAGE));
    }


}
