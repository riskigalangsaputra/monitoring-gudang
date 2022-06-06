package com.nusamandiri.monitoringgudang.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Noverry Ambo
 */

@Data @Entity
public class PeminjamanDetail {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @JoinColumn
    @ManyToOne
    private Peminjaman peminjaman;

    @NotNull
    @JoinColumn(name = "id_barang")
    @ManyToOne
    private Barang barang;

}
