package com.nusamandiri.monitoringgudang.dao;

import com.nusamandiri.monitoringgudang.entity.Peminjaman;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author galang
 */
public interface PeminjamanDao extends JpaRepository<Peminjaman, String>, JpaSpecificationExecutor<Peminjaman> {

    Page<Peminjaman> findByKaryawanEmail(String email, Pageable pageable);

    Optional<Peminjaman> findByStartCodeAndId(String startCode, String id);
    Optional<Peminjaman> findByEndCodeAndId(String endCode, String id);
}
