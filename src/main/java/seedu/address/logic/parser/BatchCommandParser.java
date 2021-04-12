package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSURANCE_POLICY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHORTCUT_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHORTCUT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.BatchCommand;
import seedu.address.logic.commands.BatchOperation;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Handles all parsing of {@code BatchCommand} as well as arguments for {@code BatchOperation}.
 */
public class BatchCommandParser implements Parser<BatchCommand<? extends BatchOperation>> {
    public static final String INVALID_BATCH_COMMAND = "Invalid batch operation!\nOnly edit and delete operations "
            + "are supported.";
    public static final String REPEATED_INDICES = "Input indices are repeated! Please ensure that all indices are "
            + "different.";
    public static final String INVALID_EDIT_ARGUMENTS = "Invalid arguments for edit command!\nOnly "
            + "phone numbers, addresses, tags and insurance policies can be edited in batch.";

    /**
     * Parses input to prepare for a {@code BatchCommand}, and the {@code BatchOperations} that will be executed by it.
     * First, parses and checks the validity of the {@code BatchOperation}.
     * Then, parses and checks the validity of the indices and arguments (if applicable) to be passed to the
     * {@code BatchOperation}.
     *
     * @param args arguments of the {@code BatchCommand} passed in by the user.
     * @return a {@code BatchCommand} with the {@code List} of {@code BatchOperations} to be executed.
     * @throws ParseException if the user input for {@code BatchCommand} or the {@code BatchOperations} does not
     *      conform to the expected format.
     */
    public BatchCommand<? extends BatchOperation> parse(String args) throws ParseException {
        String[] splitCommandAndIndicesAndArgs = args.trim().split(" ", 2);
        String inputCommand = splitCommandAndIndicesAndArgs[0].trim();
        if (inputCommand.length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchCommand.MESSAGE_USAGE));
        }

        // Checks the validity of the Command that the user passed as input to the BatchCommand
        switch (inputCommand) {
        case EditCommand.COMMAND_WORD:
            /* fall through */
        case DeleteCommand.COMMAND_WORD:
            break;
        default:
            throw new ParseException(INVALID_BATCH_COMMAND);
        }

        try {

            // Tokenizes and parses the user input
            String inputIndicesAndArgs = " " + splitCommandAndIndicesAndArgs[1].trim();
            ArgumentMultimap argMultimap = ArgumentTokenizer
                            .tokenize(inputIndicesAndArgs, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                            PREFIX_ADDRESS, PREFIX_TAG, PREFIX_INSURANCE_POLICY, PREFIX_MEETING,
                            PREFIX_SHORTCUT_COMMAND, PREFIX_SHORTCUT_NAME);

            boolean doIndicesContainWords = ParserUtil.checkIndicesInputContainsWords(argMultimap.getPreamble());

            if (doIndicesContainWords && inputCommand.equals(EditCommand.COMMAND_WORD)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        EditCommand.MESSAGE_USAGE_BATCH));
            } else if (doIndicesContainWords && inputCommand.equals(DeleteCommand.COMMAND_WORD)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteCommand.MESSAGE_USAGE_BATCH));
            }

            List<Index> listOfIndices = parseAndPrepareIndices(argMultimap);

            assert inputCommand.equals(EditCommand.COMMAND_WORD) || inputCommand.equals(DeleteCommand.COMMAND_WORD);

            if (inputCommand.equals(EditCommand.COMMAND_WORD)) {
                return batchEdit(argMultimap, listOfIndices);
            } else {
                return batchDelete(listOfIndices);
            }

        } catch (ParseException e) {
            throw new ParseException(String.format(BatchCommand.ERROR_MESSAGE, e.getLocalizedMessage()));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchCommand.MESSAGE_USAGE));
        }
    }

    private List<Index> parseAndPrepareIndices(ArgumentMultimap argMultimap) throws ParseException {
        String indices = argMultimap.getPreamble();
        if (indices.length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchCommand.MESSAGE_USAGE));
        }
        List<Index> listOfIndices = ParserUtil.parseIndices(indices.trim());

        // Reverse sort the list so that upon deletion of clients, the same index can still be used since we deleted
        // from the back. No side effect for EditCommands.
        Collections.sort(listOfIndices);
        Collections.reverse(listOfIndices);

        return listOfIndices;
    }

    private BatchCommand<EditCommand> batchEdit(ArgumentMultimap argMultimap, List<Index> listOfIndices)
            throws ParseException {
        if (areOtherPrefixesEntered(argMultimap)) {
            throw new ParseException(INVALID_EDIT_ARGUMENTS);
        }

        // Joins the rest of the input by user to be passed to the EditCommands
        List<String> listOfPhoneNumbers = argMultimap.getAllValues(PREFIX_PHONE);
        List<String> listOfAddresses = argMultimap.getAllValues(PREFIX_ADDRESS);
        List<String> listOfTags = argMultimap.getAllValues(PREFIX_TAG);
        List<String> listOfInsurancePolicies = argMultimap.getAllValues(PREFIX_INSURANCE_POLICY);
        String inputCommandArgs = concatAllArguments(listOfPhoneNumbers, listOfAddresses, listOfTags,
                listOfInsurancePolicies);

        List<EditCommand> listOfEditCommands = createEditCommands(listOfIndices, inputCommandArgs);

        return new BatchCommand<>(listOfEditCommands);
    }

    private List<EditCommand> createEditCommands(List<Index> listOfIndices, String inputCommandArgs)
            throws ParseException {
        EditCommandParser editCommandParser = new EditCommandParser();
        List<EditCommand> listOfEditCommands = new ArrayList<>();

        for (Index index : listOfIndices) {
            String newCommandInput = index.getOneBased() + " " + inputCommandArgs;
            EditCommand editCommand = editCommandParser.parse(newCommandInput);
            listOfEditCommands.add(editCommand);
        }

        return listOfEditCommands;
    }

    private BatchCommand<DeleteCommand> batchDelete(List<Index> listOfIndices) throws ParseException {
        List<DeleteCommand> listOfDeleteCommands = createDeleteCommands(listOfIndices);
        return new BatchCommand<>(listOfDeleteCommands);
    }

    private List<DeleteCommand> createDeleteCommands(List<Index> listOfIndices) throws ParseException {
        DeleteCommandParser deleteCommandParser = new DeleteCommandParser();
        List<DeleteCommand> listOfDeleteCommands = new ArrayList<>();

        for (Index index : listOfIndices) {
            String indexString = String.valueOf(index.getOneBased());
            DeleteCommand deleteCommand = deleteCommandParser.parse(indexString);
            listOfDeleteCommands.add(deleteCommand);
        }

        return listOfDeleteCommands;
    }

    private String concatAllArguments(List<String> listOfPhoneNumbers, List<String> listOfAddresses,
                                      List<String> listOfTags, List<String> listOfInsurancePolicies) {
        StringBuilder stringBuilder = new StringBuilder();

        appendWithPrefix(stringBuilder, PREFIX_PHONE, listOfPhoneNumbers);
        appendWithPrefix(stringBuilder, PREFIX_ADDRESS, listOfAddresses);
        appendWithPrefix(stringBuilder, PREFIX_TAG, listOfTags);
        appendWithPrefix(stringBuilder, PREFIX_INSURANCE_POLICY, listOfInsurancePolicies);

        return stringBuilder.toString().trim();
    }

    private void appendWithPrefix(StringBuilder stringBuilder, Prefix prefix, List<String> listOfInformation) {
        for (int i = 0; i < listOfInformation.size(); i++) {
            stringBuilder
                    .append(prefix)
                    .append(listOfInformation.get(i))
                    .append(" ");
        }
    }

    private boolean areOtherPrefixesEntered(ArgumentMultimap argMultimap) {
        return argMultimap.getValue(PREFIX_NAME).isPresent()
                || argMultimap.getValue(PREFIX_EMAIL).isPresent();
    }
}
