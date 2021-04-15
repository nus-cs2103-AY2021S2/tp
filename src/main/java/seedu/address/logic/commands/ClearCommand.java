package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.group.GroupHashMap.DEFAULT_GROUP_NAME;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "FriendDex has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        // Delete old images that are tracked by data file
        model.getAddressBook().getPersonList().forEach(Person::deletePicture);

        model.setAddressBook(new AddressBook());
        model.updateUpcomingDates();
        model.updateDetailedPerson(null);
        model.setCurrentGroup(DEFAULT_GROUP_NAME);
        model.updateFilteredPersonList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
