package com.nusamandiri.monitoringgudang.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author galang
 */
@Entity
@Table(name = "c_security_user")
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @NotEmpty
    private String fullname;

    @NotNull
    @NotEmpty
    @Column(unique = true)
    @EqualsAndHashCode.Include
    @Size(max = 100)
    private String username;

    @Transient
    @JsonIgnore
    private String password;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    @JsonIgnore
    @OneToOne(mappedBy = "user", optional = true)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private UserPassword userPassword;

    @NotNull
    private Boolean active = false;
}
