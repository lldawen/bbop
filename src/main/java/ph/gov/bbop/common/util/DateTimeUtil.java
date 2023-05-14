package ph.gov.bbop.common.util;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateTimeUtil {

    private static final DateTimeFormatter DEFAULT_DATE_PATTERN = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FILENAME_DATE_PATTERN = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter DEFAULT_DATETIME_PATTERN = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private DateTimeUtil() {}

    public static LocalDate parse(String dateString) {
        if (StringUtils.isNotEmpty(dateString)) {
            return LocalDate.parse(dateString, DEFAULT_DATE_PATTERN);
        }
        return null;
    }

    public static String format(LocalDate localDate) {
        if (localDate != null) {
            return localDate.format(DEFAULT_DATE_PATTERN);
        }
        return StringUtils.EMPTY;
    }

    public static String formatForFilename(LocalDate localDate) {
        if (localDate != null) {
            return localDate.format(FILENAME_DATE_PATTERN);
        }
        return StringUtils.EMPTY;
    }

    public static LocalDateTime parseWithTime(String dateTimeString) {
        if (StringUtils.isNotEmpty(dateTimeString)) {
            return LocalDateTime.parse(dateTimeString, DEFAULT_DATETIME_PATTERN);
        }
        return null;
    }

    public static String formatWithTime(LocalDateTime localDateTime) {
        if (localDateTime != null) {
            return localDateTime.format(DEFAULT_DATETIME_PATTERN);
        }
        return StringUtils.EMPTY;
    }
}
