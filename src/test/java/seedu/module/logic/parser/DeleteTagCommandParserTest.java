package seedu.module.logic.parser;

import static seedu.module.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.module.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.module.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.module.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Test;

import seedu.module.logic.commands.CommandTestUtil;
import seedu.module.logic.commands.DeleteTagCommand;
import seedu.module.model.tag.Tag;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the NotDoneCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteTagCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteTagCommandParserTest {
    private DeleteTagCommandParser parser = new DeleteTagCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteTagCommand() {
        assertParseSuccess(parser, "1 " + CommandTestUtil.TAG_DESC_LOW, new DeleteTagCommand(INDEX_FIRST_TASK,
                new Tag("priorityLow")));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        //No index
        assertParseFailure(parser, "a tag", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteTagCommand.MESSAGE_USAGE));

        //Negative index
        assertParseFailure(parser, "-1" + CommandTestUtil.TAG_DESC_LOW,
                String.format(ParserUtil.MESSAGE_INVALID_INDEX));

        //Valid index, but no other arguments offered
        assertParseFailure(parser, "1 ",
                String.format(DeleteTagCommand.MESSAGE_NOT_EDITED));

        //Valid index, invalid tag
        assertParseFailure(parser, "1" + CommandTestUtil.INVALID_TAG_DESC,
                String.format(Tag.MESSAGE_CONSTRAINTS));
    }

}

