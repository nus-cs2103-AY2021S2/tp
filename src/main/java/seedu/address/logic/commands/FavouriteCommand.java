package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.ViewTutorPredicate;

import java.util.List;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class FavouriteCommand extends Command {

    public static final String COMMAND_WORD = "favourite";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " ID";

    public static final String MESSAGE_SUCCESS = "Favourite tutor: %s";

    public static final String MESSAGE_INVALID_INDEX = "Invalid index %d";

    public static final String MESSAGE_AlREADY_FAVOURITE = "Tutor is already a favourite";

    private final Index targetIndex;

    public FavouriteCommand(Index targetIndex) {
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
        if (person.isFavourite()) {
            throw new CommandException(MESSAGE_AlREADY_FAVOURITE);
        } else {
            person.setFavourite(true);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, person.getName()));
    }
}
