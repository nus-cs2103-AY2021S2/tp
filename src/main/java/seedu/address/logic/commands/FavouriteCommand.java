package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Favourite;
import seedu.address.model.person.Person;

/**
 * Favourites a person identified using it's displayed index from the address book.
 */
public class FavouriteCommand extends Command {

    public static final String COMMAND_WORD = "fav";
    public static final String OPTION_REMOVE_FAV = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Favourites the person at the index in the address book.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "[" + PREFIX_OPTION + "OPTION]\n"
            + "Options: remove (to unfavourite)\n"
            + "Example: " + COMMAND_WORD + " 2\n"
            + "Example: " + COMMAND_WORD + " 4 o/" + OPTION_REMOVE_FAV;
    public static final String MESSAGE_FAV_IN_PROGRESS = "Favourite command is still being implemented.";
    public static final String MESSAGE_FAVOURITE_PERSON_SUCCESS = "Favourited Person: %1$s";
    public static final String MESSAGE_UNFAVOURITE_PERSON_SUCCESS = "Unfavourited Person: %1$s";

    private final Index index;
    private final boolean isFav;

    /**
     * @param index of the person in the filtered person list to edit
     * @param isFav whether this FavouriteCommand is to favourite or unfavourite
     */
    public FavouriteCommand(Index index, boolean isFav) {
        this.index = index;
        this.isFav = isFav;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToFavourite = lastShownList.get(index.getZeroBased());
        EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();
        editPersonDescriptor.setFavourite(new Favourite(String.valueOf(isFav)));
        Person favouritedPerson = EditCommand.createEditedPerson(personToFavourite, editPersonDescriptor);

        model.setPerson(personToFavourite, favouritedPerson);
        return new CommandResult(
                String.format(isFav ? MESSAGE_FAVOURITE_PERSON_SUCCESS : MESSAGE_UNFAVOURITE_PERSON_SUCCESS,
                        favouritedPerson));
    }
}
