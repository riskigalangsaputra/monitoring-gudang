package com.nusamandiri.monitoringgudang.entity.security;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author galang
 */
@Entity
@Table(name = "c_security_user_password")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPassword {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_user", nullable = false, columnDefinition = "varchar(36)")
    private User user;

    @Column(nullable = false, name = "user_password")
    private String password;
}
