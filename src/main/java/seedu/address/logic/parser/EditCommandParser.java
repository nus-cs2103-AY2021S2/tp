package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRESSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIZE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditGarmentDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.description.Description;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SIZE, PREFIX_COLOUR, PREFIX_DRESSCODE, PREFIX_TYPE,
                        PREFIX_DESCRIPTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditGarmentDescriptor editGarmentDescriptor = new EditGarmentDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editGarmentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_SIZE).isPresent()) {
            editGarmentDescriptor.setSize(ParserUtil.parseSize(argMultimap.getValue(PREFIX_SIZE).get()));
        }
        if (argMultimap.getValue(PREFIX_COLOUR).isPresent()) {
            editGarmentDescriptor.setColour(ParserUtil.parseColour(argMultimap.getValue(PREFIX_COLOUR).get()));
        }
        if (argMultimap.getValue(PREFIX_DRESSCODE).isPresent()) {
            editGarmentDescriptor.setDressCode(ParserUtil.parseDressCode(argMultimap.getValue(PREFIX_DRESSCODE).get()));
        }
        if (argMultimap.getValue(PREFIX_TYPE).isPresent()) {
            editGarmentDescriptor.setType(ParserUtil.parseType(argMultimap.getValue(PREFIX_TYPE).get()));
        }
        parseDescriptionsForEdit(argMultimap.getAllValues(PREFIX_DESCRIPTION))
                .ifPresent(editGarmentDescriptor::setDescriptions);

        if (!editGarmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editGarmentDescriptor);
    }

    /**
     * Parses {@code Collection<String> descriptions} into a {@code Set<Description>}
     * if {@code descriptions} is non-empty.
     * If {@code descriptions} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Description>} containing zero descriptions.
     */
    private Optional<Set<Description>> parseDescriptionsForEdit(Collection<String> descriptions) throws ParseException {
        assert descriptions != null;

        if (descriptions.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> descriptionSet = descriptions.size() == 1 && descriptions
                .contains("") ? Collections.emptySet() : descriptions;
        return Optional.of(ParserUtil.parseDescriptions(descriptionSet));
    }

}
