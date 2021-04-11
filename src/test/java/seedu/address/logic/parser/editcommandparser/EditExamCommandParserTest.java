package seedu.address.logic.parser.editcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENERAL_EVENT_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATETIME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CS2101;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX_1;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX_2;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.LocalDateTimeUtil;
import seedu.address.logic.commands.editcommand.EditExamCommand;
import seedu.address.model.module.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;

public class EditExamCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditExamCommand.MESSAGE_USAGE);

    private EditExamCommandParser parser = new EditExamCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no module specified
        String userInput1 = " " + PREFIX_MODULE + " " + PREFIX_EXAM.getPrefix()
                            + "1 " + PREFIX_DATE + VALID_EXAM_DATETIME_1;
        assertParseFailure(parser, userInput1, String.format(Title.MESSAGE_CONSTRAINTS, "Modules"));

        // no exam index specified
        String userInput2 = " " + PREFIX_MODULE + VALID_TITLE_CS2101 + " " + PREFIX_EXAM.getPrefix() + " "
                            + PREFIX_DATE + VALID_EXAM_DATETIME_1;
        assertParseFailure(parser, userInput2,
                MESSAGE_INVALID_INDEX_1 + "e/" + MESSAGE_INVALID_INDEX_2);

        // no date specified
        String userInput3 = " " + PREFIX_MODULE + VALID_TITLE_CS2101 + " "
                            + PREFIX_EXAM.getPrefix() + "1 " + PREFIX_DATE;
        assertParseFailure(parser, userInput3, Exam.MESSAGE_CONSTRAINTS);

        // no field specified
        String userInput4 = "";
        assertParseFailure(parser, userInput4,
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditExamCommand.MESSAGE_USAGE));

        // all fields empty
        String userInput5 = " " + PREFIX_MODULE.getPrefix() + " " + PREFIX_EXAM + " " + PREFIX_DATE;
        assertParseFailure(parser, userInput5, String.format(Title.MESSAGE_CONSTRAINTS, "Modules"));
    }

    @Test
    public void parse_invalidValue_failure() {
        String userInput1 = " " + PREFIX_MODULE.getPrefix() + INVALID_TITLE + " " + PREFIX_EXAM + "1 "
                            + PREFIX_DATE + VALID_EXAM_DATETIME_1;
        assertParseFailure(parser, userInput1, String.format(Title.MESSAGE_CONSTRAINTS, "Modules")); // invaid title

        String userInput2 = " " + PREFIX_MODULE.getPrefix() + VALID_TITLE_CS2101 + " " + PREFIX_EXAM + "0 "
                + PREFIX_DATE + VALID_EXAM_DATETIME_1;
        assertParseFailure(parser, userInput2,
                MESSAGE_INVALID_INDEX_1 + "e/" + MESSAGE_INVALID_INDEX_2); // invaid exam index

        String userInput3 = " " + PREFIX_MODULE.getPrefix() + VALID_TITLE_CS2101 + " " + PREFIX_EXAM + "1 "
                            + PREFIX_DATE + INVALID_GENERAL_EVENT_DATE_1;
        assertParseFailure(parser, userInput3, Exam.MESSAGE_CONSTRAINTS); // invaid exam date
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = " " + PREFIX_MODULE + VALID_TITLE_CS2101 + " " + PREFIX_EXAM + "1 "
                            + PREFIX_DATE + VALID_EXAM_DATETIME_1;
        Module target = new Module(new Title(VALID_TITLE_CS2101));
        LocalDateTime edit = LocalDateTime.parse(VALID_EXAM_DATETIME_1, LocalDateTimeUtil.DATETIME_FORMATTER);
        EditExamCommand expectedCommand = new EditExamCommand(target, 1, edit);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
