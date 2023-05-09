package util;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;



/** This class is used to convert the time to UTC. It is used to enter the UTC time into the database as a Timestamp. */
public class TimeZoneUtil {

    /**
     * This method takes input and converts it to UTC then a Timestamp.
     *
     * @param date     date input
     * @param timeHour hour input
     * @param time     minutes input
     * @return startSqlTs returns the time converted to UTC as a Timestamp.
     */
    public static Timestamp getStartUTC(LocalDate date, LocalTime timeHour, LocalTime time) throws SQLException {
        LocalDateTime startDateAndTime = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(),
                timeHour.getHour(), timeHour.getMinute(), time.getSecond());

        ZoneId zid = ZoneId.systemDefault();

        ZonedDateTime zonedStart = startDateAndTime.atZone(zid);
        ZonedDateTime utcStart = zonedStart.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime thisLDTStart = utcStart.toLocalDateTime();

        String formatUTCStart = thisLDTStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Timestamp startSqlTS = Timestamp.valueOf(formatUTCStart);

        return startSqlTS;
    }
}



















