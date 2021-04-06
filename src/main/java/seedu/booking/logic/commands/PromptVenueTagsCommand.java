package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.Tag;
import seedu.booking.model.venue.Venue;

public class PromptVenueTagsCommand extends Command {
    private final Set<Tag> tagSet;

    public PromptVenueTagsCommand(Set<Tag> tagSet) {
        this.tagSet = tagSet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ModelManager.processStateInput(tagSet);
        ModelManager.setStateInactive();
        CommandResult result;
        Venue venue = (Venue) ModelManager.create();
        result = new AddVenueCommand(venue).execute(model);
        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PromptVenueTagsCommand // instanceof handles nulls
                && this.tagSet.equals(((PromptVenueTagsCommand) other).tagSet));
    }
}
