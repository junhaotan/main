package seedu.address.logic.parser.statistics;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.statistics.PlotProductSalesCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.transaction.DateTime;

/**
 * Parses input arguments and creates a new PlotProductSalesCommand object
 */
public class PlotProductSalesCommandParser implements Parser<PlotProductSalesCommand> {
    private static final int DEFAULT_LENGTH = 7;
    private static final DateTime DEFAULT_START_DATE =
            new DateTime(DateTime.DEFAULT_VALUE.minusDays(DEFAULT_LENGTH));

    /**
     * Parses the given {@code String} of arguments in the context of the PlotProductSalesCommand
     * and returns a PlotProductSalesCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PlotProductSalesCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_START_DATE, PREFIX_END_DATE);
        Index index;

        try {
            index = ParserUtil.parseIndex(args);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PlotProductSalesCommand.MESSAGE_USAGE), pe);
        }

        DateTime startDateTime;
        if (argMultimap.getValue(PREFIX_START_DATE).isPresent()) {
            startDateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_START_DATE).get());
        } else {
            startDateTime = DEFAULT_START_DATE;
        }

        DateTime endDateTime;
        if (argMultimap.getValue(PREFIX_END_DATE).isPresent()) {
            endDateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_END_DATE).get());
        } else {
            endDateTime = new DateTime(startDateTime.value.plusDays(DEFAULT_LENGTH));
        }

        return new PlotProductSalesCommand(index, startDateTime, endDateTime);
    }

}

