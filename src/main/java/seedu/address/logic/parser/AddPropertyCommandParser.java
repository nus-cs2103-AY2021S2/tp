package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSTAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddPropertyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.name.Name;
import seedu.address.model.property.Address;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.PostalCode;
import seedu.address.model.property.Property;
import seedu.address.model.property.Type;
import seedu.address.model.remark.Remark;

/**
 * Parses input arguments and creates a new AddPropertyCommand object.
 */
public class AddPropertyCommandParser implements Parser<AddPropertyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPropertyCommand
     * and returns an AddPropertyCommand object for execution.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public AddPropertyCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TYPE, PREFIX_ADDRESS, PREFIX_POSTAL,
                        PREFIX_DEADLINE, PREFIX_REMARK);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TYPE, PREFIX_ADDRESS, PREFIX_POSTAL, PREFIX_DEADLINE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parsePropertyName(argMultimap.getValue(PREFIX_NAME).get());
        Type type = ParserUtil.parsePropertyType(argMultimap.getValue(PREFIX_TYPE).get());
        Address address = ParserUtil.parsePropertyAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        PostalCode postal = ParserUtil.parsePropertyPostal(argMultimap.getValue(PREFIX_POSTAL).get());
        Deadline deadline = ParserUtil.parsePropertyDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        Remark remark = ParserUtil.parsePropertyRemark(argMultimap.getValue(PREFIX_REMARK).orElse(null));

        Property property = new Property(name, type, address, postal, deadline, remark);

        return new AddPropertyCommand(property);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
