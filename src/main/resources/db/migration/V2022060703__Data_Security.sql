INSERT INTO c_security_permission (id, permission_label, permission_value)
VALUES ('1_1_view_master', 'Melihat data master', 'ROLE_VIEW_MASTER'),
       ('1_2_edit_master', 'Edit data master', 'ROLE_EDIT_MASTER'),
       ('2_1_view_peminjaman_admin', 'Melihat data peminjaman admin', 'ROLE_VIEW_PEMINJAMAN_ADMIN'),
       ('2_2_edit_peminjaman_admin', 'Edit data peminjaman admin', 'ROLE_EDIT_PEMINJAMAN_ADMIN'),
       ('3_1_view_user', 'Melihat data user', 'ROLE_VIEW_USER'),
       ('3_2_edit_user', 'Edit data user', 'ROLE_EDIT_USER'),
       ('4_1_view_peminjaman_pegawai', 'Melihat data peminjaman pegawai', 'ROLE_VIEW_PEMINJAMAN_PEGAWAI'),
       ('4_2_edit_peminjaman_pegawai', 'Edit data peminjaman pegawai', 'ROLE_EDIT_PEMINJAMAN_PEGAWAI'),
       ('5_1_view_peminjaman_admin', 'Melihat data laporan admin', 'ROLE_VIEW_LAPORAN_ADMIN'),
       ('5_2_edit_peminjaman_admin', 'Edit data laporan admin', 'ROLE_EDIT_LAPORAN_ADMIN');


INSERT INTO c_security_role (id, description, name)
VALUES ('role_for_sadmin', 'Super Admin', 'SUPER_ADMIN'),
       ('role_for_admin', 'Admin', 'ADMINISTRATOR'),
       ('role_for_pegawai', 'Pegawai', 'PEGAWAI');

INSERT INTO c_security_role_permission (id_role, id_permission)
VALUES ('role_for_sadmin', '1_1_view_master'),
       ('role_for_sadmin', '1_2_edit_master'),
       ('role_for_sadmin', '2_1_view_peminjaman_admin'),
       ('role_for_sadmin', '2_2_edit_peminjaman_admin'),
       ('role_for_sadmin', '3_1_view_user'),
       ('role_for_sadmin', '3_2_edit_user'),
       ('role_for_sadmin', '5_1_view_peminjaman_admin'),
       ('role_for_sadmin', '5_2_edit_peminjaman_admin'),
       ('role_for_pegawai', '4_1_view_peminjaman_pegawai'),
       ('role_for_pegawai', '4_2_edit_peminjaman_pegawai');

INSERT INTO c_security_user (id, username, fullname, active, id_role)
VALUES ('sadmin', 'super.admin@yopmail.com', 'Super Admin Gudang', true, 'role_for_sadmin'),
       ('u-01', 'galang@yopmail.com', 'Riski Galang', true, 'role_for_pegawai'),
       ('u-02', 'noka@yopmail.com', 'Noka Setiadi', true, 'role_for_pegawai');

INSERT INTO c_security_user_password (id_user, user_password)
VALUES ('sadmin', '$2b$10$jr7IQOt65ljFl3g9fdggxeUwLujqWBTFciTiuUzM8zJSJYXovM4wG'),
       ('u-01', '$2b$10$jr7IQOt65ljFl3g9fdggxeUwLujqWBTFciTiuUzM8zJSJYXovM4wG'),
       ('u-02', '$2b$10$jr7IQOt65ljFl3g9fdggxeUwLujqWBTFciTiuUzM8zJSJYXovM4wG');

INSERT INTO karyawan (id, updated, created, nipp, nama, jenis_kelamin, telepon, email, alamat)
VALUES ('k-01', null, null, '12345', 'Riski Galang', 'PRIA', '0811918123', 'galang@yopmail.com', 'jakarta'),
       ('k-02', null, null, '12346', 'Noka Setiadi', 'PRIA', '0918292832', 'noka@yopmail.com', 'Banyuwangi');

