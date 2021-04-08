package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.commons.core.identifier.Identifier;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditEventDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * Error Cases:
     * 1. Integer out of range
     * 1a. Integer negative
     * 1b. Integer larger than 2^31 - 1 or less than -2^31
     * 2. No descriptors are present
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_STATUS, PREFIX_PRIORITY);

        Identifier identifier;

        try {
            identifier = ParserUtil.parseIdentifier(argMultimap.getPreamble());
        } catch (ParseException pe) {
            if (pe.getMessage().equals(ParserUtil.MESSAGE_ADDITIONAL_ARTEFACTS)
                    || pe.getMessage().equals(ParserUtil.MESSAGE_EMPTY_IDENTIFIER)) {
                throw new ParseException(pe.getMessage()
                        + EditCommand.MESSAGE_USAGE);
            }
            throw new ParseException(pe.getMessage());
        }

        EditEventDescriptor editEventDescriptor = new EditEventDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editEventDescriptor.setEventName(ParserUtil.parseEventName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editEventDescriptor.setDescription(ParserUtil.parseDescription(
                    argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editEventDescriptor.setEventStatus(ParserUtil.parseEventStatus(argMultimap.getValue(PREFIX_STATUS).get()));
        }

        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            editEventDescriptor.setEventPriority(ParserUtil.parseEventPriority(
                    argMultimap.getValue(PREFIX_PRIORITY).get()));
        }

        if (!editEventDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(identifier, editEventDescriptor);
    }

}
