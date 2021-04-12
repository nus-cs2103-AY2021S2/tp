/*package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;
import static seedu.address.logic.commands.NoteCommand.MESSAGE_VIEW_SUCCESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_VIEW;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Note;

public class NoteCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_view_note_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_VIEW_SUCCESS,
                false, false, Optional.empty(),false);
        assertCommandSuccess(new NoteCommand(Index.fromZeroBased(0), PREFIX_NOTE_VIEW, new Note("placeholder")),
                model, expectedCommandResult, expectedModel);
    }
}*/
