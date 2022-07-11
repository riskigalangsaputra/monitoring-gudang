package com.nusamandiri.monitoringgudang.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @author galang
 */
@Data
public class OnGoingDto {

    private String id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalJatuhTempo;

    private String startCode;
}
