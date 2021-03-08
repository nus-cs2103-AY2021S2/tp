package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Deletes all target tags from the addressbook.
 */
public class DeleteTagCommand extends DeleteCommand {

    public static final String MESSAGE_DELETE_TAGS_SUCCESS = "Deleted Tags: %1$s";

    private final Set<Tag> targetTags;

    public DeleteTagCommand(Set<Tag> targetTags) {
        this.targetTags = targetTags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> personList = model.getPersonListCopy();

        for (Person person : personList) {
            removeTargetTags(model, person);
        }

        System.out.println(targetTags.toString());
        System.out.println(displayTags());
        return new CommandResult(String.format(MESSAGE_DELETE_TAGS_SUCCESS, displayTags()));
    }

    private void removeTargetTags(Model model, Person person) {

        Set<Tag> tags = new HashSet<>(person.getTags());

        boolean isUpdated = false;
        for (Tag t : targetTags) {
            isUpdated = tags.remove(t);
        }

        if (!isUpdated) {
            return;
        }

        Person editedPerson = new Person(person.getName(),
                person.getPhone(),
                person.getEmail(),
                person.getAddress(),
                tags);

        model.setPerson(person, editedPerson);
    }

    private String displayTags() {
        assert targetTags.size() > 0;
        return targetTags.stream()
                .map(t -> t.toString())
                .map(s -> s.substring(1, s.length() - 1))
                .reduce((a, b) -> a + ", " + b)
                .get();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTagCommand // instanceof handles nulls
                && targetTags.equals(((DeleteTagCommand) other).targetTags)); // state check
    }
}
