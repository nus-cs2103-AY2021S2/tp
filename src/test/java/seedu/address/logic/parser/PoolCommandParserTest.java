package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COMMUTER_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.COMMUTER_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMMUTER;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMMUTER_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalDrivers.AMY;
import static seedu.address.testutil.TypicalDrivers.BOB;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PoolCommand;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.driver.Driver;
import seedu.address.testutil.CommuterBuilder;
import seedu.address.testutil.DriverBuilder;

class PoolCommandParserTest {
    private PoolCommandParser parser = new PoolCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Driver expectedDriver = new DriverBuilder(BOB).build();
        Set<Index> expectedCommuters = new CommuterBuilder().build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB
                        + COMMUTER_DESC_1 + COMMUTER_DESC_2,
                new PoolCommand(expectedDriver, expectedCommuters));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB
                        + COMMUTER_DESC_1 + COMMUTER_DESC_2,
                new PoolCommand(expectedDriver, expectedCommuters));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB
                        + COMMUTER_DESC_1 + COMMUTER_DESC_2,
                new PoolCommand(expectedDriver, expectedCommuters));

        // commuters out of order
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB
                        + COMMUTER_DESC_2 + COMMUTER_DESC_1,
                new PoolCommand(expectedDriver, expectedCommuters));

        // duplicated commuters
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB
                        + COMMUTER_DESC_1 + COMMUTER_DESC_1 + COMMUTER_DESC_2,
                new PoolCommand(expectedDriver, expectedCommuters));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Driver expectedDriver = new DriverBuilder(AMY).build();
        Set<Index> expectedCommuters = new CommuterBuilder().withIndices(new int[]{1}).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + COMMUTER_DESC_1,
                new PoolCommand(expectedDriver, expectedCommuters));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, PoolCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB
                + COMMUTER_DESC_1 + COMMUTER_DESC_2, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB
                + COMMUTER_DESC_1 + COMMUTER_DESC_2, expectedMessage);

        // missing commuter prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB
                + VALID_COMMUTER_1 + COMMUTER_DESC_2, expectedMessage);


        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB
                + VALID_COMMUTER_1 + COMMUTER_DESC_2, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB
                + COMMUTER_DESC_1 + COMMUTER_DESC_2, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC
                + COMMUTER_DESC_1 + COMMUTER_DESC_2, Phone.MESSAGE_CONSTRAINTS);

        // invalid commuter
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + INVALID_COMMUTER + COMMUTER_DESC_2, ParserUtil.MESSAGE_INVALID_INDEX);

        // two invalid values, only first invalid value reported (Name)
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB
                + INVALID_COMMUTER + COMMUTER_DESC_2, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB
                        + INVALID_COMMUTER + COMMUTER_DESC_2,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, PoolCommand.MESSAGE_USAGE));
    }

}
