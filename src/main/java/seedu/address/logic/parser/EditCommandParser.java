package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSURANCE_POLICY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
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
import seedu.address.model.insurancepolicy.InsurancePolicy;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_TAG, PREFIX_INSURANCE_POLICY, PREFIX_MEETING);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

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
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        parsePoliciesForEdit(argMultimap.getAllValues(PREFIX_INSURANCE_POLICY))
                .ifPresent(editPersonDescriptor::setPolicies);

        parseMeetingForEdit(argMultimap.getAllValues(PREFIX_MEETING))
                .ifPresent(editPersonDescriptor::setMeeting);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
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
     * Parses {@code Collection<String> policyId} into a {@code List<InsurancePolicy>} if {@code policies} is non-empty.
     * If {@code policies} contain only one element which is an empty string, it will be parsed into a
     * {@code List<InsurancePolicy>} containing zero policies.
     */
    private Optional<List<InsurancePolicy>> parsePoliciesForEdit(Collection<String> policyIds) throws ParseException {
        assert policyIds != null;

        if (policyIds.isEmpty()) {
            return Optional.empty();
        }

        Collection<String> policyList = policyIds.size() == 1 && policyIds.contains("")
                ? Collections.emptySet()
                : policyIds;
        return Optional.of(ParserUtil.parsePolicies(policyList));
    }

    /**
     * Parses {@code Collection<String> policyId} into a {@code List<InsurancePolicy>} if {@code policies} is non-empty.
     * If {@code policies} contain only one element which is an empty string, it will be parsed into a
     * {@code List<InsurancePolicy>} containing zero policies.
     */
    private Optional<List<Meeting>> parseMeetingForEdit(Collection<String> meeting) throws ParseException {
        assert meeting != null;

        if (meeting.isEmpty()) {
            return Optional.empty();
        }

        Collection<String> policyList = meeting.size() == 1 && meeting.contains("")
                ? Collections.emptySet()
                : meeting;
        return Optional.of(ParserUtil.parseMeeting(policyList));
    }

}
