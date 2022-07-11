package com.nusamandiri.monitoringgudang.entity;

import com.nusamandiri.monitoringgudang.utils.Constant;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author galang
 */
@Data
@Entity
public class Peminjaman extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_karyawan")
    private Karyawan karyawan;

    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String code;

    private String startCode;
    private String endCode;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Constant.StatusPeminjaman statusPeminjaman;

    private LocalDate tanggalPeminjaman;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalPengembalian;

    private LocalDateTime waktuPengambilan;

    private LocalDate tanggalJatuhTempo;

    private String alasanDitolak;

    @OneToMany(mappedBy = "peminjaman", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PeminjamanDetail> peminjamanDetails = new ArrayList<>();

    @Valid
    @Transient
    private List<PeminjamanDetail> details = new ArrayList<>();

}
