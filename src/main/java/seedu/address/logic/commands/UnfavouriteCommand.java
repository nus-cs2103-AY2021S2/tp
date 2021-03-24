package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class UnfavouriteCommand extends Command {

    public static final String COMMAND_WORD = "unfavourite";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " ID";

    public static final String MESSAGE_SUCCESS = "Unfavourite tutor: %s";

    public static final String MESSAGE_INVALID_INDEX = "Invalid index %d";

    private static final String MESSAGE_AlREADY_UNFAVOURITE = "Tutor is already not a favourite";

    private final Index targetIndex;

    public UnfavouriteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> tutorList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= tutorList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_INDEX, targetIndex.getZeroBased()));
        }

        Person person = tutorList.get(targetIndex.getZeroBased());
        if (!person.isFavourite()) {
            throw new CommandException(MESSAGE_AlREADY_UNFAVOURITE);
        } else {
            person.setFavourite(false);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, person.getName()));
    }
}
