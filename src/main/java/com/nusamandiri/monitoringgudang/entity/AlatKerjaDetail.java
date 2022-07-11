package com.nusamandiri.monitoringgudang.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author galang
 */
@Data @Entity
public class AlatKerjaDetail {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_alat_kerja")
    private AlatKerja alatKerja;

    @NotNull @NotEmpty @Size(max = 100)
    private String deskripsi;
}
