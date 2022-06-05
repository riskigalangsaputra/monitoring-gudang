package com.nusamandiri.monitoringgudang.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author galang
 */
@Data @Entity
public class Kategori extends BaseEntity {

    @NotNull @NotEmpty @Size(min = 3, max = 100) @Column(unique = true)
    private String nama;

    @Size(max = 100)
    private String deskripsi;
}
