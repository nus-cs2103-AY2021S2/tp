package seedu.budgetbaby.logic.parser;

import seedu.budgetbaby.ablogic.commands.EditCommand;
import seedu.budgetbaby.commons.core.index.Index;
import seedu.budgetbaby.logic.commands.EditFrCommand;
import seedu.budgetbaby.logic.commands.EditFrCommand.EditFrDescriptor;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;
import seedu.budgetbaby.model.record.Category;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.budgetbaby.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

/**
 * Parses input arguments and creates a new EditFrCommand object
 */
public class EditFrCommandParser implements BudgetBabyCommandParser<EditFrCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditFrCommand
     * and returns an EditFrCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditFrCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_AMOUNT, PREFIX_CATEGORY);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditFrCommand.MESSAGE_USAGE), pe);
        }

        EditFrDescriptor editedFrDescriptor = new EditFrDescriptor();
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editedFrDescriptor.setDescription(
                ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_AMOUNT).isPresent()) {
            editedFrDescriptor.setAmount(ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get()));
        }
        parseCategoriesForEdit(argMultimap.getAllValues(PREFIX_CATEGORY)).ifPresent(editedFrDescriptor::setCategories);

        if (!editedFrDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditFrCommand.MESSAGE_NOT_EDITED);
        }

        return new EditFrCommand(index, editedFrDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Category>} if {@code categories} is non-empty.
     * If {@code categories} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Category>} containing zero categories.
     */
    private Optional<Set<Category>> parseCategoriesForEdit(Collection<String> categories) throws ParseException {
        assert categories != null;

        if (categories.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet =
            categories.size() == 1 && categories.contains("") ? Collections.emptySet() : categories;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}

