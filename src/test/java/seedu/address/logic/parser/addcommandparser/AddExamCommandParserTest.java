package seedu.address.logic.parser.addcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATETIME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CS2103;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalRemindMe.VALID_EXAM;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.addcommand.AddExamCommand;
import seedu.address.model.module.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;
import seedu.address.testutil.typicalmodules.ModuleBuilder;

public class AddExamCommandParserTest {
    private AddExamCommandParser parser = new AddExamCommandParser();
    private Module cs2103 = new ModuleBuilder().withTitle(VALID_TITLE_CS2103).emptyBuild();

    @Test
    public void parse_allFieldPresent_success() {
        String userInput = " " + PREFIX_MODULE + VALID_TITLE_CS2103 + " " + PREFIX_EXAM + VALID_EXAM_DATETIME_1;
        assertParseSuccess(parser, userInput, new AddExamCommand(cs2103, VALID_EXAM));
    }

    @Test
    public void parse_missingValue_failure() {
        //empty module
        String userInput1 = " " + PREFIX_MODULE + " " + PREFIX_EXAM + VALID_EXAM_DATETIME_1;
        assertParseFailure(parser, userInput1, String.format(Title.MESSAGE_CONSTRAINTS, "Modules"));

        //empty exam date
        String userInput2 = " " + PREFIX_MODULE + VALID_TITLE_CS2103 + " " + PREFIX_EXAM;
        assertParseFailure(parser, userInput2, Exam.MESSAGE_CONSTRAINTS);

        //blank empty date
        String userInput3 = " " + PREFIX_MODULE + VALID_TITLE_CS2103 + " " + PREFIX_EXAM + "   ";
        assertParseFailure(parser, userInput3, Exam.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_wrongValue_failure() {
        //wrong values for module
        String userInput1 = " " + PREFIX_MODULE + "/" + VALID_TITLE_CS2103 + " " + PREFIX_EXAM + VALID_EXAM_DATETIME_1;
        assertParseFailure(parser, userInput1, String.format(Title.MESSAGE_CONSTRAINTS, "Modules"));

        String userInput2 = " " + PREFIX_MODULE + VALID_TITLE_CS2103 + "?" + " " + PREFIX_EXAM + VALID_EXAM_DATETIME_1;
        assertParseFailure(parser, userInput2, String.format(Title.MESSAGE_CONSTRAINTS, "Modules"));

        String userInput3 = " " + PREFIX_MODULE + "MODULE_1" + " " + PREFIX_EXAM + VALID_EXAM_DATETIME_1;
        assertParseFailure(parser, userInput3, String.format(Title.MESSAGE_CONSTRAINTS, "Modules"));

        //wrong values for date
        String userInput4 = " " + PREFIX_MODULE + VALID_TITLE_CS2103 + " " + PREFIX_EXAM + "nani";
        assertParseFailure(parser, userInput4, Exam.MESSAGE_CONSTRAINTS);

        String userInput5 = " " + PREFIX_MODULE + VALID_TITLE_CS2103 + " " + PREFIX_EXAM + "01/01/1998";
        assertParseFailure(parser, userInput5, Exam.MESSAGE_CONSTRAINTS);

        String userInput6 = " " + PREFIX_MODULE + VALID_TITLE_CS2103 + " " + PREFIX_EXAM + "1200";
        assertParseFailure(parser, userInput6, Exam.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_missingPrefix_failure() {
        //missing module prefix
        String userInput1 = " " + VALID_TITLE_CS2103 + " " + PREFIX_EXAM + VALID_EXAM_DATETIME_1;
        assertParseFailure(parser, userInput1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExamCommand.MESSAGE_USAGE));

        //missing exam prefix
        String userInput2 = " " + PREFIX_MODULE + VALID_TITLE_CS2103 + " " + VALID_EXAM_DATETIME_1;
        assertParseFailure(parser, userInput2,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExamCommand.MESSAGE_USAGE));
    }
}
