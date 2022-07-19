package com.nusamandiri.monitoringgudang.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author galang
 */
public interface Last5DataPeminjamanDto {

    String getId();

    LocalDateTime getTanggal();

    String getNama();

    String getCode();

    String getStatus();
}
