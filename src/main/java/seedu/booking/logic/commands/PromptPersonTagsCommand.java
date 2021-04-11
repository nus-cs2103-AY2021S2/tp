package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.booking.logic.StatefulLogicManager;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.Tag;
import seedu.booking.model.person.Person;

public class PromptPersonTagsCommand extends Command {

    private final Set<Tag> tagSet;

    public PromptPersonTagsCommand(Set<Tag> tagSet) {
        this.tagSet = tagSet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        StatefulLogicManager.processStateInput(tagSet);
        CommandResult result;
        Person person = (Person) StatefulLogicManager.create();
        result = new AddPersonCommand(person).execute(model);
        StatefulLogicManager.setStateInactive();
        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PromptPersonTagsCommand // instanceof handles nulls
                && this.tagSet.equals(((PromptPersonTagsCommand) other).tagSet));
    }
}
