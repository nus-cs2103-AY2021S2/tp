package seedu.address.model.alias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAliases.ADD_COMMAND;
import static seedu.address.testutil.TypicalAliases.INVALID_COMMAND_STRING;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;

/**
 * More specific command testing with arguments and sub commands for {@code isValidCommand} is done through the
 * {@code AddressBookParserTest} and individual command parser tests. This is because {@code isValidCommand} calls
 * {@code isValidCommandToAlias} in the {@code AddressBookParser}.
 */
public class CommandTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Command(null));
    }

    @Test
    public void constructor_invalidCommand_throwsIllegalArgumentException() {
        // empty command
        assertThrows(IllegalArgumentException.class, () -> new Command(""));

        // invalid command
        assertThrows(IllegalArgumentException.class, () -> new Command(INVALID_COMMAND_STRING));
    }

    @Test
    public void isValidCommand() {
        // null name
        assertThrows(NullPointerException.class, () -> Command.isValidCommand(null));

        // valid clear command
        assertTrue(Command.isValidCommand(ClearCommand.COMMAND_WORD));

        // valid exit command
        assertTrue(Command.isValidCommand(ExitCommand.COMMAND_WORD));

        // valid list command
        assertTrue(Command.isValidCommand(ListCommand.COMMAND_WORD));

        // valid help command
        assertTrue(Command.isValidCommand(HelpCommand.COMMAND_WORD));
    }

    @Test
    public void isSameCommand() {
        assertEquals(ADD_COMMAND, new Command(AddCommand.COMMAND_WORD));
    }
}
