package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

import java.util.*;

/**
 * Clears the address book if given no arguments, all clears all Persons with a particular tag.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    public static final String MESSAGE_CLEAR_TAG_SUCCESS = "Cleared all persons with any tag: %1$s";
    private Set<Tag> tagsToClear;

    public ClearCommand(Set<Tag> tagsToClear) {
        this.tagsToClear = tagsToClear;
    }

    public ClearCommand() {
        tagsToClear = new HashSet<>();
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (tagsToClear.isEmpty()) {
            model.setAddressBook(new AddressBook());
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            Iterator<Person> personIterator = model.getAddressBook().getPersonList().iterator();
            List<Person> personsToDelete = new ArrayList<>();
            while (personIterator.hasNext()) {
                Person person = personIterator.next();
                Set<Tag> tags = person.getTags();
                if (!Collections.disjoint(tags, tagsToClear)) {
                    personsToDelete.add(person);
                }
            }
            personsToDelete.forEach(model::deletePerson);
            return new CommandResult(String.format(MESSAGE_CLEAR_TAG_SUCCESS, tagsToClear.toString()));
        }
    }
}
