package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.TagCommandParser.tagsToString;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

/**
 * Add tags to person in the address book.
 */
public class AddTagCommand extends TagCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add tag(s) to person(s) in the address book.\n"
            + "Parameters: " + ADD_SUB_COMMAND_WORD + " { shown | selected | INDEX… } -t TAG…\n"
            + "Examples:\n"
            + COMMAND_WORD + " " + ADD_SUB_COMMAND_WORD + " shown -t Photoshop\n"
            + COMMAND_WORD + " " + ADD_SUB_COMMAND_WORD + " selected -t Illustrator\n"
            + COMMAND_WORD + " " + ADD_SUB_COMMAND_WORD + " 1 2 3 -t Photoshop -t Illustrator";

    public static final String MESSAGE_SUCCESS = "Tag command executed on %1$d person(s). Tag(s) added: %2$s\n"
            + "Note: Same tags will not be added to the persons.";

    private final Set<Tag> tags;
    private final List<Index> targetIndexes;
    private final boolean isShownIndex;
    private final boolean isSelectedIndex;

    /**
     * Creates an AddTagCommand.
     */
    private AddTagCommand(List<Index> targetIndexes, Set<Tag> tags, boolean isShownIndex, boolean isSelectedIndex) {
        requireAllNonNull(targetIndexes, tags, isShownIndex, isSelectedIndex);
        this.targetIndexes = targetIndexes;
        this.tags = tags;
        this.isShownIndex = isShownIndex;
        this.isSelectedIndex = isSelectedIndex;
    }

    /**
     * Creates an AddTagCommand that adds the specified {@code tags} to all the shown items in the list.
     */
    public static AddTagCommand createWithShownIndex(Set<Tag> tags) {
        return new AddTagCommand(new ArrayList<>(), tags, true, false);
    }

    /**
     * Creates an AddTagCommand that adds the specified {@code tags} to all the selected items in the list.
     */
    public static AddTagCommand createWithSelectedIndex(Set<Tag> tags) {
        return new AddTagCommand(new ArrayList<>(), tags, false, true);
    }

    /**
     * Creates an AddTagCommand that adds the specified {@code tags} to all the items in the {@code targetIndexes}.
     */
    public static AddTagCommand createWithTargetIndexes(List<Index> targetIndexes, Set<Tag> tags) {
        return new AddTagCommand(targetIndexes, tags, false, false);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (isShownIndex) {
            return addToShownIndex(model);
        }
        if (isSelectedIndex) {
            return addToSelectedIndex(model);
        }
        return addToTargetIndexes(model);
    }

    /**
     * Add tags to shown person from {@code Model}.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException when no person is shown
     */
    private CommandResult addToShownIndex(Model model) throws CommandException {
        List<Person> personList = model.getFilteredPersonList();
        if (personList.size() == 0) {
            throw new CommandException(MESSAGE_NO_SHOWN_PERSON);
        }

        for (Person person : personList) {
            Person editedPerson = createEditedPerson(person, tags);
            model.setPerson(person, editedPerson);
        }

        int updateCount = personList.size();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, updateCount, tagsToString(tags)));
    }

    /**
     * Add tags to selected person from {@code Model}.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException when no peron is selected
     */
    private CommandResult addToSelectedIndex(Model model) throws CommandException {
        model.applySelectedPredicate();
        if (model.getFilteredPersonList().size() == 0) {
            throw new CommandException(MESSAGE_NO_SELECTED_PERSON);
        }
        return addToShownIndex(model);
    }

    /**
     * Add tags to person from {@code Model} based on the {@code targetIndexes}.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException if index is invalid
     */
    private CommandResult addToTargetIndexes(Model model) throws CommandException {
        List<Person> shownList = model.getFilteredPersonList();

        // Validate indexes
        for (Index targetIndex : targetIndexes) {
            if (targetIndex.getZeroBased() >= shownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
        }

        for (Index targetIndex : targetIndexes) {
            Person person = shownList.get(targetIndex.getZeroBased());
            Person editedPerson = createEditedPerson(person, tags);
            model.setPerson(person, editedPerson);
        }

        int updateCount = targetIndexes.size();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, updateCount, tagsToString(tags)));
    }

    /**
     * Creates and returns a {@code Person} after adding {@code tags}.
     */
    private static Person createEditedPerson(Person personToEdit, Set<Tag> tags) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Company updatedCompany = personToEdit.getCompany();
        JobTitle updatedJobTitle = personToEdit.getJobTitle();
        Address updatedAddress = personToEdit.getAddress();
        Remark updatedRemark = personToEdit.getRemark();
        Set<Tag> updatedTags = new HashSet<>(personToEdit.getTags());
        updatedTags.addAll(tags);

        return new Person(updatedName, updatedPhone, updatedEmail, updatedCompany, updatedJobTitle,
                updatedAddress, updatedRemark, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTagCommand // instanceof handles nulls
                && tags.equals(((AddTagCommand) other).tags)
                && targetIndexes.containsAll(((AddTagCommand) other).targetIndexes)
                && ((AddTagCommand) other).targetIndexes.containsAll(targetIndexes)
                && isShownIndex == ((AddTagCommand) other).isShownIndex)
                && isSelectedIndex == ((AddTagCommand) other).isSelectedIndex;
    }

}
