package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javafx.util.Pair;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.appointment.AddAppointmentCommand;
import seedu.address.logic.commands.appointment.ClearAppointmentCommand;
import seedu.address.logic.commands.appointment.DeleteAppointmentCommand;
import seedu.address.logic.commands.appointment.EditAppointmentCommand;
import seedu.address.logic.commands.appointment.FindAppointmentCommand;
import seedu.address.logic.commands.appointment.ListAppointmentCommand;
import seedu.address.logic.commands.doctor.AddDoctorCommand;
import seedu.address.logic.commands.doctor.ClearDoctorCommand;
import seedu.address.logic.commands.doctor.DeleteDoctorCommand;
import seedu.address.logic.commands.doctor.EditDoctorCommand;
import seedu.address.logic.commands.doctor.FindDoctorCommand;
import seedu.address.logic.commands.doctor.ListDoctorCommand;
import seedu.address.logic.commands.patient.AddPatientCommand;
import seedu.address.logic.commands.patient.ClearPatientCommand;
import seedu.address.logic.commands.patient.DeletePatientCommand;
import seedu.address.logic.commands.patient.EditPatientCommand;
import seedu.address.logic.commands.patient.ListPatientCommand;
import seedu.address.logic.parser.appointment.AddAppointmentCommandParser;
import seedu.address.logic.parser.appointment.DeleteAppointmentCommandParser;
import seedu.address.logic.parser.appointment.EditAppointmentCommandParser;
import seedu.address.logic.parser.appointment.FindAppointmentCommandParser;
import seedu.address.logic.parser.doctor.AddDoctorCommandParser;
import seedu.address.logic.parser.doctor.DeleteDoctorCommandParser;
import seedu.address.logic.parser.doctor.EditDoctorCommandParser;
import seedu.address.logic.parser.doctor.FindDoctorCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.patient.AddPatientCommandParser;
import seedu.address.logic.parser.patient.DeletePatientCommandParser;
import seedu.address.logic.parser.patient.EditPatientCommandParser;
import seedu.address.logic.parser.patient.FindPatientCommand;
import seedu.address.logic.parser.patient.FindPatientCommandParser;
import seedu.address.storage.InputCommandStorage;

/**
 * Parses user input.
 */
