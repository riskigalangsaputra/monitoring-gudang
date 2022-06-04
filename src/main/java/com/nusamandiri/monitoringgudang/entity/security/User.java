package com.nusamandiri.monitoringgudang.entity.security;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author galang
 */
@Data @Entity(name = "s_users")
public class User {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    @NotNull @NotEmpty
    private String username;

    @NotNull
    private Boolean active = false;
}
