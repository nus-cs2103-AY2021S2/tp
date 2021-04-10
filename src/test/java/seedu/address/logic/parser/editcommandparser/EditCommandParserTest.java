package seedu.address.logic.parser.editcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_DEADLINE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_DESCRIPTION_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATETIME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENERAL_EVENT_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENERAL_EVENT_DESCRIPTION_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CS2101;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENERAL_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseCommandFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.editcommand.EditCommand;
import seedu.address.model.module.Title;

public class EditCommandParserTest {
    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_noPrefixSpecified_failure() {
        // with preamble
        String userInput1 = "1";
        assertParseCommandFailure(parser, userInput1,
                                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        String userInput2 = "words";
        assertParseCommandFailure(parser, userInput2,
                                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));

        // without preamble
        String userInput3 = "";
        assertParseCommandFailure(parser, userInput3,
                                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_allPrefixSpecified_failure() {

        // without preamble
        String userInput2 = " " + PREFIX_MODULE + " " + PREFIX_NAME + " " + PREFIX_TAG + " " + PREFIX_GENERAL_EVENT
                            + " " + PREFIX_DATE + " " + PREFIX_ASSIGNMENT + " " + PREFIX_EXAM;
        assertParseCommandFailure(parser, userInput2, String.format(Title.MESSAGE_CONSTRAINTS, "Modules"));
    }

    @Test
    public void parse_moduleCommand_success() {
        String userInput = " 1 " + PREFIX_MODULE + VALID_TITLE_CS2101;
        EditModuleCommandParser expectedParser = new EditModuleCommandParser();
        assertParseCommandSuccess(parser, userInput, expectedParser);
    }

    @Test
    public void parse_examCommand_success() {
        String userInput = " " + PREFIX_MODULE + VALID_TITLE_CS2101 + " " + PREFIX_EXAM + "1 "
                            + PREFIX_DATE + VALID_EXAM_DATETIME_1;
        EditExamCommandParser expectedParser = new EditExamCommandParser();
        assertParseCommandSuccess(parser, userInput, expectedParser);
    }

    @Test
    public void parse_assignmentCommand_success() {
        // parse deadline
        String userInput1 = " " + PREFIX_MODULE + VALID_TITLE_CS2101 + " " + PREFIX_ASSIGNMENT + "1 "
                            + PREFIX_DEADLINE + VALID_ASSIGNMENT_DEADLINE_1;
        EditAssignmentCommandParser expectedParser1 = new EditAssignmentCommandParser();
        assertParseCommandSuccess(parser, userInput1, expectedParser1);

        // parse description
        String userInput2 = " " + PREFIX_MODULE + VALID_TITLE_CS2101 + " " + PREFIX_ASSIGNMENT + "1 "
                            + PREFIX_DESCRIPTION + VALID_ASSIGNMENT_DESCRIPTION_1;
        EditAssignmentCommandParser expectedParser2 = new EditAssignmentCommandParser();
        assertParseCommandSuccess(parser, userInput2, expectedParser2);

        // parse both
        String userInput3 = " " + PREFIX_MODULE + VALID_TITLE_CS2101 + " " + PREFIX_ASSIGNMENT + "1 "
                            + PREFIX_DESCRIPTION + VALID_ASSIGNMENT_DESCRIPTION_1 + " "
                            + PREFIX_DEADLINE + VALID_ASSIGNMENT_DEADLINE_1;
        EditAssignmentCommandParser expectedParser3 = new EditAssignmentCommandParser();
        assertParseCommandSuccess(parser, userInput3, expectedParser3);
    }

    @Test
    public void parse_generalEventCommand_success() {
        // parse deadline
        String userInput1 = " 1 " + PREFIX_DATE + VALID_GENERAL_EVENT_DATE_1;
        EditEventCommandParser expectedParser1 = new EditEventCommandParser();
        assertParseCommandSuccess(parser, userInput1, expectedParser1);

        // parse description
        String userInput2 = " 1 " + PREFIX_GENERAL_EVENT + VALID_GENERAL_EVENT_DESCRIPTION_1;
        EditEventCommandParser expectedParser2 = new EditEventCommandParser();
        assertParseCommandSuccess(parser, userInput2, expectedParser2);

        // parse both
        String userInput3 = " 1 " + PREFIX_GENERAL_EVENT + VALID_GENERAL_EVENT_DESCRIPTION_1 + " "
                            + PREFIX_DATE + VALID_GENERAL_EVENT_DATE_1;
        EditEventCommandParser expectedParser3 = new EditEventCommandParser();
        assertParseCommandSuccess(parser, userInput3, expectedParser3);
    }

    @Test
    public void parse_personCommand_success() {
        // parse name
        String userInput1 = " 1 " + PREFIX_NAME + VALID_NAME_AMY;
        EditPersonCommandParser expectedParser1 = new EditPersonCommandParser();
        assertParseCommandSuccess(parser, userInput1, expectedParser1);

        // parse birthday
        String userInput2 = " 1 " + PREFIX_NAME + VALID_NAME_AMY + " " + PREFIX_BIRTHDAY + VALID_BIRTHDAY_AMY;
        EditPersonCommandParser expectedParser2 = new EditPersonCommandParser();
        assertParseCommandSuccess(parser, userInput2, expectedParser2);

        // parse tags
        String userInput3 = " 1 " + PREFIX_NAME + VALID_NAME_AMY + " " + PREFIX_TAG + VALID_TAG_FRIEND;
        EditPersonCommandParser expectedParser3 = new EditPersonCommandParser();
        assertParseCommandSuccess(parser, userInput3, expectedParser3);

        // parse all
        String userInput4 = " 1 " + PREFIX_NAME + VALID_NAME_AMY + " " + PREFIX_BIRTHDAY + VALID_BIRTHDAY_AMY
                            + " " + PREFIX_TAG + VALID_TAG_FRIEND;
        EditPersonCommandParser expectedParser4 = new EditPersonCommandParser();
        assertParseCommandSuccess(parser, userInput4, expectedParser4);
    }
}
