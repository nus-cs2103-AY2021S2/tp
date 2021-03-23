package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRICULATION_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL_RESIDENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VACCINATION_STATUS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MATRICULATION_NUMBER, PREFIX_FACULTY, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_VACCINATION_STATUS, PREFIX_MEDICAL_DETAILS,
                        PREFIX_SCHOOL_RESIDENCE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editStudentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_MATRICULATION_NUMBER).isPresent()) {
            editStudentDescriptor.setMatriculationNumber(ParserUtil.parseMatric(argMultimap
                    .getValue(PREFIX_MATRICULATION_NUMBER).get()));
        }
        if (argMultimap.getValue(PREFIX_FACULTY).isPresent()) {
            editStudentDescriptor.setFaculty(ParserUtil.parseFaculty(argMultimap.getValue(PREFIX_FACULTY).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editStudentDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editStudentDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editStudentDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_VACCINATION_STATUS).isPresent()) {
            editStudentDescriptor.setVaccinationStatus(ParserUtil.parseVacStatus(argMultimap
                    .getValue(PREFIX_VACCINATION_STATUS).get()));
        }
        if (argMultimap.getValue(PREFIX_MEDICAL_DETAILS).isPresent()) {
            editStudentDescriptor.setMedicalDetails(ParserUtil.parseMedicalDetails(argMultimap
                    .getValue(PREFIX_MEDICAL_DETAILS).get()));
        }

        if (argMultimap.getValue(PREFIX_SCHOOL_RESIDENCE).isPresent()) {
            editStudentDescriptor.setSchoolResidence(ParserUtil.parseSchoolRes(argMultimap
                    .getValue(PREFIX_SCHOOL_RESIDENCE)));
        }

        if (!editStudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editStudentDescriptor);
    }
}
