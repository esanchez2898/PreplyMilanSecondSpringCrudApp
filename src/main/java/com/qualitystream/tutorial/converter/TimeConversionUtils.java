package com.qualitystream.tutorial.converter;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeConversionUtils {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final ZoneId zoneId = ZoneId.of("Mexico/General");


    public String timestampToString(Timestamp timestamp) {
        ZonedDateTime mexicoTime = timestamp.toLocalDateTime().atZone(zoneId);
        return mexicoTime.format(dateTimeFormatter);
    }

    public Timestamp stringToTimestamp(String str) {
        try {
            ZonedDateTime zonedDateTime = LocalDateTime.parse(str, dateTimeFormatter).atZone(zoneId);
            return Timestamp.valueOf(zonedDateTime.toLocalDateTime());

        } catch (DateTimeParseException e) {
            System.out.println("Error converting to Timestamp " + e.getMessage());
            return null;
        }
    }

    public Timestamp getMexicoTimestamp() {
        ZonedDateTime mexicoTime = Instant.now().atZone(zoneId);
        return Timestamp.valueOf(mexicoTime.toLocalDateTime());

    }




}
