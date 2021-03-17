package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedCommandAlias.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAliases.ADD_ALIAS_STRING;
import static seedu.address.testutil.TypicalAliases.ADD_COMMAND_ALIAS;
import static seedu.address.testutil.TypicalAliases.INVALID_ALIAS_STRING;
import static seedu.address.testutil.TypicalAliases.INVALID_COMMAND_STRING;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.Command;

public class JsonAdaptedCommandAliasTest {

    @Test
    public void toModelType_validCommandAliasDetails_returnsCommandAlias() throws Exception {
        JsonAdaptedCommandAlias commandAlias = new JsonAdaptedCommandAlias(ADD_COMMAND_ALIAS);
        assertEquals(ADD_COMMAND_ALIAS, commandAlias.toModelType());
    }

    @Test
    public void toModelType_invalidAlias_throwsIllegalValueException() {
        JsonAdaptedCommandAlias commandAlias =
                new JsonAdaptedCommandAlias(INVALID_ALIAS_STRING, AddCommand.COMMAND_WORD);
        String expectedMessage = Alias.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, commandAlias::toModelType);
    }

    @Test
    public void toModelType_nullAlias_throwsIllegalValueException() {
        JsonAdaptedCommandAlias commandAlias = new JsonAdaptedCommandAlias(null, AddCommand.COMMAND_WORD);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Alias.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, commandAlias::toModelType);
    }

    @Test
    public void toModelType_invalidCommand_throwsIllegalValueException() {
        JsonAdaptedCommandAlias commandAlias = new JsonAdaptedCommandAlias(ADD_ALIAS_STRING, INVALID_COMMAND_STRING);
        String expectedMessage = Command.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, commandAlias::toModelType);
    }

    @Test
    public void toModelType_nullCommand_throwsIllegalValueException() {
        JsonAdaptedCommandAlias commandAlias = new JsonAdaptedCommandAlias(ADD_ALIAS_STRING, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Command.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, commandAlias::toModelType);
    }

}
