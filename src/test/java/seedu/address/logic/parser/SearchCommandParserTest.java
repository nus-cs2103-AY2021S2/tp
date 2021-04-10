package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_KEYWORD_SUPPLIED;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SearchCommand;
import seedu.address.model.person.predicate.NameSchoolAndSubjectContainsKeywordsPredicate;
import seedu.address.model.subject.Subject;

public class SearchCommandParserTest {

    private SearchCommandParser parser = new SearchCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidSubject_throwsParseException() {
        //first subject name is invalid
        assertParseFailure(parser, " n/Alice Bob s/simei jurong t/abc",
            String.format(MESSAGE_INVALID_KEYWORD_SUPPLIED, Subject.MESSAGE_CONSTRAINTS));

        //second subject name is invalid
        assertParseFailure(parser, " n/Alice Bob s/simei jurong t/bio abc",
            String.format(MESSAGE_INVALID_KEYWORD_SUPPLIED, Subject.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_validArgs_returnsSearchCommand() {
        // no leading and trailing whitespaces
        SearchCommand expectedSearchCommand =
                new SearchCommand(new NameSchoolAndSubjectContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"),
                        Arrays.asList("simei", "jurong"), Arrays.asList(new Subject("bio"),
                        new Subject("math"))));
        assertParseSuccess(parser, " n/Alice Bob s/simei jurong t/bio math", expectedSearchCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "   n/Alice     Bob   s/simei  jurong"
            + " t/bio   math    ", expectedSearchCommand);
    }

}
