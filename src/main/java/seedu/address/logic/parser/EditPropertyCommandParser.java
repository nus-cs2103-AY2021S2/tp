package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_ASKING_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSTAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAGS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditPropertyCommand;
import seedu.address.logic.commands.EditPropertyCommand.EditClientDescriptor;
import seedu.address.logic.commands.EditPropertyCommand.EditPropertyDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditPropertyCommand object.
 */
public class EditPropertyCommandParser implements Parser<EditPropertyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditPropertyCommand
     * and returns an EditPropertyCommand object for execution.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public EditPropertyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TYPE, PREFIX_ADDRESS, PREFIX_POSTAL,
                        PREFIX_DEADLINE, PREFIX_REMARK, PREFIX_CLIENT_NAME, PREFIX_CLIENT_CONTACT,
                        PREFIX_CLIENT_EMAIL, PREFIX_CLIENT_ASKING_PRICE, PREFIX_TAGS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPropertyCommand.MESSAGE_USAGE), pe);
        }

        EditPropertyDescriptor editPropertyDescriptor = new EditPropertyDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPropertyDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_TYPE).isPresent()) {
            editPropertyDescriptor.setType(ParserUtil.parsePropertyType(argMultimap.getValue(PREFIX_TYPE).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPropertyDescriptor.setAddress(
                    ParserUtil.parsePropertyAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_POSTAL).isPresent()) {
            editPropertyDescriptor.setPostalCode(
                    ParserUtil.parsePropertyPostal(argMultimap.getValue(PREFIX_POSTAL).get()));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editPropertyDescriptor.setDeadline(
                    ParserUtil.parsePropertyDeadline(argMultimap.getValue(PREFIX_DEADLINE).get()));
        }
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            editPropertyDescriptor.setRemarks(ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get()));
        }

        EditClientDescriptor editClientDescriptor = new EditClientDescriptor();
        if (argMultimap.getValue(PREFIX_CLIENT_NAME).isPresent()) {
            editClientDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_CLIENT_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_CLIENT_CONTACT).isPresent()) {
            editClientDescriptor.setContact(
                    ParserUtil.parseClientContact(argMultimap.getValue(PREFIX_CLIENT_CONTACT).get()));
        }
        if (argMultimap.getValue(PREFIX_CLIENT_EMAIL).isPresent()) {
            editClientDescriptor.setEmail(ParserUtil.parseClientEmail(argMultimap.getValue(PREFIX_CLIENT_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_CLIENT_ASKING_PRICE).isPresent()) {
            editClientDescriptor.setAskingPrice(
                    ParserUtil.parseClientAskingPrice(argMultimap.getValue(PREFIX_CLIENT_ASKING_PRICE).get()));
        }

        if (argMultimap.getValue(PREFIX_TAGS).isPresent()) {
            editPropertyDescriptor.setTags(ParserUtil.parseTags(argMultimap.getValue(PREFIX_TAGS).get()));
        }

        if (editClientDescriptor.isAnyFieldEdited()) {
            editPropertyDescriptor.setClientDescriptor(editClientDescriptor);
        }

        if (!editPropertyDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditPropertyCommand.MESSAGE_NOT_EDITED);
        }

        return new EditPropertyCommand(index, editPropertyDescriptor);
    }
}
