package seedu.taskify.logic.commands.util;

import static seedu.taskify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskify.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static seedu.taskify.commons.util.StringUtil.reduceWhitespaces;
import static seedu.taskify.model.task.Status.isValidStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import seedu.taskify.commons.core.index.Index;
import seedu.taskify.commons.util.StringUtil;
import seedu.taskify.logic.commands.DeleteCommand;
import seedu.taskify.logic.commands.exceptions.CommandException;
import seedu.taskify.logic.parser.exceptions.ParseException;
import seedu.taskify.model.Model;
import seedu.taskify.model.task.Status;
import seedu.taskify.model.task.Task;

/**
 * Utility class for deleting multiple tasks at once
 */
public class DeleteUtil {


    public static final String MESSAGE_PARSE_MULTIPLE_INDEX_ON_SINGLE_INDEX = "The string passed to ParserUtil"
            + ".parseMultipleIndex() contains only one argument";
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_INDEX_RANGE = "Invalid index range given. Second index should be "
            + "bigger than the first index.";
    public static final String MESSAGE_INVALID_TASK_FOR_INDEX_RANGE = "The given index range includes indices that do"
            + " not correspond to a task";
    public static final String MESSAGE_DELETE_BY_STATUS_USAGE = DeleteCommand.COMMAND_WORD + ": Delete all tasks of a"
            + " specified Status.\n" + "Parameters: STATUS_STRING (in lower caps)\n"
            + "Note: \"-all\" must be added after the specified status\n"
            + "Example: " + DeleteCommand.COMMAND_WORD + " completed -all";
    public static final String MESSAGE_NO_TASKS_OF_GIVEN_STATUS = "There are no tasks with the given status!";


    /**
     * Checks if {@code argumentInput} contains more than one valid index and if all are valid indexes.
     * @param argumentInput user's input excluding the command word
     * @return false if {@code argumentInput} contains only one index, that is valid. Note that the range "x-x" where
     * x is a non zero unsigned integer will throw a ParseException instead
     * @throws ParseException if {@code argumentInput} cannot be parsed properly later on (See {@code
     * extractStringArgumentsIntoIndexes(String input)}).
     */
    public static boolean hasMultipleValidIndex(String argumentInput) throws ParseException {
        String[] arguments = extractStringArgumentsIntoIndexes(argumentInput);
        boolean hasOnlyOneArgument = arguments.length == 1;

        return !hasOnlyOneArgument;
    }

    /**
     * Checks if user is trying to delete tasks using an index range
     * @param input user's input other than the command word
     * @return true if the user is deleting using an index range
     */
    public static boolean isDeletingTasksByRange(String input) {
        input = reduceWhitespaces(input);
        String regex = "^(?<firstNum>[0-9]+)-(?<secondNum>[0-9]+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.find();
    }

