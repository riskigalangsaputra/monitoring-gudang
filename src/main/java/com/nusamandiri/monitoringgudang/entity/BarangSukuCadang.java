package com.nusamandiri.monitoringgudang.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Noverry Ambo
 */
@Data @Entity
public class BarangSukuCadang extends BaseEntity {

    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String code;

    @NotNull @NotEmpty @Size(max = 100)
    private String nama;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_kategori")
    private Kategori kategori;

    @NotNull @NotEmpty
    private String barcode;

    @NotNull
    private Integer qty;

    @OneToMany(mappedBy = "barang", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BarangDetail> barangDetails = new HashSet<>();
}
