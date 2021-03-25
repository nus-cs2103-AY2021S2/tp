package dog.pawbook.logic.parser;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_SESSION;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import dog.pawbook.logic.commands.EditProgramCommand;
import dog.pawbook.logic.parser.exceptions.ParseException;
import dog.pawbook.model.managedentity.program.Session;

public class EditProgramCommandParser extends EditCommandParser<EditProgramCommand> {
    private static final Prefix[] EDIT_PROGRAM_SPECIFIC_PREFIXES = {PREFIX_SESSION};
    private static final Prefix[] EDIT_PROGRAM_ALL_PREFIXES =
            Stream.of(EDIT_COMMON_PREFIXES, EDIT_PROGRAM_SPECIFIC_PREFIXES).flatMap(Stream::of).toArray(Prefix[]::new);

    @Override
    protected Prefix[] getAllPrefixes() {
        return EDIT_PROGRAM_ALL_PREFIXES;
    }

    @Override
    protected String getUsageText() {
        return EditProgramCommand.MESSAGE_USAGE;
    }

    @Override
    protected EditProgramCommand genEditCommand(int id, ArgumentMultimap argMultimap) throws ParseException {
        EditProgramCommand.EditProgramDescriptor editProgramDescriptor = new EditProgramCommand.EditProgramDescriptor();

        fillCommonAttributes(editProgramDescriptor, argMultimap);

        parseSessionsForEdit(argMultimap.getAllValues(PREFIX_SESSION)).ifPresent(editProgramDescriptor::setSessions);

        if (editProgramDescriptor.isNoFieldEdited()) {
            throw new ParseException(EditProgramCommand.MESSAGE_NOT_EDITED);
        }

        return new EditProgramCommand(id, editProgramDescriptor);
    }

    /**
     * Parses {@code Collection<String> sessions} into a {@code Set<Session>} if {@code sessions} is non-empty.
     * If {@code sessions} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Session>} containing zero sessions.
     */
    private static Optional<Set<Session>> parseSessionsForEdit(Collection<String> sessions) throws ParseException {
        requireNonNull(sessions);

        if (sessions.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> sessionSet = sessions.size() == 1 && sessions.contains("")
                ? Collections.emptySet()
                : sessions;
        return Optional.of(ParserUtil.parseSessions(sessionSet));
    }
}
