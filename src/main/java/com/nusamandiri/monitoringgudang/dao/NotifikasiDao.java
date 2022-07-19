package com.nusamandiri.monitoringgudang.dao;

import com.nusamandiri.monitoringgudang.dto.NotifikasiDto;
import com.nusamandiri.monitoringgudang.entity.Notifikasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author galang
 */
public interface NotifikasiDao extends JpaRepository<Notifikasi, String> {

//    @Query(value = "select n.pesan, n.judul from notifikasi n inner join peminjaman p on n.id_peminjaman = p.id where p.karyawan.email = :username order by n.created desc limit 6", nativeQuery = true)
//    List<NotifikasiDto> findAllOrderByDesc(@Param("username") String username);
}
