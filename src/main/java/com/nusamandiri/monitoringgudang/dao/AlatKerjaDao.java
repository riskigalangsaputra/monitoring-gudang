package com.nusamandiri.monitoringgudang.dao;

import com.nusamandiri.monitoringgudang.entity.AlatKerja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author galang
 */
public interface AlatKerjaDao extends JpaRepository<AlatKerja, String>, JpaSpecificationExecutor<AlatKerja> {
    AlatKerja findByCode(String code);

    List<AlatKerja> findByIdIn(List<String> id);
}
