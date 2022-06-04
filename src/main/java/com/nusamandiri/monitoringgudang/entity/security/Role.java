package com.nusamandiri.monitoringgudang.entity.security;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author galang
 */
@Data
@Entity
@Table(name = "s_roles")
public class Role {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @NotEmpty @Column(unique = true)
    private String name;

}
