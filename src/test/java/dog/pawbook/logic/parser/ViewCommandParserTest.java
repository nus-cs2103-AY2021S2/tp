//@@ ZhangAnli
package dog.pawbook.logic.parser;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_ID_GENERAL;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_EMPTY_STRING;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_NEGATIVE_ID_STRING;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_UNKNOWN_ID_STRING;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_ENTITY_ID;
import static dog.pawbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static dog.pawbook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static dog.pawbook.testutil.TypicalEntities.getTypicalDatabase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dog.pawbook.logic.commands.ViewCommand;
import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;

public class ViewCommandParserTest {

    private ViewCommandParser parser = new ViewCommandParser();
    private Model model;

    @BeforeEach
    public void setupModel() {
        model = new ModelManager(getTypicalDatabase(), new UserPrefs());
    }

    @Test
    public void parse_emptyArguments_failure() {
        // Test that empty arguments ParseException is thrown
        assertParseFailure(parser, INVALID_EMPTY_STRING, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidId_failure() {
        // Test that negative ID ParseException is thrown
        assertParseFailure(parser, INVALID_NEGATIVE_ID_STRING, MESSAGE_INVALID_ID_GENERAL);

        // Test that invalid ID ParseException is thrown
        assertParseFailure(parser, INVALID_UNKNOWN_ID_STRING, MESSAGE_INVALID_ID_GENERAL);
    }

    @Test
    public void parse_validId_success() {
        // Test that no parseException thrown
        assertParseSuccess(parser, VALID_ENTITY_ID, new ViewCommand(Integer.parseInt(VALID_ENTITY_ID)));
    }
}
