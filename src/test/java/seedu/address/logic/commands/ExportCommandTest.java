package seedu.address.logic.commands;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModel.getTypicalModel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

class ExportCommandTest {

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new ExportCommand(null));
    }

    @Test
    void execute_invalidIndex() {
        Model modelStub = getTypicalModel();
        Index index = Index.fromOneBased(50);

        ExportCommand exportCommand = new ExportCommand(index);

        assertThrows(CommandException.class, String.format(ExportCommand.MESSAGE_INVALID_INDEX,
                index.getOneBased()), () -> exportCommand.execute(modelStub));
    }

    @Test
    void execute_success() {
        Model modelStub = getTypicalModel();
        Index index = Index.fromOneBased(1);

        ExportCommand exportCommand = new ExportCommand(index);

        Assertions.assertDoesNotThrow(() -> exportCommand.execute(modelStub));
    }

}