public class UserInputParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final String CLOSEST_COMMAND_MESSAGE = "Invalid command! Perhaps you meant: \n\"%s\"?";

    /**
     * Used for storing user input in its raw form.
     */
    private final InputCommandStorage inputCommandStorage = new InputCommandStorage();

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform to the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        //store userInput in inputCommandStorage
        inputCommandStorage.addInput(userInput);

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (CommandWord.fromString(commandWord)) {
        // Appointment related commands
        case ADD_APPOINTMENT:
            return new AddAppointmentCommandParser().parse(arguments);

        case CLEAR_APPOINTMENT:
            return new ClearAppointmentCommand();

        case DELETE_APPOINTMENT:
            return new DeleteAppointmentCommandParser().parse(arguments);

        case EDIT_APPOINTMENT:
            return new EditAppointmentCommandParser().parse(arguments);

        case FIND_APPOINTMENT:
            return new FindAppointmentCommandParser().parse(arguments);

        case LIST_APPOINTMENT:
            return new ListAppointmentCommand();


        // Patient related commands
        case ADD_PATIENT:
            return new AddPatientCommandParser().parse(arguments);

        case CLEAR_PATIENT:
            return new ClearPatientCommand();

        case DELETE_PATIENT:
            return new DeletePatientCommandParser().parse(arguments);

        case EDIT_PATIENT:
            return new EditPatientCommandParser().parse(arguments);

        case FIND_PATIENT:
            return new FindPatientCommandParser().parse(arguments);

        case LIST_PATIENT:
            return new ListPatientCommand();


        // Doctor related commands
        case ADD_DOCTOR:
            return new AddDoctorCommandParser().parse(arguments);

        case CLEAR_DOCTOR:
            return new ClearDoctorCommand();

        case DELETE_DOCTOR:
            return new DeleteDoctorCommandParser().parse(arguments);

        case EDIT_DOCTOR:
            return new EditDoctorCommandParser().parse(arguments);

        case FIND_DOCTOR:
            return new FindDoctorCommandParser().parse(arguments);

        case LIST_DOCTOR:
            return new ListDoctorCommand();

        // Common Commands
        case EXIT:
            return new ExitCommand();

        case HELP:
            return new HelpCommand();

        default:
            throw handleUnknownCommand(commandWord);
        }
    }

    /**
     * Wrapper method to handle an unknown command and returns a relevant ParseException.
     *
     * @param commandWord the command word which is not in {@link CommandWord#values()}
     * @return {@code ParseException} with the relevant message
     */
    public ParseException handleUnknownCommand(String commandWord) {
        String closestCommand = MinEditDistance.getClosestCommand(commandWord);
        if (closestCommand.equals("")) {
            return new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
        return new ParseException(String.format(CLOSEST_COMMAND_MESSAGE, closestCommand));
    }

    private static class MinEditDistance {

        private static final int MAX_EDIT_DISTANCE = 10;

        /**
         * Compares the {@code testCommand} with the string representation of all {@code CommandWord}s
         * in {@link CommandWord#values()} and returns the closest {@code CommandWord} if the
         * minimum edit distance between the known {@code CommandWord} String and {@code testCommand}
         * is less than or equal to {@code MAX_EDIT_DISTANCE}.
         */
        public static String getClosestCommand(String testCommand) {
            return Stream.of(CommandWord.values())
                    .map(cw -> getMinEditDistance(cw.getCommandWord().toLowerCase(), testCommand.toLowerCase()))
                    .min(new Comparator<Pair<String, Integer>>() {
                        public int compare(Pair<String, Integer> pair1, Pair<String, Integer> pair2) {
                            if (pair1.getValue() == pair2.getValue()) {
                                return 0;
                            } else if (pair1.getValue() > pair2.getValue()) {
                                return 1;
                            } else {
                                return -1;
                            }
                        }
                    })
                    .filter(pair -> pair.getValue() <= MAX_EDIT_DISTANCE)
                    .map(pair -> pair.getKey()).orElse("");
        }

        /**
         * Calculates the minimum edit distance between 2 strings.
         *
         * @param knownCommand String that represents the known command String
         * @param unknownCommand String that represents the unknown command String
         * @return {@code Pair<String, Integer>}, a pair containing the known
         * command string and its corresponding minimum edit distance
         */
        private static Pair<String, Integer> getMinEditDistance(String knownCommand, String unknownCommand) {
            // terminate early if the minimum edit distance is confirmed to be more than MAX_EDIT_DISTANCE
            if (Math.abs(knownCommand.length() - unknownCommand.length()) > MAX_EDIT_DISTANCE) {
                return new Pair<>(knownCommand, MAX_EDIT_DISTANCE + 1);
            }

            // @@author onnwards-reused
            // minimum edit distance algorithm reused from
            // https://www.geeksforgeeks.org/java-program-to-implement-levenshtein-distance-computing-algorithm/
            // with minor modifications
            int[][] minEditDistArr = new int[knownCommand.length() + 1][unknownCommand.length() + 1];
            for (int i = 0; i <= knownCommand.length(); i++) {
                for (int j = 0; j <= unknownCommand.length(); j++) {
                    if (i == 0) {
                        minEditDistArr[i][j] = j;
                    } else if (j == 0) {
                        minEditDistArr[i][j] = i;
                    } else {
                        minEditDistArr[i][j] = Stream.of(
                            minEditDistArr[i - 1][j] + 1,
                            minEditDistArr[i][j - 1] + 1,
                            minEditDistArr[i - 1][j - 1]
                                    + (knownCommand.charAt(i - 1) == unknownCommand.charAt(j - 1) ? 0 : 1)
                        )
                        .reduce(Integer.MAX_VALUE, Integer::min);
                    }
                }
            }
            return new Pair<>(knownCommand, minEditDistArr[knownCommand.length()][unknownCommand.length()]);
        }
    }

    enum CommandWord {
        // Appointment related commands
        ADD_APPOINTMENT(AddAppointmentCommand.COMMAND_WORD),
        CLEAR_APPOINTMENT(ClearAppointmentCommand.COMMAND_WORD),
        DELETE_APPOINTMENT(DeleteAppointmentCommand.COMMAND_WORD),
        EDIT_APPOINTMENT(EditAppointmentCommand.COMMAND_WORD),
        FIND_APPOINTMENT(FindAppointmentCommand.COMMAND_WORD),
        LIST_APPOINTMENT(ListAppointmentCommand.COMMAND_WORD),

        // Patient related commands
        ADD_PATIENT(AddPatientCommand.COMMAND_WORD),
        CLEAR_PATIENT(ClearPatientCommand.COMMAND_WORD),
        DELETE_PATIENT(DeletePatientCommand.COMMAND_WORD),
        EDIT_PATIENT(EditPatientCommand.COMMAND_WORD),
        FIND_PATIENT(FindPatientCommand.COMMAND_WORD),
        LIST_PATIENT(ListPatientCommand.COMMAND_WORD),

        // Patient related commands
        ADD_DOCTOR(AddDoctorCommand.COMMAND_WORD),
        CLEAR_DOCTOR(ClearDoctorCommand.COMMAND_WORD),
        DELETE_DOCTOR(DeleteDoctorCommand.COMMAND_WORD),
        EDIT_DOCTOR(EditDoctorCommand.COMMAND_WORD),
        FIND_DOCTOR(FindDoctorCommand.COMMAND_WORD),
        LIST_DOCTOR(ListDoctorCommand.COMMAND_WORD),

        // Common Commands
        EXIT(ExitCommand.COMMAND_WORD),
        HELP(HelpCommand.COMMAND_WORD),
        UNKNOWN("");

        private final String commandWord;

        private CommandWord(String commandWord) {
            this.commandWord = commandWord;
        }

        public String getCommandWord() {
            return commandWord;
        }

        public static CommandWord fromString(String commandWordString) {
            return Stream.of(CommandWord.values())
                    .filter(cw -> cw.commandWord.equals(commandWordString))
                    .findFirst()
                    .orElse(CommandWord.UNKNOWN);
        }
    }
}
