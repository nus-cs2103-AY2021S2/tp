package seedu.address.logic.parser.addcommandparser;

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
    public void parse_missingField_failure() {
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
}
