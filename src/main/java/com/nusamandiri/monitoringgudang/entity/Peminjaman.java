package com.nusamandiri.monitoringgudang.entity;

import com.nusamandiri.monitoringgudang.entity.security.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author User
 */
@Data @Entity
public class Peminjaman extends BaseEntity {


    @JoinColumn(name = "id_user")
    @ManyToOne
    private User user;

    private Integer qty;

    private LocalDateTime tglPinjam;

    private LocalDateTime tglKembali;

    private Integer duration;

    private String status;

    private String keterangan;

    @OneToMany(mappedBy = "peminjaman", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PeminjamanDetail> peminjamanDetails = new HashSet<>();
}
