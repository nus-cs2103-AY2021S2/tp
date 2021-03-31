package seedu.address.logic.parser.alias;

public class AliasCommandParserTest {
    /*
    private AliasCommandParser parser = new AliasCommandParser();

    @Test
    void parse_allFieldsPresent_success() {
        // valid alias name and basic command
        assertParseSuccess(parser,
                "alias a/h cmd/help",
                new AliasCommand(new Alias("h", "help")));
        // valid alias name and multiple word input

    }

    /*
    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAliasCommand.MESSAGE_USAGE);

        // missing alias name and input
        assertParseFailure(parser,
                String.format(" %s %s", PREFIX_ALIAS_ALIAS_NAME, PREFIX_ALIAS_ALIAS_INPUT),
                expectedMessage);

        // missing input
        assertParseFailure(parser,
                String.format(" %s aliasName %s", PREFIX_ALIAS_ALIAS_NAME, PREFIX_ALIAS_ALIAS_INPUT),
                expectedMessage);

        // missing alias name
        assertParseFailure(parser,
                String.format(" %s %s input here", PREFIX_ALIAS_ALIAS_NAME, PREFIX_ALIAS_ALIAS_INPUT),
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid aliasname
        assertParseFailure(parser,
                String.format(" %s #asd@## %s input here", PREFIX_ALIAS_ALIAS_NAME, PREFIX_ALIAS_ALIAS_INPUT),
                Alias.MESSAGE_NAME_CONSTRAINTS);
        // multiple words which would be individually valid
        assertParseFailure(parser,
                String.format(" %s multiple words %s input here", PREFIX_ALIAS_ALIAS_NAME, PREFIX_ALIAS_ALIAS_INPUT),
                Alias.MESSAGE_NAME_CONSTRAINTS);
    }
    */
}
