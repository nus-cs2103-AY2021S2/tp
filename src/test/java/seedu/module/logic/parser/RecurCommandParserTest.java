package seedu.module.logic.parser;

import static seedu.module.logic.commands.CommandTestUtil.INVALID_RECURRENCE_DESC;
import static seedu.module.logic.commands.CommandTestUtil.VALID_RECURRENCE_LAB;
import static seedu.module.logic.parser.CliSyntax.PREFIX_RECURRENCE;
import static seedu.module.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.module.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.module.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.module.testutil.TypicalTasks.getTypicalModuleBook;

import org.junit.jupiter.api.Test;

import seedu.module.commons.core.index.Index;
import seedu.module.commons.core.optionalfield.OptionalField;
import seedu.module.logic.commands.RecurCommand;
import seedu.module.model.Model;
import seedu.module.model.ModelManager;
import seedu.module.model.UserPrefs;
import seedu.module.model.task.Recurrence;

public class RecurCommandParserTest {
    private Model model = new ModelManager(getTypicalModuleBook(), new UserPrefs());
    private RecurCommandParser parser = new RecurCommandParser();

    @Test
    public void parse_invalidIndex_throwsParseException() {
        String invalidIndexString = "-1";
        String userInput = invalidIndexString + " " + PREFIX_RECURRENCE + VALID_RECURRENCE_LAB;
        assertParseFailure(parser, userInput, ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_validUserInput_returnsValidRecurCommand() {
        Index index = INDEX_FIRST_TASK;
        String indexString = String.valueOf(index.getOneBased());
        String userInput = indexString + " " + PREFIX_RECURRENCE + VALID_RECURRENCE_LAB;
        RecurCommand expectedCommand = new RecurCommand(INDEX_FIRST_TASK);
        expectedCommand.setRecurrence(new OptionalField<>(new Recurrence(VALID_RECURRENCE_LAB)));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidRecurrenceString_throwsParseException() {
        Index index = INDEX_FIRST_TASK;
        String indexString = String.valueOf(index.getOneBased());
        String userInput = indexString + INVALID_RECURRENCE_DESC;
        RecurCommand expectedCommand = new RecurCommand(INDEX_FIRST_TASK);
        expectedCommand.setRecurrence(new OptionalField<>(new Recurrence(VALID_RECURRENCE_LAB)));

        assertParseFailure(parser, userInput, Recurrence.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_emptyUserInput_throwsParseException() {
        //EP: null
        assertParseFailure(parser, "", parser.RECUR_EXCEPTION_MESSAGE);
    }
}
