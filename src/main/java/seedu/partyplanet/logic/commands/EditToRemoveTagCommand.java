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

public class EditToRemoveTagCommand extends EditCommand {

    public static final String MESSAGE_REMOVE_TAGS_SUCCESS = "Removed tag from: %1$s";
    public static final String MESSAGE_TAGS_NOT_REMOVED = "These tags do not exist in persons listed. No tags removed.";

    private final Set<Tag> targetTags;

    private final List<Person> editedPersons = new ArrayList<Person>();

    /**
     * Creates an EditToRemoveTagCommand to edit the {@code Person} with specified {@code Tag}
     */
    public EditToRemoveTagCommand(Set<Tag> targetTags) {
        this.targetTags = targetTags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        for (Person person : lastShownList) {
            if (hasTags(person)) {
                editedPersons.add(person);
            }
        }

        if (editedPersons.isEmpty()) {
            return new CommandResult(MESSAGE_TAGS_NOT_REMOVED);
        }

        for (Person personToEdit : editedPersons) {
            removeTagFromPerson(model, personToEdit);
        }

        model.addState();

        return new CommandResult(String.format(MESSAGE_REMOVE_TAGS_SUCCESS,
                editedPersons.isEmpty() ? "" : displayPersons(editedPersons)));
    }

    private boolean hasTags(Person personToCheck) {
        Set<Tag> personTags = personToCheck.getTags();

        for (Tag targetTag : targetTags) {
            if (personTags.contains(targetTag)) {
                return true;
            }
        }
        return false;
    }

    private void removeTagFromPerson(Model model, Person personToEdit) {
        Set<Tag> personTags = new HashSet<>(personToEdit.getTags());

        for (Tag targetTag: targetTags) {
            personTags.remove(targetTag);
        }

        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getBirthday(), personToEdit.getAddress(), personToEdit.getRemark(), personTags);
        model.setPerson(personToEdit, editedPerson);
    }


    /**
     * Returns names of edited persons in the form "a, b, c,..."
     */
    private String displayPersons(List<Person> editedPersons) {
        assert editedPersons.size() > 0;
        return editedPersons.stream()
                .map(p -> p.getName().toString())
                .reduce((a, b) -> a + ", " + b)
                .get();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditToRemoveTagCommand // instanceof handles nulls
                && targetTags.equals(((EditToRemoveTagCommand) other).targetTags)); // state check
    }
}
