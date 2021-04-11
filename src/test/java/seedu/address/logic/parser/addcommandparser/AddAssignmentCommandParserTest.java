package seedu.address.logic.parser.addcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_DEADLINE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_DESCRIPTION_1;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalRemindMe.MOD_2;
import static seedu.address.testutil.TypicalRemindMe.VALID_ASSIGNMENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.addcommand.AddAssignmentCommand;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Title;

public class AddAssignmentCommandParserTest {

    private AddAssignmentCommandParser parser = new AddAssignmentCommandParser();

    @Test
    public void parse_allFieldPresent_success() {
        String userInput = " " + PREFIX_MODULE + "MOD 2" + " " + PREFIX_ASSIGNMENT + VALID_ASSIGNMENT_DESCRIPTION_1
                + " " + PREFIX_DEADLINE + VALID_ASSIGNMENT_DEADLINE_2;
        assertParseSuccess(parser, userInput, new AddAssignmentCommand(MOD_2, VALID_ASSIGNMENT));
    }

    @Test
    public void parse_missingValue_failure() {
        //empty module
        String userInput1 = " " + PREFIX_MODULE + " " + PREFIX_ASSIGNMENT + VALID_ASSIGNMENT_DESCRIPTION_1 + " "
                + PREFIX_DEADLINE + VALID_ASSIGNMENT_DEADLINE_2;
        assertParseFailure(parser, userInput1, String.format(Title.MESSAGE_CONSTRAINTS, "Modules"));

        //empty assignment description
        String userInput2 = " " + PREFIX_MODULE + "MOD 2" + " " + PREFIX_ASSIGNMENT + " "
                + PREFIX_DEADLINE + VALID_ASSIGNMENT_DEADLINE_2;
        assertParseFailure(parser, userInput2, Assignment.DESCRIPTION_CONSTRAINTS);

        //empty deadline
        String userInput3 = " " + PREFIX_MODULE + "MOD 2" + " " + PREFIX_ASSIGNMENT + VALID_ASSIGNMENT_DESCRIPTION_1
                + " " + PREFIX_DEADLINE;
        assertParseFailure(parser, userInput3, Assignment.DATE_CONSTRAINTS);

        //blank deadline
        String userInput4 = " " + PREFIX_MODULE + "MOD 2" + " " + PREFIX_ASSIGNMENT + VALID_ASSIGNMENT_DESCRIPTION_1
                + " " + PREFIX_DEADLINE + "    ";
        assertParseFailure(parser, userInput4, Assignment.DATE_CONSTRAINTS);
    }

    @Test
    public void parse_wrongValue_failure() {
        //wrong module values
        String userInput1 = " " + PREFIX_MODULE + "?MOD 2" + " " + PREFIX_ASSIGNMENT
                + VALID_ASSIGNMENT_DESCRIPTION_1 + " " + PREFIX_DEADLINE + VALID_ASSIGNMENT_DEADLINE_2;
        assertParseFailure(parser, userInput1, String.format(Title.MESSAGE_CONSTRAINTS, "Modules"));

        String userInput2 = " " + PREFIX_MODULE + "MOD 2/" + " " + PREFIX_ASSIGNMENT
                + VALID_ASSIGNMENT_DESCRIPTION_1 + " " + PREFIX_DEADLINE + VALID_ASSIGNMENT_DEADLINE_2;
        assertParseFailure(parser, userInput2, String.format(Title.MESSAGE_CONSTRAINTS, "Modules"));

        String userInput3 = " " + PREFIX_MODULE + "MOD_2" + " " + PREFIX_ASSIGNMENT
                + VALID_ASSIGNMENT_DESCRIPTION_1 + " " + PREFIX_DEADLINE + VALID_ASSIGNMENT_DEADLINE_2;
        assertParseFailure(parser, userInput3, String.format(Title.MESSAGE_CONSTRAINTS, "Modules"));

        //wrong deadline values
        String userInput4 = " " + PREFIX_MODULE + "MOD 2" + " " + PREFIX_ASSIGNMENT + VALID_ASSIGNMENT_DESCRIPTION_1
                + " " + PREFIX_DEADLINE + "hi";
        assertParseFailure(parser, userInput4, Assignment.DATE_CONSTRAINTS);

        String userInput5 = " " + PREFIX_MODULE + "MOD 2" + " " + PREFIX_ASSIGNMENT + VALID_ASSIGNMENT_DESCRIPTION_1
                + " " + PREFIX_DEADLINE + "01/01/1998";
        assertParseFailure(parser, userInput5, Assignment.DATE_CONSTRAINTS);

        String userInput6 = " " + PREFIX_MODULE + "MOD 2" + " " + PREFIX_ASSIGNMENT + VALID_ASSIGNMENT_DESCRIPTION_1
                + " " + PREFIX_DEADLINE + "1200";
        assertParseFailure(parser, userInput6, Assignment.DATE_CONSTRAINTS);
    }

    @Test
    public void parse_missingPrefix_failure() {
        //missing module prefix
        String userInput1 = " " + "MOD 2" + " " + PREFIX_ASSIGNMENT + VALID_ASSIGNMENT_DESCRIPTION_1 + " "
                + PREFIX_DEADLINE + VALID_ASSIGNMENT_DEADLINE_2;
        assertParseFailure(parser, userInput1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssignmentCommand.MESSAGE_USAGE));

        //missing assignment prefix
        String userInput2 = " " + PREFIX_MODULE + "MOD 2" + " " + VALID_ASSIGNMENT_DESCRIPTION_1 + " "
                + PREFIX_DEADLINE + VALID_ASSIGNMENT_DEADLINE_2;
        assertParseFailure(parser, userInput2,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssignmentCommand.MESSAGE_USAGE));

        //missing deadline prefix
        String userInput3 = " " + PREFIX_MODULE + "MOD 2" + " " + PREFIX_ASSIGNMENT + VALID_ASSIGNMENT_DESCRIPTION_1
                + " " + VALID_ASSIGNMENT_DEADLINE_2;
        assertParseFailure(parser, userInput3,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssignmentCommand.MESSAGE_USAGE));
    }
}
