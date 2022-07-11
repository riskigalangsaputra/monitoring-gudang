package com.nusamandiri.monitoringgudang.entity;

import com.nusamandiri.monitoringgudang.utils.Constant;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * @author galang
 */
@Data @Entity
public class AlatKerja extends BaseEntity {

    @NotNull @NotEmpty @Column(unique = true)
    private String code;

    @NotNull @NotEmpty @Size(max = 100)
    private String nama;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_kategori")
    private Kategori kategori;

    @Enumerated(EnumType.STRING)
    private Constant.StatusAlatKerja status;

    @OneToMany(mappedBy = "alatKerja", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlatKerjaDetail> alatKerjaDetails = new ArrayList<>();

    @Valid
    @Transient
    private List<AlatKerjaDetail> details = new ArrayList<>();

}
