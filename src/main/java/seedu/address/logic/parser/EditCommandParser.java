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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditCommand.EditPolicyMode;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.insurancepolicy.InsurancePolicy;
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
            throw new ParseException(pe.getLocalizedMessage() + "\n" + EditCommand.MESSAGE_USAGE, pe);
        }

        if (argMultimap.getValue(PREFIX_MEETING).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
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

        List<String> inputPolicies = argMultimap.getAllValues(PREFIX_INSURANCE_POLICY);

        EditPolicyMode editPolicyMode = getEditPolicyMode(inputPolicies);
        List<String> policiesTrimmed = getPolicyListFromInput(inputPolicies);
        parsePoliciesByEditMode(policiesTrimmed, editPolicyMode, editPersonDescriptor);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor, editPolicyMode);
    }

    private void parsePoliciesByEditMode(List<String> policies, EditPolicyMode editPolicyMode,
                                         EditPersonDescriptor editPersonDescriptor) throws ParseException {
        boolean isModeReplaceOrAppendPolicies = editPolicyMode == EditPolicyMode.REPLACE
                || editPolicyMode == EditPolicyMode.APPEND;
        boolean isModeRemovePolicies = editPolicyMode == EditPolicyMode.REMOVE;
        boolean isModeModifyPolicies = editPolicyMode == EditPolicyMode.MODIFY;

        if (isModeReplaceOrAppendPolicies) {
            parsePoliciesForEdit(policies).ifPresent(editPersonDescriptor::setPoliciesToAdd);
        } else if (isModeRemovePolicies) {
            parsePoliciesForEdit(policies).ifPresent(editPersonDescriptor::setPoliciesToRemove);
        } else if (isModeModifyPolicies) {
            List<List<String>> addAndRemovePairs = getPoliciesFromModifyPairs(policies);
            parsePoliciesForEdit(addAndRemovePairs.get(1)).ifPresent(editPersonDescriptor::setPoliciesToAdd);
            parsePoliciesForEdit(addAndRemovePairs.get(0)).ifPresent(editPersonDescriptor::setPoliciesToRemove);
        }
    }

    private List<List<String>> getPoliciesFromModifyPairs(List<String> modifyPairs) throws ParseException {
        List<String> policiesToAdd = new ArrayList<>();
        List<String> policiesToRemove = new ArrayList<>();
        for (String pair : modifyPairs) {
            String[] policies = pair.split(";");
            if (policies.length != 2 || (policies[0].isEmpty() || policies[1].isEmpty())) {
                throw new ParseException(EditCommand.MESSAGE_MODIFY_POLICY_CONSTRAINT);
            }
            policiesToRemove.add(policies[0]);
            policiesToAdd.add(policies[1]);
        }
        List<List<String>> removeAndAddPairs = new ArrayList<>();
        removeAndAddPairs.add(policiesToRemove);
        removeAndAddPairs.add(policiesToAdd);
        return removeAndAddPairs;
    }

    /**
     * Get policy edit mode from input policies.
     * @param inputPolicies policy input from user
     * @return EditPolicyMode
     * @throws ParseException if there is more than one flag
     */
    private EditPolicyMode getEditPolicyMode(List<String> inputPolicies) throws ParseException {
        // defensive
        if (!isNumOfFlagsValid(inputPolicies)) {
            throw new ParseException(EditPolicyMode.MESSAGE_EDIT_POLICY_MULTIPLE_FLAG_CONSTRAINTS);
        }

        String policyWithFlag = "";
        for (String policy : inputPolicies) {
            if (policy.contains("-")) {
                policyWithFlag = policy;
            }
        }

        if (policyWithFlag.isEmpty()) {
            // default when no flag is specified
            return EditPolicyMode.REPLACE;
        } else {
            String[] policyWithFlagSplit = policyWithFlag.split(" ");
            String flag = "";
            for (String string : policyWithFlagSplit) {
                if (string.contains("-")) {
                    flag = string;
                }
            }
            return ParserUtil.parseEditPolicyMode(flag);
        }
    }

    private static boolean isNumOfFlagsValid(List<String> inputPolicies) {
        int flagCount = 0;
        for (String policy : inputPolicies) {
            int flagNum = StringUtil.countMatches(policy, "-");
            flagCount += flagNum;
            if (flagCount > 1) {
                return false;
            }
        }
        return true;
    }

    private List<String> getPolicyListFromInput(List<String> inputStrings) {
        List<String> trimmedPolicies = new ArrayList<>();
        for (String string : inputStrings) {
            if (string.contains("-")) {
                trimmedPolicies.add(removeFlagFromPolicy(string));
            } else {
                trimmedPolicies.add(string);
            }
        }
        return trimmedPolicies;
    }

    private String removeFlagFromPolicy(String policyString) {
        String[] policyStringsSplit = policyString.split(" ");
        ArrayList<String> trimmedPolicyStringList = new ArrayList<>();
        for (String str : policyStringsSplit) {
            if (!str.contains("-")) {
                trimmedPolicyStringList.add(str);
            }
        }
        return String.join(" ", trimmedPolicyStringList);
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
    private Optional<List<InsurancePolicy>> parsePoliciesForEdit(Collection<String> policyIds) {
        assert policyIds != null;

        if (policyIds.isEmpty()) {
            return Optional.empty();
        }

        Collection<String> policyList = policyIds.size() == 1 && policyIds.contains("")
                ? Collections.emptySet()
                : policyIds;
        return Optional.of(new ArrayList<>(ParserUtil.parsePolicies(policyList)));
    }

}
