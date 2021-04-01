package seedu.iscam.logic.parser.clientcommands;

import static java.util.Objects.requireNonNull;
import static seedu.iscam.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_IMAGE;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_PLAN;
import static seedu.iscam.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.EditCommand;
import seedu.iscam.logic.commands.EditCommand.EditClientDescriptor;
import seedu.iscam.logic.parser.ArgumentMultimap;
import seedu.iscam.logic.parser.ArgumentTokenizer;
import seedu.iscam.logic.parser.Parser;
import seedu.iscam.logic.parser.ParserUtil;
import seedu.iscam.logic.parser.exceptions.ParseException;
import seedu.iscam.logic.parser.exceptions.ParseFormatException;
import seedu.iscam.model.commons.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_LOCATION, PREFIX_PLAN, PREFIX_TAG, PREFIX_IMAGE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseFormatException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE),
                    pe);
        }

        EditClientDescriptor editClientDescriptor = new EditClientDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editClientDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editClientDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editClientDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            editClientDescriptor.setLocation(ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get()));
        }
        if (argMultimap.getValue(PREFIX_PLAN).isPresent()) {
            String planName = argMultimap.getValue(PREFIX_PLAN).get();

            // If user input "ip/", Insurance Plan changes to "No plans yet"
            if (planName.equals("")) {
                planName = "No plans yet";
            }

            editClientDescriptor.setPlan(ParserUtil.parsePlan(planName));
        }
        if (argMultimap.getValue(PREFIX_IMAGE).isPresent()) {
            editClientDescriptor.setImageRes(ParserUtil.parseImageRes(argMultimap.getValue(PREFIX_IMAGE).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editClientDescriptor::setTags);

        if (!editClientDescriptor.isAnyFieldEdited()) {
            throw new ParseFormatException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editClientDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
