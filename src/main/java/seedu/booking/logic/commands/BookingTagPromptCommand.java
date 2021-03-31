package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.Tag;

public class BookingTagPromptCommand extends Command {

    private final Set<Tag> tagSet;

    public BookingTagPromptCommand(Set<Tag> tagSet) {
        this.tagSet = tagSet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ModelManager.processStateInput(tagSet);
        ModelManager.setNextState();
        return new CommandResult(ModelManager.getNextPromptMessage());
    }
}
