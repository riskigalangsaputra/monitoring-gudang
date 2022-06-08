INSERT INTO c_security_permission (id, permission_label, permission_value)
VALUES ('1_1_view_master', 'Melihat data master', 'VIEW_DATA_MASTER'),
       ('1_2_edit_master', 'Edit data master', 'EDIT_DATA_MASTER'),
       ('2_1_view_peminjaman', 'Melihat data peminjaman', 'VIEW_DATA_PEMINJAMAN'),
       ('2_2_edit_peminjaman', 'Edit data peminjaman', 'EDIT_DATA_PEMINJAMAN'),
       ('3_1_view_user', 'Melihat data user', 'VIEW_DATA_USER'),
       ('3_2_edit_user', 'Edit data user', 'EDIT_DATA_USER');


INSERT INTO c_security_role (id, description, name)
VALUES ('role_for_sadmin', 'Super Admin', 'SUPER_ADMIN'),
       ('role_for_admin', 'Admin', 'ADMINISTRATOR'),
       ('role_for_pegawai', 'Pegawai', 'PEGAWAI');

INSERT INTO c_security_role_permission (id_role, id_permission)
VALUES ('role_for_sadmin', '1_1_view_master'),
       ('role_for_sadmin', '1_2_edit_master'),
       ('role_for_sadmin', '2_1_view_peminjaman'),
       ('role_for_sadmin', '2_2_edit_peminjaman'),
       ('role_for_sadmin', '3_1_view_user'),
       ('role_for_sadmin', '3_2_edit_user');

INSERT INTO c_security_user (id, username, fullname, active, id_role)
VALUES ('sadmin', 'super.admin@yopmail.com', 'Super Admin Gudang', true, 'role_for_sadmin');

INSERT INTO c_security_user_password (id_user, user_password)
VALUES ('sadmin', '$2b$10$jr7IQOt65ljFl3g9fdggxeUwLujqWBTFciTiuUzM8zJSJYXovM4wG');
