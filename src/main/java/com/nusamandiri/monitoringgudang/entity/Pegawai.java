package com.nusamandiri.monitoringgudang.entity;

import com.nusamandiri.monitoringgudang.entity.security.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * @author galang
 */
@Data
@Entity
public class Pegawai extends BaseEntity{

    @NotNull @NotEmpty @Column(unique = true) @Size(min = 5, max = 10)
    private String nipp;

    @NotNull @NotEmpty @Size(min = 3 ,max = 100)
    private String nama;

    @NotNull @Enumerated(EnumType.STRING)
    private JenisKelamin jenisKelamin;

    private LocalDate tanggalLahir;

    @NotNull @NotEmpty @Size(max = 15) @Column(unique = true)
    private String telepon;

    @NotNull @NotEmpty @Email @Size(max = 30) @Column(unique = true)
    private String email;

    @Size(max = 255)
    private String alamat;

    public enum JenisKelamin {
        PRIA, WANITA
    }

}
