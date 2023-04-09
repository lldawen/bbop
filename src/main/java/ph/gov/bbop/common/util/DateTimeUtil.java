package ph.gov.bbop.common.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public final class DateTimeUtil {

    private static final DateTimeFormatter DEFAULT_DATE_PARSER_PATTERN = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER_PATTERN = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);

    private DateTimeUtil() {}

    public static LocalDate parse(String dateTimeString) {
        return LocalDate.parse(dateTimeString, DEFAULT_DATE_PARSER_PATTERN);
    }

    public static String format(LocalDate localDate) {
        return DEFAULT_DATETIME_FORMATTER_PATTERN.format(localDate);
    }
}