    /**
     * Checks if user is trying to delete all tasks of a specific {@code Status}, from the user's input
     * @param argumentInput user's input other than the command word
     * @throws ParseException if the user did not enter the status correctly.
     */
    public static boolean isDeletingTasksByStatus(String argumentInput) throws ParseException {
        argumentInput = reduceWhitespaces(argumentInput);
        String regex = "[a-zA-Z]* -all";
        boolean isTrying = argumentInput.matches(regex);

        if (!isTrying) {
            if (argumentInput.contains("all")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_DELETE_BY_STATUS_USAGE));
            }
            return false;
        }

        int endIndex = argumentInput.indexOf(" -all");
        String statusArg = argumentInput.substring(0, endIndex);

        if (!isValidStatus(statusArg)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_DELETE_BY_STATUS_USAGE));
        }

        return true;
    }

    /**
     * Extracts a String array of indexes if the input follows the format for multiple indexes correctly.
     *
     * @param input String to extract from
     * @return a String array containing the valid or invalid indexes
     * @throws ParseException if an invalid index range was given i.e "2-2" or "100-99", or if an index has leading
     * zeroes i.e "0-1" or "01-09", or if the arguments are incomprehensible.
     */
    public static String[] extractStringArgumentsIntoIndexes(String input) throws ParseException {
        input = reduceWhitespaces(input);

        String regex = "^(?<firstNum>[0-9]+)-(?<secondNum>[0-9]+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        boolean hasFoundIndexRange = matcher.find();

        if (!hasFoundIndexRange) {
            String[] arguments = input.split(" ");
            for (String argument : arguments) {
                if (!StringUtil.isNonZeroUnsignedInteger(argument)) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            DeleteCommand.MESSAGE_USAGE));
                }
            }
            return arguments;
        }

        String first = matcher.group("firstNum");
        String second = matcher.group("secondNum");

        return extractIndexesAsStringFromRange(first, second);
    }



    /**
     * Helper method for #{@code extractStringArgumentsIntoIndexes}
     */
    private static String[] extractIndexesAsStringFromRange(String lowerBound, String upperBound)
            throws ParseException {
        String leadingZeroesRegex = "0+[1-9]*";
        boolean isFirstIndexInvalid = lowerBound.matches(leadingZeroesRegex);
        boolean isSecondIndexInvalid = upperBound.matches(leadingZeroesRegex);

        if (isFirstIndexInvalid || isSecondIndexInvalid) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        int firstNum = Integer.parseInt(lowerBound);
        int secondNum = Integer.parseInt(upperBound);
        boolean isRangeInvalid = firstNum >= secondNum;

        if (isRangeInvalid) {
            throw new ParseException(MESSAGE_INVALID_INDEX_RANGE);
        }

        return IntStream.rangeClosed(firstNum, secondNum).mapToObj(String::valueOf).toArray(String[]::new);
    }

    /**
     * Returns the list of tasks to delete from {@code tasksSource} using indexes from {@code targetIndexes}
     * @param tasksSource the list of tasks that contains the tasks to be deleted
     * @param targetIndexes the indices of the tasks to delete
     * @param isDeletingByRange true if user was deleting using an index range, false if user listed the individual
     *                          indices
     * @return the list of tasks to be deleted
     * @throws CommandException
     */
    public static List<Task> getTasksToDelete(List<Task> tasksSource, List<Index> targetIndexes,
                                              boolean isDeletingByRange) throws CommandException {
        List<Task> tasksToDelete = new ArrayList<>();

        // Checks if the index range does not correspond to at least one task in Taskify
        boolean isIndexReferringToInvalidTask =
                targetIndexes.get(targetIndexes.size() - 1).getOneBased() > tasksSource.size();

        if (isDeletingByRange && isIndexReferringToInvalidTask) {
            throw new CommandException(MESSAGE_INVALID_TASK_FOR_INDEX_RANGE);
        }

        for (Index targetIndex : targetIndexes) {
            if (targetIndex.getZeroBased() >= tasksSource.size()) {
                throw new CommandException(MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
            }

            Task taskToDelete = tasksSource.get(targetIndex.getZeroBased());
            tasksToDelete.add(taskToDelete);
        }
        return tasksToDelete;
    }

    /**
     * Returns all tasks with the specified {@code Status}, that are to be deleted
     */
    public static List<Task> getTasksToDelete(Status status, List<Task> tasksSource) {
        return tasksSource.stream().filter(task -> task.getStatus().equals(status)).collect(Collectors.toList());
    }

    /**
     * Generates the success message when multiple tasks are deleted, by any of the 3 methods to delete multiple tasks
     */
    public static String generateSuccessMessage(List<Task> tasksToDelete) {
        StringBuilder sb = new StringBuilder();
        sb.append("Deleted Tasks: \n");
        for (Task toDelete : tasksToDelete) {
            sb.append(toDelete);
            sb.append("\n\n");
        }
        return sb.toString();
    }

    /**
     * Deletes tasks from a {@link Model}
     * @param model the {@code Model} to delete the tasks from
     * @param tasksToDelete the tasks to delete
     */
    public static void deleteTasksFromModel(Model model, List<Task> tasksToDelete) {
        for (Task toDelete : tasksToDelete) {
            model.deleteTask(toDelete);
        }
    }
}
