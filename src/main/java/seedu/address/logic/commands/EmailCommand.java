package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.EmailCommandParser.SELECTED;
import static seedu.address.logic.parser.EmailCommandParser.SPECIAL_INDEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

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
    public static final String MESSAGE_NO_PERSON = "No person(s) to email";
    public static final String MESSAGE_NO_SELECTED = "No selected person(s) to email";
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Open email client, with email subjects.\n"
                    + "Parameters: { shown | selected | INDEX… }\n"
                    + "Examples:\n"
                    + COMMAND_WORD + " " + SPECIAL_INDEX + "\n"
                    + COMMAND_WORD + " " + SELECTED + "\n"
                    + COMMAND_WORD + " 1\n"
                    + COMMAND_WORD + " 1 2 5";

    private final boolean isSpecialIndex;
    private final boolean isEmailSelected;
    private final List<Index> selectedIndexes;

    /**
     * Private constructor. Should only be called via builder.
     *
     * @param isSpecialIndex
     * @param isEmailSelected
     * @param selectedIndexes
     */
    private EmailCommand(boolean isSpecialIndex, boolean isEmailSelected,
            List<Index> selectedIndexes) {
        this.isSpecialIndex = isSpecialIndex;
        this.isEmailSelected = isEmailSelected;
        this.selectedIndexes = requireNonNull(selectedIndexes);
    }

    public static EmailCommand buildEmailSelectedCommand() {
        return new EmailCommand(false, true, new ArrayList<>());
    }

    public static EmailCommand buildEmailShownCommand() {
        return new EmailCommand(true, false, new ArrayList<>());
    }

    public static EmailCommand buildEmailIndexCommand(List<Index> indexes) {
        return new EmailCommand(false, false, indexes);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getFilteredPersonList().size() == 0) {
            throw new CommandException(MESSAGE_NO_PERSON);
        }

        if (isSpecialIndex) {
            return emailAll(model);
        }
        if (isEmailSelected) {
            return emailSelected(model);
        }
        return emailOneOrMultiple(model);

    }

    /**
     * Emails one or multiple person from model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException if index is invalid
     */
    private CommandResult emailOneOrMultiple(Model model) throws CommandException {
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

    /**
     * Emails all the person in the shown person list.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     */
    private CommandResult emailAll(Model model) {
        try {
            List<Person> personList = model.getFilteredPersonList();
            String emailValues = getEmailString(personList);
            URI uri = new URI("mailto:" + emailValues);
            MainApp.getInstance().getHostServices().showDocument(uri.toASCIIString());
            return new CommandResult(MESSAGE_SUCCESS);
        } catch (URISyntaxException e) {
            return new CommandResult(MESSAGE_FAILURE);
        } finally {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        }
    }

    /**
     * Emails all the selected person.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     */
    private CommandResult emailSelected(Model model) throws CommandException {
        model.applySelectedPredicate();
        if (model.getFilteredPersonList().size() == 0) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            throw new CommandException(MESSAGE_NO_SELECTED);
        }
        return emailAll(model);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailCommand) // instanceof handles nulls
                && isSpecialIndex == ((EmailCommand) other).isSpecialIndex
                && isEmailSelected == ((EmailCommand) other).isEmailSelected
                && selectedIndexes.containsAll(((EmailCommand) other).selectedIndexes)
                && ((EmailCommand) other).selectedIndexes.containsAll(selectedIndexes);
    }

    private String getEmailString(List<Person> personList) {
        if (isSpecialIndex || isEmailSelected) {
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
