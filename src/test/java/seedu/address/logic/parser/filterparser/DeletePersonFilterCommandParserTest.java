package seedu.address.logic.parser.filterparser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.filtercommands.DeletePersonFilterCommand;
import seedu.address.model.filter.TutorFilter;
import seedu.address.testutil.TypicalFilters;

/**
 * Integration tests for DeleteTutorFilterCommandParser.
 */
public class DeletePersonFilterCommandParserTest {
    private DeletePersonFilterCommandParser parser = new DeletePersonFilterCommandParser();

    @Test
    public void parse() {
        // EP 1: No subjects
        TutorFilter tutorFilter = TypicalFilters.getBensonNoSubjectsFilter();
        DeletePersonFilterCommand deletePersonFilterCommand = new DeletePersonFilterCommand(tutorFilter);

        assertParseSuccess(
                parser,
                " n/Benson Meier g/Male p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25",
                deletePersonFilterCommand);

        // EP 2: One subject
        tutorFilter = TypicalFilters.getBensonOneSubjectFilter();
        deletePersonFilterCommand = new DeletePersonFilterCommand(tutorFilter);

        assertParseSuccess(
                parser,
                " n/Benson Meier g/Male p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25"
                + " s/English l/Secondary 4 r/60 y/4 q/A-Level",
                deletePersonFilterCommand);

        // EP 3: Multiple Subjects
        tutorFilter = TypicalFilters.getBensonTwoSubjectsFilter();
        deletePersonFilterCommand = new DeletePersonFilterCommand(tutorFilter);

        assertParseSuccess(
                parser,
                " n/Benson Meier g/Male p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25"
                + " s/English l/Secondary 4 r/60 y/4 q/A-Level"
                + " s/Geography l/Secondary 4 r/60 y/4 q/A-Level",
                deletePersonFilterCommand);
    }
}
