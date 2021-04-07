package seedu.address.logic.parser.editcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENERAL_EVENT_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_DEADLINE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_DESCRIPTION_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CS2101;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.LocalDateTimeUtil;
import seedu.address.logic.commands.editcommand.EditAssignmentCommand;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Description;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;

public class EditAssignmentCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAssignmentCommand.MESSAGE_USAGE);

    private EditAssignmentCommandParser parser = new EditAssignmentCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no module specified
        String userInput1 = " " + PREFIX_MODULE + " " + PREFIX_ASSIGNMENT.getPrefix()
                            + "1 " + PREFIX_DEADLINE + VALID_ASSIGNMENT_DEADLINE_1;
        assertParseFailure(parser, userInput1, String.format(Title.MESSAGE_CONSTRAINTS, "Modules"));
        String userInput2 = " " + PREFIX_MODULE + " " + PREFIX_ASSIGNMENT.getPrefix()
                            + "1 " + PREFIX_DESCRIPTION + VALID_ASSIGNMENT_DESCRIPTION_1;
        assertParseFailure(parser, userInput2, String.format(Title.MESSAGE_CONSTRAINTS, "Modules"));

        // no exam specified
        String userInput3 = " " + PREFIX_MODULE + VALID_TITLE_CS2101 + " " + PREFIX_ASSIGNMENT.getPrefix() + " "
                            + PREFIX_DEADLINE + VALID_ASSIGNMENT_DEADLINE_1;
        assertParseFailure(parser, userInput3, MESSAGE_INVALID_INDEX);
        String userInput4 = " " + PREFIX_MODULE + VALID_TITLE_CS2101 + " " + PREFIX_ASSIGNMENT.getPrefix() + " "
                            + PREFIX_DESCRIPTION + VALID_ASSIGNMENT_DESCRIPTION_1;
        assertParseFailure(parser, userInput4, MESSAGE_INVALID_INDEX);

        // no edit specified
        String userInput5 = " " + PREFIX_MODULE + VALID_TITLE_CS2101 + " "
                            + PREFIX_ASSIGNMENT.getPrefix() + "1 " + PREFIX_DEADLINE;
        assertParseFailure(parser, userInput5, Assignment.DATE_CONSTRAINTS);
        String userInput6 = " " + PREFIX_MODULE + VALID_TITLE_CS2101 + " "
                            + PREFIX_ASSIGNMENT.getPrefix() + "1 " + PREFIX_DESCRIPTION;
        assertParseFailure(parser, userInput6, Description.MESSAGE_CONSTRAINTS);

        // no field specified
        String userInput7 = "";
        assertParseFailure(parser, userInput7,
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAssignmentCommand.MESSAGE_USAGE));

        // all fields empty
        String userInput8 = " " + PREFIX_MODULE.getPrefix() + " " + PREFIX_ASSIGNMENT + " " + PREFIX_DEADLINE;
        assertParseFailure(parser, userInput8, String.format(Title.MESSAGE_CONSTRAINTS, "Modules"));

        String userInput9 = " " + PREFIX_MODULE.getPrefix() + " " + PREFIX_ASSIGNMENT + " " + PREFIX_DESCRIPTION;
        assertParseFailure(parser, userInput9, String.format(Title.MESSAGE_CONSTRAINTS, "Modules"));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid module title
        String userInput1 = " " + PREFIX_MODULE.getPrefix() + INVALID_TITLE + " " + PREFIX_ASSIGNMENT + "1 "
                            + PREFIX_DEADLINE + VALID_ASSIGNMENT_DEADLINE_1;
        assertParseFailure(parser, userInput1, String.format(Title.MESSAGE_CONSTRAINTS, "Modules")); // invaid title
        String userInput2 = " " + PREFIX_MODULE.getPrefix() + INVALID_TITLE + " " + PREFIX_ASSIGNMENT + "1 "
                            + PREFIX_DESCRIPTION + VALID_ASSIGNMENT_DESCRIPTION_1;
        assertParseFailure(parser, userInput2, String.format(Title.MESSAGE_CONSTRAINTS, "Modules")); // invaid title

        // invalid assignment index
        String userInput3 = " " + PREFIX_MODULE.getPrefix() + VALID_TITLE_CS2101 + " " + PREFIX_ASSIGNMENT + "0 "
                            + PREFIX_DEADLINE + VALID_ASSIGNMENT_DEADLINE_1;
        assertParseFailure(parser, userInput3, MESSAGE_INVALID_INDEX); // invaid exam index
        String userInput4 = " " + PREFIX_MODULE.getPrefix() + VALID_TITLE_CS2101 + " " + PREFIX_ASSIGNMENT + "0 "
                            + PREFIX_DESCRIPTION + VALID_ASSIGNMENT_DESCRIPTION_1;
        assertParseFailure(parser, userInput4, MESSAGE_INVALID_INDEX); // invaid exam index

        // invalid edit
        String userInput5 = " " + PREFIX_MODULE.getPrefix() + VALID_TITLE_CS2101 + " " + PREFIX_ASSIGNMENT + "1 "
                            + PREFIX_DEADLINE + INVALID_GENERAL_EVENT_DATE_1;
        assertParseFailure(parser, userInput5, Assignment.DATE_CONSTRAINTS); // invaid exam date
    }

    @Test
    public void parse_editOneField_success() {
        // valid date edit
        String userInput1 = " " + PREFIX_MODULE + VALID_TITLE_CS2101 + " " + PREFIX_ASSIGNMENT + "1 "
                            + PREFIX_DEADLINE + VALID_ASSIGNMENT_DEADLINE_1;
        Module target = new Module(new Title(VALID_TITLE_CS2101));
        LocalDateTime dateEdit = LocalDateTime.parse(VALID_ASSIGNMENT_DEADLINE_1, LocalDateTimeUtil.DATETIME_FORMATTER);
        EditAssignmentCommand expectedCommand1 = new EditAssignmentCommand(target, 1, null, dateEdit);
        assertParseSuccess(parser, userInput1, expectedCommand1);

        // valid description edit
        String userInput2 = " " + PREFIX_MODULE + VALID_TITLE_CS2101 + " " + PREFIX_ASSIGNMENT + "1 "
                            + PREFIX_DESCRIPTION + VALID_ASSIGNMENT_DESCRIPTION_1;
        Description descriptionEdit = new Description(VALID_ASSIGNMENT_DESCRIPTION_1);
        EditAssignmentCommand expectedCommand2 = new EditAssignmentCommand(target, 1, descriptionEdit, null);
        assertParseSuccess(parser, userInput2, expectedCommand2);
    }

    @Test
    public void parse_editBothFields_success() {
        String userInput = " " + PREFIX_MODULE + VALID_TITLE_CS2101 + " " + PREFIX_ASSIGNMENT + "1 "
                + PREFIX_DESCRIPTION + VALID_ASSIGNMENT_DESCRIPTION_1 + " "
                + PREFIX_DEADLINE + VALID_ASSIGNMENT_DEADLINE_1;
        Module target = new Module(new Title(VALID_TITLE_CS2101));
        LocalDateTime dateEdit = LocalDateTime.parse(VALID_ASSIGNMENT_DEADLINE_1, LocalDateTimeUtil.DATETIME_FORMATTER);
        Description descriptionEdit = new Description(VALID_ASSIGNMENT_DESCRIPTION_1);
        EditAssignmentCommand expectedCommand = new EditAssignmentCommand(target, 1, descriptionEdit, dateEdit);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
