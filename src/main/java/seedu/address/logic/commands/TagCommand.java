package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHILD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Favourite;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TimeAdded;
import seedu.address.model.tag.Tag;
/**
 * Appends tags to an existing person in the address book.
 */
public class TagCommand extends Command {
    public static final String COMMAND_WORD = "tag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Appends tags to the tags of the person identified "
            + "by the index number used in the displayed person list. "
            + "Tags will be added to existing tags.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_OPTION + "OPTION] "
            + "[" + PREFIX_CHILD + "CHILDTAG]... "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "form teacher";
    public static final String MESSAGE_TAG_PERSON_SUCCESS = "Tagged Person: %1$s";
    public static final String MESSAGE_TAG_REPLACE_PERSON_SUCCESS = "Tags replaced for: %1$s";

    private final Index index;
    private final boolean isReplace;
    private final Set<Tag> tags;

    /**
     * @param index of the person in the filtered person list to edit
     * @param tags Set of tags to be appended to the person
     */
    public TagCommand(Index index, Set<Tag> tags) {
        this.index = index;
        this.tags = tags;
        this.isReplace = false;
    }
    /**
     * @param index of the person in the filtered person list to edit
     * @param tags Set of tags to be appended to the person
     * @param isReplace Whether this TagCommand is meant to replace existing tags
     */
    public TagCommand(Index index, Set<Tag> tags, boolean isReplace) {
        this.index = index;
        this.tags = tags;
        this.isReplace = isReplace;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToTag = lastShownList.get(index.getZeroBased());
        Person taggedPerson;
        if (isReplace) {
            taggedPerson = createReplacedTaggedPerson(personToTag);
            model.setPerson(personToTag, taggedPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(String.format(MESSAGE_TAG_REPLACE_PERSON_SUCCESS, taggedPerson));
        } else {
            taggedPerson = createTaggedPerson(personToTag);
            model.setPerson(personToTag, taggedPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(String.format(MESSAGE_TAG_PERSON_SUCCESS, taggedPerson));
        }

    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToTag}
     * with tags appended with {@code tags}.
     */
    private Person createTaggedPerson(Person personToTag) {
        assert personToTag != null;

        Name updatedName = personToTag.getName();
        Phone updatedPhone = personToTag.getPhone();
        Email updatedEmail = personToTag.getEmail();
        Address updatedAddress = personToTag.getAddress();
        Set<Tag> updatedTags = new HashSet<>(personToTag.getTags());
        updatedTags.addAll(tags);
        TimeAdded timeAdded = personToTag.getTimeAdded();
        Favourite favourite = personToTag.getFavourite();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, timeAdded, favourite);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToTag}
     * with tags replaced with {@code tags}.
     */
    private Person createReplacedTaggedPerson(Person personToTag) {
        assert personToTag != null;

        Name updatedName = personToTag.getName();
        Phone updatedPhone = personToTag.getPhone();
        Email updatedEmail = personToTag.getEmail();
        Address updatedAddress = personToTag.getAddress();
        Set<Tag> updatedTags = tags;
        TimeAdded timeAdded = personToTag.getTimeAdded();
        Favourite favourite = personToTag.getFavourite();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, timeAdded, favourite);
    }
}
