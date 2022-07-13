package com.nusamandiri.monitoringgudang.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author galang
 */
@Data @Entity
public class PeminjamanDetail {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_peminjaman")
    private Peminjaman peminjaman;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_alatKerja")
    private AlatKerja alatKerja;

}
