package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.OPTION_REMOVE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Favourite;

/**
 * Favourites a contact identified using it's displayed index from the address book.
 */
public class FavouriteCommand extends Command {

    public static final String COMMAND_WORD = "fav";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Favourites the contact at the index in the address book.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "[" + PREFIX_OPTION + "OPTION]\n"
            + "Options: remove (to unfavourite)\n"
            + "Example: " + COMMAND_WORD + " 2\n"
            + "Example: " + COMMAND_WORD + " 4 o/" + OPTION_REMOVE;
    public static final String MESSAGE_FAV_IN_PROGRESS = "Favourite command is still being implemented.";
    public static final String MESSAGE_FAVOURITE_CONTACT_SUCCESS = "Favourited Contact: \n%1$s";
    public static final String MESSAGE_UNFAVOURITE_CONTACT_SUCCESS = "Unfavourited Contact: \n%1$s";

    private final Index index;
    private final boolean isFav;

    /**
     * @param index of the contact in the filtered contact list to edit
     * @param isFav whether this FavouriteCommand is to favourite or unfavourite
     */
    public FavouriteCommand(Index index, boolean isFav) {
        this.index = index;
        this.isFav = isFav;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownList = model.getFilteredContactList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Contact contactToFavourite = lastShownList.get(index.getZeroBased());
        EditCommand.EditContactDescriptor editContactDescriptor = new EditCommand.EditContactDescriptor();
        editContactDescriptor.setFavourite(new Favourite(String.valueOf(isFav)));
        Contact favouritedContact = EditCommand.createEditedContact(contactToFavourite, editContactDescriptor);

        model.setContact(contactToFavourite, favouritedContact);
        return new CommandResult(
                String.format(isFav ? MESSAGE_FAVOURITE_CONTACT_SUCCESS : MESSAGE_UNFAVOURITE_CONTACT_SUCCESS,
                        favouritedContact));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FavouriteCommand // instanceof handles nulls
                && Boolean.compare(isFav, ((FavouriteCommand) other).isFav) == 0); // state check
    }
}
