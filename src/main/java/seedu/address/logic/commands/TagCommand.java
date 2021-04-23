package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHILD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Address;
import seedu.address.model.Model;
import seedu.address.model.Name;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Favourite;
import seedu.address.model.contact.Phone;
import seedu.address.model.contact.TimeAdded;
import seedu.address.model.tag.Tag;
/**
 * Appends tags to an existing contact in the address book.
 */
public class TagCommand extends Command {
    public static final String COMMAND_WORD = "tag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Appends tags to the tags of the contact identified "
            + "by the index number used in the displayed contact list. "
            + "Tags will be added to existing tags.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_OPTION + "OPTION] "
            + "[" + PREFIX_CHILD + "CHILDTAG]... "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "form teacher";
    public static final String MESSAGE_TAG_CONTACT_SUCCESS = "Tagged Contact: \n%1$s";
    public static final String MESSAGE_TAG_REPLACE_CONTACT_SUCCESS = "Tags replaced for: \n%1$s";

    private final Index index;
    private final boolean isReplace;
    private final Set<Tag> tags;

    /**
     * @param index of the contact in the filtered contact list to edit
     * @param tags Set of tags to be appended to the contact
     */
    public TagCommand(Index index, Set<Tag> tags) {
        this.index = index;
        this.tags = tags;
        this.isReplace = false;
    }
    /**
     * @param index of the contact in the filtered contact list to edit
     * @param tags Set of tags to be appended to the contact
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
        List<Contact> lastShownList = model.getFilteredContactList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Contact contactToTag = lastShownList.get(index.getZeroBased());
        Contact taggedContact;
        if (isReplace) {
            taggedContact = createReplacedTaggedContact(contactToTag);
            model.setContact(contactToTag, taggedContact);
            model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
            return new CommandResult(String.format(MESSAGE_TAG_REPLACE_CONTACT_SUCCESS, taggedContact));
        } else {
            taggedContact = createTaggedContact(contactToTag);
            model.setContact(contactToTag, taggedContact);
            model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
            return new CommandResult(String.format(MESSAGE_TAG_CONTACT_SUCCESS, taggedContact));
        }

    }

    /**
     * Creates and returns a {@code Contact} with the details of {@code contactToTag}
     * with tags appended with {@code tags}.
     */
    private Contact createTaggedContact(Contact contactToTag) {
        assert contactToTag != null;

        Name updatedName = contactToTag.getName();
        Phone updatedPhone = contactToTag.getPhone();
        Email updatedEmail = contactToTag.getEmail();
        Address updatedAddress = contactToTag.getAddress();
        Set<Tag> updatedTags = new HashSet<>(contactToTag.getTags());
        updatedTags.addAll(tags);
        TimeAdded timeAdded = contactToTag.getTimeAdded();
        Favourite favourite = contactToTag.getFavourite();

        return new Contact(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, timeAdded, favourite);
    }

    /**
     * Creates and returns a {@code Contact} with the details of {@code contactToTag}
     * with tags replaced with {@code tags}.
     */
    private Contact createReplacedTaggedContact(Contact contactToTag) {
        assert contactToTag != null;

        Name updatedName = contactToTag.getName();
        Phone updatedPhone = contactToTag.getPhone();
        Email updatedEmail = contactToTag.getEmail();
        Address updatedAddress = contactToTag.getAddress();
        Set<Tag> updatedTags = tags;
        TimeAdded timeAdded = contactToTag.getTimeAdded();
        Favourite favourite = contactToTag.getFavourite();

        return new Contact(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, timeAdded, favourite);
    }
}
