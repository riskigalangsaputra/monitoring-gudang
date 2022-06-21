package com.nusamandiri.monitoringgudang;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        System.out.println(LocalDateTime.now());
    }
}
