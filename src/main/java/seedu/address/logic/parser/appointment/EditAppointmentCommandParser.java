package seedu.address.logic.parser.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT_START;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.appointment.EditAppointmentCommand;
import seedu.address.logic.commands.appointment.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;


public class EditAppointmentCommandParser implements Parser<EditAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditAppointmentCommand
     * and returns an EditAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PATIENT, PREFIX_DOCTOR,
                        PREFIX_TIMESLOT_START, PREFIX_TIMESLOT_END, PREFIX_TIMESLOT_DURATION, PREFIX_TAG);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAppointmentCommand.MESSAGE_USAGE), pe);
        }

        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();
        if (argMultimap.getValue(PREFIX_PATIENT).isPresent()) {
            editAppointmentDescriptor
                    .setPatientIndex(ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PATIENT).get()));
        }

        if (argMultimap.getValue(PREFIX_DOCTOR).isPresent()) {
            editAppointmentDescriptor.setDoctor(argMultimap.getValue(PREFIX_DOCTOR).get());
        }

        if (argMultimap.getValue(PREFIX_TIMESLOT_END).isPresent()) {
            editAppointmentDescriptor.setTimeslot(ParserUtil
                    .parseTimeslotByEnd(argMultimap.getValue(PREFIX_TIMESLOT_START).get(),
                    argMultimap.getValue(PREFIX_TIMESLOT_END).get()));
        }
        if (argMultimap.getValue(PREFIX_TIMESLOT_DURATION).isPresent()) {
            editAppointmentDescriptor
                    .setTimeslot(ParserUtil.parseTimeslotByDuration(argMultimap.getValue(PREFIX_TIMESLOT_START).get(),
                    argMultimap.getValue(PREFIX_TIMESLOT_DURATION).get()));
        }

        if (!editAppointmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditAppointmentCommand.MESSAGE_NOT_EDITED);
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editAppointmentDescriptor::setTags);

        return new EditAppointmentCommand(index, editAppointmentDescriptor);
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
