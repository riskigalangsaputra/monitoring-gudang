INSERT INTO kategori (id, updated, created, nama, deskripsi)
VALUES ('k-001', null, null, 'Bor', 'Alat bor'),
       ('k-002', null, null, 'Obeng', ''),
       ('k-003', null, null, 'Solder', '');

INSERT INTO alat_kerja (id, updated, created, code, nama, id_kategori, status)
VALUES ('a-001', null, null, 'ALT-001', 'Boshop 68Vf Mesin Bor', 'K-001', 'NOT_USED'),
       ('a-002', null, null, 'ALT-002', 'Obeng Bolak Balik', 'K-002', 'NOT_USED'),
       ('a-003', null, null, 'ALT-003', 'Solder Station Analog', 'K-003', 'NOT_USED');

INSERT INTO alat_kerja_detail (id, id_alat_kerja, deskripsi)
VALUES ('ad-001', 'a-001', 'No Load Speed: 0-2800r/min'),
       ('ad-002', 'a-001', 'Protection Class: II'),
       ('ad-003', 'a-002', 'FLAT 75mmx5mm'),
       ('ad-004', 'a-002', 'terbuat dari lapisan besi vanadium dengan pegangan anti slip'),
       ('ad-005', 'a-003', 'Power consumption:60W'),
       ('ad-006', 'a-003', 'Soldering Iron Temperature range:200-480');