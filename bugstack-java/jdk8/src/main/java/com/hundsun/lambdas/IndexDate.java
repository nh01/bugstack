package com.hundsun.lambdas;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

/**
 * Date API
 */
public class IndexDate {



    public static void test01() {
        // clock 提供对当前日期和时间的访问。
        // 我们可以利用它来替代 System.currentTimeMillis() 方法。
        // 另外，通过 clock.instant() 能够获取一个 instant 实例， 此实例能够方便地转换成老版本中的 java.util.Date 对象。
        Clock clock = Clock.systemDefaultZone();
        long mills = clock.millis();
        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);

        // Timezones时区
        // zoneId代表时区
        System.out.println(ZoneId.getAvailableZoneIds());

        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");

        // localtime
        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);
        System.out.println(now1.isBefore(now2));

        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minBetween = ChronoUnit.MINUTES.between(now1, now2);

        //
        LocalTime late = LocalTime.of(23,59,59);
        System.out.println(late); // 23:59:59
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.CHINA);
        LocalTime localTime = LocalTime.parse("13:23", formatter);

        // localDate
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);
        LocalDate independenceDay = LocalDate.of(2014, Month.APRIL, 4);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale.CHINA);
        LocalDate xmas = LocalDate.parse("24.12.2014", dateTimeFormatter);
        System.out.println(xmas); // 2014-12-24

    }


}
