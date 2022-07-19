package com.nusamandiri.monitoringgudang.dao;

import com.nusamandiri.monitoringgudang.dto.GrafikDto;
import com.nusamandiri.monitoringgudang.dto.Last5DataPeminjamanDto;
import com.nusamandiri.monitoringgudang.entity.Peminjaman;
import com.nusamandiri.monitoringgudang.utils.Constant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author galang
 */
public interface PeminjamanDao extends JpaRepository<Peminjaman, String>, JpaSpecificationExecutor<Peminjaman> {

    Page<Peminjaman> findByKaryawanEmail(String email, Pageable pageable);

    Optional<Peminjaman> findByStartCodeAndId(String startCode, String id);
    Optional<Peminjaman> findByEndCodeAndId(String endCode, String id);

    @Query(value = "SELECT COUNT(*) as total, status_peminjaman as status from peminjaman group by status_peminjaman", nativeQuery = true)
    List<GrafikDto> countPeminjaman();

    long countByStatusPeminjaman(Constant.StatusPeminjaman status);

    @Query(value = "select p.id, p.created as tanggal,  k.nama, p.code, p.status_peminjaman as status from peminjaman p inner join karyawan k on p.id_karyawan = k.id order by p.created ASC LIMIT 5", nativeQuery = true)
    List<Last5DataPeminjamanDto> findByLast5Data();


}
