package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.ViewTutorPredicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class ListFavouriteCommand extends Command {

    public static final String COMMAND_WORD = "list_favourites";

    public static final String MESSAGE_SUCCESS = "Listed all favourites";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> favouritePersonList = new ArrayList<>();
        List<Person> personList = model.getAddressBook().getPersonList();

        for (Person p: personList) {
            if (p.isFavourite()) {
                favouritePersonList.add(p);
            }
        }

        Predicate<Person> personPredicate = new ViewTutorPredicate(favouritePersonList);

        model.updateFilteredPersonList(personPredicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
