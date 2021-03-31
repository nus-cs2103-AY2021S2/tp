package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.MainApp;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;

/**
 * Opens email client with the selected person(s) email as the subject.
 */
public class EmailCommand extends Command {

    public static final String COMMAND_WORD = "email";
    public static final String MESSAGE_SUCCESS = "Opened email client";
    public static final String MESSAGE_FAILURE = "URL Exception occurred";
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Open email client, with email subjects.\n"
                    + "Parameters: { shown | INDEX… }\n"
                    + "Examples:\n"
                    + COMMAND_WORD + " shown\n"
                    + COMMAND_WORD + " 1\n"
                    + COMMAND_WORD + " 1 2 5";

    private final boolean isSpecialIndex;
    private final List<Index> selectedIndexes;

    /**
     * Initializes EmailCommand that selects all the shown items in list.
     */
    public EmailCommand() {
        isSpecialIndex = true;
        selectedIndexes = new ArrayList<>();
    }

    /**
     * Initializes EmailCommand with a list of parsed user input indexes.
     *
     * @param indexes parsed user input indexes
     * @throws NullPointerException if {@code indexes} is null.
     */
    public EmailCommand(List<Index> indexes) {
        isSpecialIndex = false;
        selectedIndexes = requireNonNull(indexes);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        // Validate indexes
        for (Index targetIndex : selectedIndexes) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
        }

        try {
            List<Person> personList = model.getFilteredPersonList();
            String emailValues = getEmailString(personList);
            URI uri = new URI("mailto:" + emailValues);
            MainApp.getInstance().getHostServices().showDocument(uri.toASCIIString());
            return new CommandResult(MESSAGE_SUCCESS);
        } catch (URISyntaxException e) {
            return new CommandResult(MESSAGE_FAILURE);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailCommand) // instanceof handles nulls
                && isSpecialIndex == ((EmailCommand) other).isSpecialIndex
                && ((EmailCommand) other).selectedIndexes.containsAll(selectedIndexes);
    }

    private String getEmailString(List<Person> personList) {
        if (isSpecialIndex) {
            return personList.stream()
                    .map(Person::getEmail)
                    .map(Email::toString)
                    .collect(Collectors.joining(","));
        }
        return selectedIndexes.stream()
                .map(Index::getZeroBased)
                .map(personList::get)
                .map(Person::getEmail)
                .map(Email::toString)
                .collect(Collectors.joining(","));
    }
}
