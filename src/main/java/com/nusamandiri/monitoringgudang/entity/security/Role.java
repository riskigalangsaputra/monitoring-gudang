package com.nusamandiri.monitoringgudang.entity.security;

import com.nusamandiri.monitoringgudang.entity.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * @author galang
 */
@Entity
@Table(name = "c_security_role")
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @Size(max = 15)
    @NotEmpty(message = "Nama role tidak boleh kosong")
    @Column(name = "name", nullable = false, unique = true, length = 15)
    private String name;

    @Size(max = 100)
    @Column(name = "description", length = 100)
    private String description;

    @ManyToMany
    @OrderBy("permissionValue asc")
    @JoinTable(
            name="c_security_role_permission",
            joinColumns=@JoinColumn(name="id_role", nullable=false, columnDefinition = "VARCHAR(36)"),
            inverseJoinColumns=@JoinColumn(name="id_permission", nullable=false, columnDefinition = "VARCHAR(36)")
    )
    private Set<Permission> permissions = new HashSet<Permission>();

}
