package seedu.module.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.module.model.Model;
import seedu.module.model.ModuleBook;
import seedu.module.model.ModuleManager;

/**
 * Clears the module book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Module book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setModuleBook(new ModuleBook());
        ModuleManager.rebuildMapping();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
