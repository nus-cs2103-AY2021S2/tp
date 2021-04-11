package seedu.cakecollate.logic.parser;

import static seedu.cakecollate.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_ORDER_DESCRIPTION;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_ORDER_ITEM_IDX;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import seedu.cakecollate.commons.core.index.IndexList;
import seedu.cakecollate.logic.commands.AddCommand;
import seedu.cakecollate.logic.parser.exceptions.ParseException;
import seedu.cakecollate.model.order.Address;
import seedu.cakecollate.model.order.DeliveryDate;
import seedu.cakecollate.model.order.Email;
import seedu.cakecollate.model.order.Name;
import seedu.cakecollate.model.order.OrderDescription;
import seedu.cakecollate.model.order.Phone;
import seedu.cakecollate.model.order.Request;
import seedu.cakecollate.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_ORDER_DESCRIPTION, PREFIX_ORDER_ITEM_IDX, PREFIX_TAG, PREFIX_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_DATE)
                || !isAnyPresent(argMultimap, PREFIX_ORDER_ITEM_IDX, PREFIX_ORDER_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        AddCommand.AddOrderDescriptor addOrderDescriptor = new AddCommand.AddOrderDescriptor();

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Map<OrderDescription, Integer> orderDescriptionSet =
                ParserUtil.parseOrderDescriptions(argMultimap.getAllValues(PREFIX_ORDER_DESCRIPTION));

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        DeliveryDate deliveryDate = ParserUtil.parseDeliveryDate(argMultimap.getValue(PREFIX_DATE).get());
        Request request = new Request(""); //request should be empty when addcommand is initialized.

        addOrderDescriptor.setName(name);
        addOrderDescriptor.setPhone(phone);
        addOrderDescriptor.setEmail(email);
        addOrderDescriptor.setAddress(address);
        addOrderDescriptor.setOrderDescriptions(orderDescriptionSet);
        addOrderDescriptor.setTags(tagList);
        addOrderDescriptor.setDeliveryDate(deliveryDate);
        addOrderDescriptor.setRequest(request);


        IndexList orderItemIndexList =
                argMultimap.getValue(PREFIX_ORDER_ITEM_IDX).isEmpty()
                        ? null
                        : ParserUtil.parseIndexList(argMultimap.getValue(PREFIX_ORDER_ITEM_IDX).get());

        return new AddCommand(orderItemIndexList, addOrderDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if any one of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isAnyPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
