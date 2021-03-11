package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddPropertyCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditPropertyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.name.Name;
import seedu.address.model.property.Address;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.PostalCode;
import seedu.address.model.property.Property;
import seedu.address.model.property.Type;
import seedu.address.model.property.client.AskingPrice;
import seedu.address.model.property.client.Client;
import seedu.address.model.property.client.Contact;
import seedu.address.model.property.client.Email;
import seedu.address.model.remark.Remark;

import java.util.stream.Stream;

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
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

/**
 * Parses input arguments and creates a new AddPropertyCommand object.
 */
public class EditPropertyCommandParser implements Parser<EditPropertyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPropertyCommand
     * and returns an AddPropertyCommand object for execution.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public EditPropertyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TYPE, PREFIX_ADDRESS, PREFIX_POSTAL,
                        PREFIX_DEADLINE, PREFIX_REMARK, PREFIX_CLIENT_NAME, PREFIX_CLIENT_CONTACT,
                        PREFIX_CLIENT_EMAIL, PREFIX_CLIENT_ASKING_PRICE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPropertyCommand.MESSAGE_USAGE), pe);
        }

        EditPropertyCommand.EditPropertyDescriptor editPropertyDescriptor = new EditPropertyCommand.EditPropertyDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPropertyDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_TYPE).isPresent()) {
            editPropertyDescriptor.setType(ParserUtil.parsePropertyType(argMultimap.getValue(PREFIX_TYPE).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPropertyDescriptor.setAddress(ParserUtil.parsePropertyAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_POSTAL).isPresent()) {
            editPropertyDescriptor.setPostalCode(ParserUtil.parsePropertyPostal(argMultimap.getValue(PREFIX_POSTAL).get()));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editPropertyDescriptor.setDeadline(ParserUtil.parsePropertyDeadline(argMultimap.getValue(PREFIX_DEADLINE).get()));
        }
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            editPropertyDescriptor.setRemark(ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get()));
        }

        EditPropertyCommand.EditClientDescriptor editClientDescriptor = new EditPropertyCommand.EditClientDescriptor();
        if (argMultimap.getValue(PREFIX_CLIENT_NAME).isPresent()) {
            editClientDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_CLIENT_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_CLIENT_CONTACT).isPresent()) {
            editClientDescriptor.setContact(ParserUtil.parseClientContact(argMultimap.getValue(PREFIX_CLIENT_CONTACT).get()));
        }
        if (argMultimap.getValue(PREFIX_CLIENT_EMAIL).isPresent()) {
            editClientDescriptor.setEmail(ParserUtil.parseClientEmail(argMultimap.getValue(PREFIX_CLIENT_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_CLIENT_ASKING_PRICE).isPresent()) {
            editClientDescriptor.setAskingPrice(ParserUtil.parseClientAskingPrice(argMultimap.getValue(PREFIX_CLIENT_ASKING_PRICE).get()));
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
