package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSURANCE_POLICY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.BatchCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class BatchCommandParser implements Parser<BatchCommand> {
    public static final String INVALID_BATCH_COMMAND = "Invalid batch operation!\nOnly edit and delete operations "
            + "are supported.";
    public static final String INVALID_EDIT_ARGUMENTS = "Invalid arguments for edit command!\nOnly tags and "
            + "insurance policies can be edited in batch.";

    /**
     * Parses input to prepare for a {@code BatchCommand}. Splits user input arguments for the {@BatchCommand} into
     * the command to be executed in batch, as well as indices and arguments (if any) for this command.
     *
     * @param args arguments of the {@BatchCommand} passed in by the user
     * @return a {@code BatchCommand} with the corresponding command parser and arguments for the command.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public BatchCommand parse(String args) throws ParseException {
        try {
            String[] splitCommandAndIndicesAndArgs = args.trim().split(" ", 2);
            String inputCommand = splitCommandAndIndicesAndArgs[0].trim();

            switch (inputCommand) {

            case EditCommand.COMMAND_WORD:
            case DeleteCommand.COMMAND_WORD:
                break;

            default:
                throw new ParseException(INVALID_BATCH_COMMAND);
            }

            String inputIndicesAndArgs = splitCommandAndIndicesAndArgs[1].trim();

            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(inputIndicesAndArgs, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                            PREFIX_ADDRESS, PREFIX_TAG, PREFIX_INSURANCE_POLICY);

            List<Index> listOfIndices = parseIndices(argMultimap);
            Collections.sort(listOfIndices);
            Collections.reverse(listOfIndices);

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

    private List<Index> parseIndices(ArgumentMultimap argMultimap) throws ParseException {
        String indices = argMultimap.getPreamble();
        return ParserUtil.parseIndices(indices.trim());
    }

    private BatchCommand<EditCommand> batchEdit(ArgumentMultimap argMultimap, List<Index> listOfIndices)
            throws ParseException {
        if (areOtherPrefixesEntered(argMultimap)) {
            throw new ParseException(INVALID_EDIT_ARGUMENTS);
        }

        List<String> listOfTags = argMultimap.getAllValues(PREFIX_TAG);
        List<String> listOfInsurancePolicies = argMultimap.getAllValues(PREFIX_INSURANCE_POLICY);
        String inputCommandArgs = concatAllArguments(listOfTags, listOfInsurancePolicies);

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

    private String concatAllArguments(List<String> listOfTags, List<String> listOfInsurancePolicies) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < listOfTags.size(); i++) {
            stringBuilder
                    .append(PREFIX_TAG.getPrefix())
                    .append(listOfTags.get(i))
                    .append(" ");
        }
        for (int i = 0; i < listOfInsurancePolicies.size(); i++) {
            stringBuilder
                    .append(PREFIX_INSURANCE_POLICY.getPrefix())
                    .append(listOfInsurancePolicies.get(i))
                    .append(" ");
        }

        return stringBuilder.toString().trim();
    }

    private boolean areOtherPrefixesEntered(ArgumentMultimap argMultimap) {
        return argMultimap.getValue(PREFIX_NAME).isPresent() || argMultimap.getValue(PREFIX_PHONE).isPresent()
                || argMultimap.getValue(PREFIX_EMAIL).isPresent() || argMultimap.getValue(PREFIX_ADDRESS).isPresent();
    }
}
