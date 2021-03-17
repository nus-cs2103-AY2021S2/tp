package seedu.partyplanet.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.person.Person;

/**
 * Deletes all person currently displayed.
 */
public class DeleteClearCommand extends DeleteCommand {

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = new ArrayList<>(model.getFilteredPersonList());
        List<Person> deletedPersons = new ArrayList<>();

        for (Person p : lastShownList) {
            deletedPersons.add(p);
            model.deletePerson(p);
        }

        // Only save state if there are changes (person deleted)
        if (!deletedPersons.isEmpty()) {
            model.addState();
        }
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, displayPersons(deletedPersons)));
    }

    /**
     * Returns list of persons in the form "a, b, c,..."
     */
    private String displayPersons(List<Person> deletedPersons) {
        return deletedPersons.stream()
                .map(p -> p.getName().toString())
                .reduce((a, b) -> a + ", " + b)
                .orElse("None!");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteClearCommand); // instanceof handles nulls
    }
}
