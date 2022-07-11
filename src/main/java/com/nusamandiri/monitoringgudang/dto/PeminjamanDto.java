package com.nusamandiri.monitoringgudang.dto;

import com.nusamandiri.monitoringgudang.entity.PeminjamanDetail;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author galang
 */
@Data
public class PeminjamanDto {
    private List<PeminjamanDetail> details = new ArrayList<>();
}
