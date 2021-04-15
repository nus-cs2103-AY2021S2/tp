package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_GROUPMATE_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_NOT_UPDATED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UpdateGroupmateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.groupmate.Role;

/**
 * Parses input arguments and creates a new UpdateGroupmateCommand object
 */
public class UpdateGroupmateCommandParser implements Parser<UpdateGroupmateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code UpdateGroupmateCommand}.
     * and returns an {@code UpdateGroupmateCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public UpdateGroupmateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INDEX, PREFIX_NAME,
                PREFIX_ROLE);

        if (!arePrefixesPresent(argMultimap, PREFIX_INDEX) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UpdateGroupmateCommand.MESSAGE_USAGE));
        }

        Index projectIndex;

        try {
            projectIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX, pe);
        }

        Index targetGroupmateIndex;

        try {
            targetGroupmateIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_GROUPMATE_DISPLAYED_INDEX, pe);
        }

        UpdateGroupmateCommand.UpdateGroupmateDescriptor updateGroupmateDescriptor =
                new UpdateGroupmateCommand.UpdateGroupmateDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            updateGroupmateDescriptor.setName(
                    ParserUtil.parseGroupmateName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        parseRolesForEdit(argMultimap.getAllValues(PREFIX_ROLE)).ifPresent(updateGroupmateDescriptor::setRoles);

        if (!updateGroupmateDescriptor.isAnyFieldEdited()) {
            throw new ParseException(MESSAGE_NOT_UPDATED);
        }

        return new UpdateGroupmateCommand(projectIndex, targetGroupmateIndex, updateGroupmateDescriptor);
    }

    /**
     * Parses {@code Collection<String> roles} into a {@code Set<Role>} if {@code roles} is non-empty.
     * If {@code roles} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Role>} containing zero tags.
     */
    private Optional<Set<Role>> parseRolesForEdit(Collection<String> roles) throws ParseException {
        assert roles != null;

        if (roles.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> roleSet = roles.size() == 1 && roles.contains("") ? Collections.emptySet() : roles;
        return Optional.of(ParserUtil.parseRoles(roleSet));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given.
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
