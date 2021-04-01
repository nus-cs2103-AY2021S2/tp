package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.PROMPT_NAME_MESSAGE;
import static seedu.booking.logic.commands.states.AddPersonCommandState.STATE_NAME;

import seedu.booking.logic.commands.states.AddPersonCommandState;
import seedu.booking.logic.commands.states.CommandState;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;

public class PromptAddPersonCommand extends Command {

    public static final String COMMAND_WORD = "add_person_n";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Starts the multi-step process to add person.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        CommandState commandState = new AddPersonCommandState();
        ModelManager.setCommandState(commandState);
        ModelManager.setStateActive();
        ModelManager.setState(STATE_NAME);
        return new CommandResult(PROMPT_NAME_MESSAGE);
    }
}


