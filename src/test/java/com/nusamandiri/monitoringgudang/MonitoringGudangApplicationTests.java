package com.nusamandiri.monitoringgudang;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest
class MonitoringGudangApplicationTests {

//    @Test
//    void contextLoads(SavePullRecond savePullRecond) {
//
////        String startDateInputan = savePullRecond.getStartDate();
////        String endDateInputan = savePullRecond.getEndDate();
//
//        String startDateInputan = "2022-08-21";
//        String endDateInputan = "2022-08-22";
//
//        String startDateMerge = startDateInputan + "00:00:00";
//        String endDateMerge = endDateInputan + "23:59:00";
//
//        LocalDateTime startDate = LocalDateTime.parse(startDateMerge);
//        LocalDateTime endDate = LocalDateTime.parse(endDateMerge);
//
//        System.out.println(startDate); // -> 2022-08-21
//
//
//
//}

    @Test
    void cobaa() {
        LocalDate today = LocalDate.now();
        LocalDate pastDate = LocalDate.parse("2022-07-12");

        if (today.compareTo(pastDate) > 0) {
            System.out.println("JATUH TEMPO MINIMAL SATU HARI DARI SEKARANG");
        }

        if (today.compareTo(pastDate) == 0) {
            System.out.println("SAMA");
        }
    }
}
