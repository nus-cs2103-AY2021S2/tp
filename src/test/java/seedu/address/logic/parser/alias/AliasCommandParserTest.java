package seedu.address.logic.parser.alias;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ROOM_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.YEAR_DESC_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Alias;
import seedu.address.logic.commands.alias.AliasCommand;

public class AliasCommandParserTest {
    private final AliasCommandParser parser = new AliasCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // valid alias name and basic command without parameters
        assertParseSuccess(parser,
                String.format(" %sh %shelp", PREFIX_ALIAS, PREFIX_COMMAND),
                new AliasCommand(new Alias("h", "help")));
        // valid alias name and basic command with trailing parameters
        assertParseSuccess(parser,
                String.format(" %sh %shelp a/parama b/paramb", PREFIX_ALIAS, PREFIX_COMMAND),
                new AliasCommand(new Alias("h", "help a/parama b/paramb")));
        // valid alias name and command that requires extra parameters
        assertParseSuccess(parser, String.format(" %saddbob %sradd "
                        + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + YEAR_DESC_BOB + ROOM_DESC_BOB,
                PREFIX_ALIAS, PREFIX_COMMAND),
                new AliasCommand(new Alias("addbob", "radd "
                        + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + YEAR_DESC_BOB + ROOM_DESC_BOB)));

    }

    @Test
    public void parse_compulsoryFieldEmpty_failure() {
        // missing alias name and command
        assertParseFailure(parser,
                String.format(" %s %s", PREFIX_ALIAS, PREFIX_COMMAND),
                Alias.MESSAGE_NAME_CONSTRAINTS);

        // missing command
        assertParseFailure(parser,
                String.format(" %sname %s", PREFIX_ALIAS, PREFIX_COMMAND),
                Alias.MESSAGE_COMMAND_CONSTRAINTS);

        // missing alias name
        assertParseFailure(parser,
                String.format(" %s %scommand", PREFIX_ALIAS, PREFIX_COMMAND),
                Alias.MESSAGE_NAME_CONSTRAINTS);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE);

        // missing alias name and command
        assertParseFailure(parser,
                String.format(" "),
                expectedMessage);

        // missing command
        assertParseFailure(parser,
                String.format(" %sname", PREFIX_ALIAS),
                expectedMessage);

        // missing alias name
        assertParseFailure(parser,
                String.format(" %scommand", PREFIX_COMMAND),
                expectedMessage);
    }

    @Test
    public void parse_invalidAliasName_failure() {
        // empty alias name
        assertParseFailure(parser,
                String.format(" %s %scommand", PREFIX_ALIAS, PREFIX_COMMAND),
                Alias.MESSAGE_NAME_CONSTRAINTS);

        // contains space
        assertParseFailure(parser,
                String.format(" %sna me %scommand", PREFIX_ALIAS, PREFIX_COMMAND),
                Alias.MESSAGE_NAME_CONSTRAINTS);

        // contains special character
        assertParseFailure(parser,
                String.format(" %sn@me %scommand", PREFIX_ALIAS, PREFIX_COMMAND),
                Alias.MESSAGE_NAME_CONSTRAINTS);

        // contains space and special character
        assertParseFailure(parser,
                String.format(" %sn@ me %scommand", PREFIX_ALIAS, PREFIX_COMMAND),
                Alias.MESSAGE_NAME_CONSTRAINTS);
    }

    @Test
    public void parse_invalidCommand_failure() {
        assertParseFailure(parser,
                String.format(" %sname %s", PREFIX_ALIAS, PREFIX_COMMAND),
                Alias.MESSAGE_COMMAND_CONSTRAINTS);
    }
}
