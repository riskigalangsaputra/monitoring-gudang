package com.nusamandiri.monitoringgudang.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * @author galang
 */
@Data @Entity
public class Notifikasi extends BaseEntity{

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_peminjaman")
    private Peminjaman peminjaman;

    private String judul;

    private String pesan;
}
