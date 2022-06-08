package com.nusamandiri.monitoringgudang.entity.security;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author galang
 */
@Entity
@Table(name = "c_security_permission")
@Data
public class Permission {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @Size(max = 100)
    @NotEmpty(message = "label tidak boleh kosong")
    @Column(name = "permission_label", nullable = false, unique = true, length = 100)
    private String permissionLabel;

    @Size(max = 100)
    @NotEmpty(message = "value tidak boleh kosong")
    @Column(name = "permission_value", nullable = false, unique = true, length = 100)
    private String permissionValue;

}