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
//@Entity
public class Pegawai extends BaseEntity{

    @NotNull @NotEmpty @Column(unique = true) @Size(min = 5, max = 10)
    private String nipp;

    @NotNull @NotEmpty @Size(min = 3 ,max = 100)
    private String nama;

    @NotNull @Enumerated(EnumType.STRING)
    private JenisKelamin jenisKelamin;

    @NotNull
    private LocalDate tanggalLahir;

    @NotNull @NotEmpty @Size(max = 15)
    private String telepon;

    @Email @Size(max = 30)
    private String email;

    @Size(max = 255)
    private String alamat;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public enum JenisKelamin {
        PRIA, WANITA
    }

}
