package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class QueryFoodIntakeCommand extends Command {

    public static final String COMMAND_WORD = "food_intake_query";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Query details of food intake based on dates.\n"
            + "There are 2 kinds of query method. You can either choose to query for a certain date or for a range of"
            + " date, both inclusive.\n"
            + "1. Query based on a date. Command usage: food_intake_query d/DATE(in d MMM yyyy format)\n"
            + "2. Query based on a range of dates.\n"
            + "Command usage: food_intake_query df/DATE(in d MMM yyyy format) dt/DATE(in d MMM yyyy format)\n";

    public static final String MESSAGE_INVALID_SYNTAX = "You have entered an invalid syntax. Only d/ or df/ and dt/"
            + " argument can be used.";

    public static final String MESSAGE_INVALID_DATE_USAGE = "You have entered an invalid date sequence. Date from (/df)"
            + " must be before Date to (/dt).";

    public static final String MESSAGE_EMPTY_DATES = "You have not entered any dates. Here is a guide on how to use"
            + "the query feature: \n" + MESSAGE_USAGE;

    private final LocalDate date;
    private final LocalDate dateTo;
    private final LocalDate dateFrom;

    /**
     * Instantiates a QueryFoodIntakeCommand instance with the date(s) input.
     *
     * @param date     a particular date
     * @param dateFrom range from this pate
     * @param dateTo   range to this date
     */
    public QueryFoodIntakeCommand(LocalDate date, LocalDate dateFrom, LocalDate dateTo) {
        this.date = date;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String output;
        if (this.date != null) {
            if (this.dateTo == null && this.dateFrom == null) {
                output = model.getFoodIntakeList().getFoodIntakeListByDate(date);
            } else {
                throw new CommandException(MESSAGE_INVALID_SYNTAX);
            }
        } else if (this.dateFrom.isBefore(this.dateTo)) {
            output = model.getFoodIntakeList().getFoodIntakeListByDateRange(dateFrom, dateTo);
        } else if (this.dateFrom.isAfter(this.dateTo) || this.dateFrom.isEqual(this.dateTo)) {
            throw new CommandException(MESSAGE_INVALID_DATE_USAGE);
        } else {
            throw new CommandException(MESSAGE_EMPTY_DATES);
        }
        return new CommandResult(output);
    }
}
