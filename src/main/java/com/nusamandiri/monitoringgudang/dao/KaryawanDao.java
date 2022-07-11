package com.nusamandiri.monitoringgudang.dao;

import com.nusamandiri.monitoringgudang.entity.Karyawan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author galang
 */
public interface KaryawanDao extends JpaRepository<Karyawan, String> {

    Optional<Karyawan> findByEmail(String email);
}
