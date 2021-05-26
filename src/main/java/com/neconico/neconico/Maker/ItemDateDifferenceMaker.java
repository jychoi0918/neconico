package com.neconico.neconico.Maker;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemDateDifferenceMaker {

    public static String between(LocalDateTime localDateTime) {
        long betweenSeconds = ChronoUnit.SECONDS.between(localDateTime, LocalDateTime.now());
        long betweenMinutes = ChronoUnit.MINUTES.between(localDateTime, LocalDateTime.now());
        long betweenHours = ChronoUnit.HOURS.between(localDateTime, LocalDateTime.now());
        long betweenDays = ChronoUnit.DAYS.between(localDateTime, LocalDateTime.now());

        if(betweenSeconds < 60) {
            return Long.toString(betweenSeconds) + "초 전";
        }
        if(betweenMinutes < 60) {
            return Long.toString(betweenMinutes) + "분 전";
        }
        if(betweenHours < 24) {
            return Long.toString(betweenHours) + "시간 전";
        }
        return Long.toString(betweenDays) + "일 전";
    }
}
