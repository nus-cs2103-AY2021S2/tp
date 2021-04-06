package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
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

        ModelManager.processStateInput(tagSet);
        CommandResult result;
        Person person = (Person) ModelManager.create();
        result = new AddPersonCommand(person).execute(model);
        ModelManager.setStateInactive();
        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PromptPersonTagsCommand // instanceof handles nulls
                && this.tagSet.equals(((PromptPersonTagsCommand) other).tagSet));
    }
}
