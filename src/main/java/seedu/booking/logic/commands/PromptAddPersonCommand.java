package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.PROMPT_EMAIL_PERSON_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_EXIT_PROMPT;
import static seedu.booking.logic.commands.CommandShowType.COMMAND_SHOW_PERSONS;
import static seedu.booking.logic.commands.states.AddPersonCommandState.STATE_EMAIL;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.booking.logic.StatefulLogicManager;
import seedu.booking.logic.commands.states.AddPersonCommandState;
import seedu.booking.logic.commands.states.CommandState;
import seedu.booking.model.Model;
import seedu.booking.model.person.Name;

public class PromptAddPersonCommand extends Command {

    public static final String COMMAND_WORD = "add_person";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_NAME + "NAME: Starts the multi-step process to add person.\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "Annabelle Lee";

    private final Name name;

    public PromptAddPersonCommand(Name name) {
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        CommandState commandState = new AddPersonCommandState(name);

        StatefulLogicManager.setCommandState(commandState);
        StatefulLogicManager.setStateActive();
        StatefulLogicManager.setState(STATE_EMAIL);
        return new CommandResult(PROMPT_EMAIL_PERSON_MESSAGE + PROMPT_MESSAGE_EXIT_PROMPT,
                COMMAND_SHOW_PERSONS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PromptAddPersonCommand // instanceof handles nulls
                && name.equals(((PromptAddPersonCommand) other).name));
    }
}


