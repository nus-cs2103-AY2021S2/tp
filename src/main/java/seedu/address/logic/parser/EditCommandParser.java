package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    public static final String SPECIAL_INDEX = "shown";
    public static final String SELECTED_INDEX = "selected";

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_COMPANY,
                        PREFIX_JOB_TITLE, PREFIX_ADDRESS, PREFIX_REMARK, PREFIX_TAG);

        List<Index> targetIndexes;
        EditPersonDescriptor editPersonDescriptor;

        // Parse special index
        if (argMultimap.getPreamble().equals(SPECIAL_INDEX)) {
            editPersonDescriptor = parseEditPersonDescriptor(argMultimap);
            if (!editPersonDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
            }
            return EditCommand.buildEditShownCommand(editPersonDescriptor);
        }

        if (argMultimap.getPreamble().trim().equals(SELECTED_INDEX)) {
            editPersonDescriptor = parseEditPersonDescriptor(argMultimap);
            if (!editPersonDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
            }
            return EditCommand.buildEditSelectedCommand(editPersonDescriptor);
        }

        try {
            targetIndexes = ParserUtil.parseIndexes(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE),
                    pe);
        }

        editPersonDescriptor = parseEditPersonDescriptor(argMultimap);
        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        return EditCommand.buildEditIndexCommand(targetIndexes, editPersonDescriptor);
    }

    private EditPersonDescriptor parseEditPersonDescriptor(ArgumentMultimap argMultimap)
            throws ParseException {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            editPersonDescriptor.setCompany(ParserUtil.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get()));
        }
        if (argMultimap.getValue(PREFIX_JOB_TITLE).isPresent()) {
            editPersonDescriptor.setJobTitle(ParserUtil.parseJobTitle(argMultimap.getValue(PREFIX_JOB_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            editPersonDescriptor.setRemark(ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        return editPersonDescriptor;
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

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns true if arguments are valid to be aliased. Only the argument in the
     * last prefix is allowed to be empty in order to be valid for aliasing, and all
     * other prefixes than the last should be valid input values.
     */
    @Override
    public boolean isValidCommandToAlias(String userInput) {
        if (userInput.trim().isEmpty()) {
            return true;
        }

        if (userInput.trim().equals(SPECIAL_INDEX)) {
            return true;
        }

        if (userInput.trim().equals(SELECTED_INDEX)) {
            return true;
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_COMPANY, PREFIX_JOB_TITLE, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_REMARK);

        // Returns false if index found in preamble of arguments is invalid
        if (!argMultimap.getPreamble().equals(SPECIAL_INDEX) && !argMultimap.getPreamble().equals(SELECTED_INDEX)) {
            try {
                ParserUtil.parseIndexes(argMultimap.getPreamble());
            } catch (ParseException pe) {
                return false;
            }
        }

        // Checks if only the last prefix argument is empty, and all other prefixes have valid input values
        Optional<Prefix> optionalPrefix = ArgumentTokenizer.getLastPrefix(userInput, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_COMPANY, PREFIX_JOB_TITLE, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_REMARK);

        if (optionalPrefix.isEmpty()) {
            return true;
        }

        final Prefix lastPrefix = optionalPrefix.get();
        return ParserUtil.validatePersonAliasArgs(argMultimap, lastPrefix);
    }

}
