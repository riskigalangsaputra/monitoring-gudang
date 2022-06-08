create table c_security_permission
(
    id               varchar(36)  not null,
    permission_label varchar(100) not null,
    permission_value varchar(100) not null,
    primary key (id)
) engine=InnoDB;

create table c_security_role
(
    id          varchar(36) not null,
    description varchar(100),
    name        varchar(15) not null,
    primary key (id)
) engine=InnoDB;

create table c_security_role_permission
(
    id_role       VARCHAR(36) not null,
    id_permission VARCHAR(36) not null,
    primary key (id_role, id_permission)
) engine=InnoDB;

create table c_security_user
(
    id        varchar(36)  not null,
    active    bit,
    username  varchar(100) not null,
    fullname  varchar(255) not null,
    id_role   varchar(36)  not null,
    primary key (id)
) engine=InnoDB;

create table c_security_user_password
(
    id_user       varchar(36)  not null,
    user_password varchar(255) not null,
    primary key (id_user)
) engine=InnoDB;

alter table c_security_permission
    add constraint UK_9gamvc5lx9mwkgi3wkaj5xuq3 unique (permission_label);

alter table c_security_permission
    add constraint UK_k4suda9cvcsoikdgquscypmt6 unique (permission_value);

alter table c_security_role
    add constraint UK_hliaoojt6u3a11d8svttju10l unique (name);

alter table c_security_user
    add constraint UK_at8if7a9lnl90wxllb9divpdf unique (username);


ALTER TABLE `c_security_role_permission`
    ADD CONSTRAINT `FKg9os4isbs19ssfahravousxes` FOREIGN KEY (`id_role`) REFERENCES `c_security_role` (`id`),
    ADD CONSTRAINT `FKnqcv2qdac1phe20qqnyi6n1n` FOREIGN KEY (`id_permission`) REFERENCES `c_security_permission` (`id`);

alter table c_security_user
    add constraint FKe5ychpyk27l8vj47v36mrn0s1
        foreign key (id_role)
            references c_security_role (id);

alter table c_security_user_password
    add constraint FK80arji7i1u0styufcy8b91it5
        foreign key (id_user)
            references c_security_user (id);


