package me.whiteship.java8to11.datatime;

import javax.xml.crypto.Data;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class App1 {
    public static void main(String[] args) {
        /**
         * 기계용 시간 API
         */
        System.out.println("======기계용 API======");
        Instant instant = Instant.now();
        System.out.println(instant); //기준시 UTC, GMT
        System.out.println(instant.atZone(ZoneId.of("UTC")));
        System.out.println(instant.atZone(ZoneId.of("Asia/Seoul")));

        ZoneId zone = ZoneId.systemDefault();
        System.out.println(zone);
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        System.out.println(zonedDateTime);
        System.out.println();

        /**
         * 휴먼용 시간 API
         */
        System.out.println("======휴먼용 API======");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        LocalDateTime of = LocalDateTime.of(2000, Month.AUGUST, 21, 20, 10, 30);
        System.out.println(of);

        ZonedDateTime nowInKorea = zonedDateTime.now(ZoneId.of("Asia/Seoul")); //특정 지역 시간을 확인 할 때
        System.out.println(nowInKorea);
        zonedDateTime.toInstant(); // ZonedDateTime 에서 Instant로 변경 가능 (서로 변경이 가능)

        /**
         * 기간 API
         */
        System.out.println("======기간용 API======");
        LocalDate today = LocalDate.now();
        LocalDate thisYearDay = LocalDate.of(2000, Month.MARCH, 10);
        Period period = Period.between(today, thisYearDay); // 남은 날짜를 계산
        System.out.println(period.getDays());

        Period until = today.until(thisYearDay);
        System.out.println(until.get(ChronoUnit.DAYS));

        //기계용 기간
        Instant now1 = Instant.now();
        Instant plus = now1.plus(10, ChronoUnit.SECONDS);
        Duration between = Duration.between(now1, plus);
        System.out.println(between.getSeconds());

        //휴먼용 기간

        Date date = new Date();
        Instant instant1 = date.toInstant();
        Date newDate = Date.from(instant1);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        LocalDateTime dateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println(dateTime);
        ZonedDateTime dateTime1 = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault());
        GregorianCalendar from = GregorianCalendar.from(dateTime1);
        System.out.println(from.getTime());

        ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId(); //레거시 API 에서 최근 API 로 변경
        TimeZone timeZone = TimeZone.getTimeZone(zoneId);// 최근 API 에서 레거시 API 로 변경

        LocalDateTime now3 = LocalDateTime.now();
        LocalDateTime plus1 = now3.plus(10, ChronoUnit.DAYS);

        LocalDateTime now2 = LocalDateTime.now();
        DateTimeFormatter mMddyy = DateTimeFormatter.ofPattern("MM/dd/yyy");
        System.out.println(now2.format(mMddyy));

        LocalDate parse = LocalDate.parse("07/15/2000", mMddyy);
        System.out.println(parse);


    }
}
