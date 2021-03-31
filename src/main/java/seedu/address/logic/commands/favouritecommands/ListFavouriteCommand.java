package seedu.address.logic.commands.favouritecommands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutor.Tutor;
import seedu.address.model.tutor.ViewTutorPredicate;

/**
 * List all Tutors who are favourites
 */
public class ListFavouriteCommand extends Command {

    public static final String COMMAND_WORD = "list_favourites";

    public static final String MESSAGE_SUCCESS = "Listed all favourites";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Tutor> favouriteTutorList = new ArrayList<>();
        List<Tutor> tutorList = model.getTutorBook().getTutorList();

        for (Tutor p: tutorList) {
            if (p.isFavourite()) {
                favouriteTutorList.add(p);
            }
        }

        Predicate<Tutor> personPredicate = new ViewTutorPredicate(favouriteTutorList);

        model.updateFilteredTutorList(personPredicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
