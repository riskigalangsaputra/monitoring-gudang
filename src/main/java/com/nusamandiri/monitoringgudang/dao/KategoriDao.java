package com.nusamandiri.monitoringgudang.dao;

import com.nusamandiri.monitoringgudang.entity.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author galang
 */
public interface KategoriDao extends JpaRepository<Kategori, String>, JpaSpecificationExecutor<Kategori> {

    Kategori findByNamaIgnoreCase(String nama);
}
