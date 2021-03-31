package seedu.address.logic.parser.alias;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.alias.UnaliasCommand;

public class UnaliasCommandParserTest {
    private final UnaliasCommandParser parser = new UnaliasCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // alias name is present and valid
        assertParseSuccess(parser,
                String.format(" %sname", PREFIX_ALIAS),
                new UnaliasCommand("name"));

        // alias name is present and has space
        assertParseSuccess(parser,
                String.format(" %sna me", PREFIX_ALIAS),
                new UnaliasCommand("na me"));

        // alias name is present and has special character
        assertParseSuccess(parser,
                String.format(" %sn@me", PREFIX_ALIAS),
                new UnaliasCommand("n@me"));

        // alias name is present and has space and special character
        assertParseSuccess(parser,
                String.format(" %sn@ me", PREFIX_ALIAS),
                new UnaliasCommand("n@ me"));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnaliasCommand.MESSAGE_USAGE);

        // missing alias name
        assertParseFailure(parser,
                String.format(" "),
                expectedMessage);
        assertParseFailure(parser,
                String.format(" %s", PREFIX_ALIAS),
                expectedMessage);
    }
}
