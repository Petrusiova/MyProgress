package myProgress.util;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DateUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // DB doesn't support LocalDate.MIN/MAX
    private static final LocalDate MIN_DATE = LocalDate.of(1, 1, 1);
    private static final LocalDate MAX_DATE = LocalDate.of(3000, 1, 1);

    public static LocalDate currentDateOrMin(LocalDate localDate) {
        return localDate != null ? localDate : MIN_DATE;
    }

    public static LocalDate currentDateOrMax(LocalDate localDate) {
        return localDate != null ? localDate : MAX_DATE;
    }

    public static String toString(LocalDate ld) {
        return ld == null ? "" : ld.format(DATE_TIME_FORMATTER);
    }

    public static @Nullable
    LocalDate parseLocalDate(@Nullable String str) {
        return StringUtils.isEmpty(str) ? null : LocalDate.parse(str);
    }
}
