package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHILD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Contact;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditAppointmentCommandParser implements Parser<EditAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_DATE, PREFIX_CONTACT,
                        PREFIX_CHILD);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            if (pe.getMessage().equals(MESSAGE_INVALID_INDEX)) {
                throw new ParseException(MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX, pe);
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        EditAppointmentCommand.MESSAGE_USAGE));
            }
        }

        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editAppointmentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editAppointmentDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editAppointmentDescriptor.setDateTime(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }

        parseContactsForEdit(argMultimap.getAllValues(PREFIX_CONTACT))
                .ifPresent(editAppointmentDescriptor::addAllContacts);

        parseChildTagsForEdit(argMultimap.getAllValues(PREFIX_CHILD))
                .ifPresent(editAppointmentDescriptor::addAllTags);

        if (!editAppointmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditAppointmentCommand.MESSAGE_NOT_EDITED);
        }

        return new EditAppointmentCommand(index, editAppointmentDescriptor);
    }

    /**
     * Parses {@code Collection<String> contacts} into a {@code Set<Contact>} if {@code contacts} is non-empty.
     * If {@code contacts} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Contact>} containing zero contacts.
     */
    private Optional<Set<Contact>> parseContactsForEdit(Collection<String> contacts) throws ParseException {
        assert contacts != null;

        if (contacts.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> contactSet = contacts.size() == 1 && contacts.contains("")
                ? Collections.emptySet()
                : contacts;
        return Optional.of(ParserUtil.parseContacts(contactSet));
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseChildTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("")
                        ? Collections.emptySet()
                        : tags;
        return Optional.of(ParserUtil.parseChildTags(tagSet));
    }

}
