package seedu.partyplanet.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.partyplanet.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.partyplanet.commons.core.Messages;
import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.person.Person;
import seedu.partyplanet.model.tag.Tag;

public class EditToRemoveTagCommand extends EditCommand {

    public static final String MESSAGE_REMOVE_TAG_SUCCESS = "Removed tag from: %1$s";

    private final Tag targetTag;

    private final List<Person> editedPersons = new ArrayList<Person>();

    /**
     * Creates an EditToRemoveTagCommand to edit the {@code Person} with specified {@code Tag}
     */
    public EditToRemoveTagCommand(Tag targetTag) {
        this.targetTag = targetTag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        for (Person person: lastShownList) {
            removeTagFromPerson(model, person);
        }
        model.addState();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_REMOVE_TAG_SUCCESS,
                editedPersons.isEmpty() ? "" : displayPersons(editedPersons)));
    }

    private void removeTagFromPerson(Model model, Person personToEdit) {
        Set<Tag> tags = new HashSet<>(personToEdit.getTags());
        boolean isEdited = tags.remove(targetTag);
        if (isEdited) {
            editedPersons.add(personToEdit);
            Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                    personToEdit.getBirthday(), personToEdit.getAddress(), personToEdit.getRemark(), tags);
            model.setPerson(personToEdit, editedPerson);
        }
    }


    /**
     * Returns names of edited persons in the form "a, b, c,..."
     */
    private String displayPersons(List<Person> editedPersons) {
        return editedPersons.stream()
                .map(p -> p.getName().toString())
                .reduce((a, b) -> a + ", " + b)
                .get();
    }
}
