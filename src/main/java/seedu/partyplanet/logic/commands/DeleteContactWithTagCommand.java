package seedu.partyplanet.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.person.Person;
import seedu.partyplanet.model.tag.Tag;

/**
 * Deletes all persons, that is tagged with the target tags, from PartyPlanet.
 * Provided that they do not have other tags.
 */
public class DeleteContactWithTagCommand extends DeleteCommand {

    public static final String MESSAGE_DELETE_TAGS_SUCCESS = "Deleted tags : %1$s";

    private final Set<Tag> targetTags;

    private final List<Person> deletedPersons;

    /**
     * Creates an DeleteContactWithTagCommand to delete the {@code Person} with specified {@code Tag}
     */
    public DeleteContactWithTagCommand(Set<Tag> targetTags) {
        this.targetTags = targetTags;
        deletedPersons = new ArrayList<>();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> personList = model.getPersonListCopy();

        for (Person person : personList) {
            removePersonWithTags(model, person);
        }

        // Only save state if there are changes (person deleted)
        if (!deletedPersons.isEmpty()) {
            model.addState();
        }

        return new CommandResult(String.format(MESSAGE_DELETE_TAGS_SUCCESS, displayTags())
                + (deletedPersons.isEmpty()
                ? ""
                : String.format("\n" + MESSAGE_DELETE_PERSON_SUCCESS, displayPersons())));
    }

    private void removePersonWithTags(Model model, Person person) {
        Set<Tag> tags = new HashSet<>(person.getTags());

        boolean isUpdated = false;
        for (Tag t : targetTags) {
            if (tags.remove(t)) {
                isUpdated = true;
            }
        }

        // Dont delete people who not updated
        if (!isUpdated) {
            return;
        }

        if (tags.isEmpty()) {
            deletedPersons.add(person);
            model.deletePerson(person);
        } else {

            Person editedPerson = new Person(person.getName(),
                    person.getPhone(),
                    person.getEmail(),
                    person.getBirthday(),
                    person.getAddress(),
                    person.getRemark(),
                    tags);

            model.setPerson(person, editedPerson);
        }

    }

    /**
     * Returns tags in the form "a, b, c,..."
     */
    private String displayTags() {
        assert targetTags.size() > 0;
        return targetTags.stream()
                .map(t -> t.toString())
                .map(s -> s.substring(1, s.length() - 1))
                .reduce((a, b) -> a + ", " + b)
                .get();
    }

    /**
     * Returns list of persons in the form "a, b, c,..."
     */
    private String displayPersons() {
        return deletedPersons.stream()
                .map(p -> p.getName().toString())
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteContactWithTagCommand // instanceof handles nulls
                && targetTags.equals(((DeleteContactWithTagCommand) other).targetTags)); // state check
    }
}
