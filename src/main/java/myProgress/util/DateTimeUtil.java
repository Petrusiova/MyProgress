package myProgress.util;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // DB doesn't support LocalDate.MIN/MAX
    private static final LocalDate MIN_DATE = LocalDate.of(1, 1, 1);
    private static final LocalDate MAX_DATE = LocalDate.of(3000, 1, 1);

//    public static LocalDateTime atStartOfDayOrMin(LocalDate localDate) {
//        return localDate != null ? localDate.atStartOfDay() : MIN_DATE;
//    }
//
//    public static LocalDateTime atStartOfNextDayOrMax(LocalDate localDate) {
//        return localDate != null ? localDate.plus(1, ChronoUnit.DAYS).atStartOfDay() : MAX_DATE;
//    }

//    public static String toString(LocalDateTime ldt) {
//        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
//    }

    public static @Nullable
    LocalDate parseLocalDate(@Nullable String str) {
        return StringUtils.isEmpty(str) ? null : LocalDate.parse(str);
    }

//    public static @Nullable
//    LocalTime parseLocalTime(@Nullable String str) {
//        return StringUtils.isEmpty(str) ? null : LocalTime.parse(str);
//    }
}
