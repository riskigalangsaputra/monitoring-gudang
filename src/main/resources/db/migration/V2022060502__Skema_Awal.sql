create table karyawan(
    id varchar(36),
    updated timestamp,
    created timestamp,
    nipp varchar(100) not null,
    nama varchar(100) not null,
    jenis_kelamin varchar(10) not null,
    telepon varchar(15) not null,
    email varchar(30) not null,
    alamat varchar(100),
    primary key (id),
    unique (nipp, telepon, email)
);

create table kategori(
    id varchar(36),
    updated timestamp,
    created timestamp,
    nama varchar(100) not null,
    deskripsi varchar(100),
    primary key (id),
    unique (nama)
);

create table alat_kerja(
    id varchar(36),
    updated timestamp,
    created timestamp,
    code varchar(100) not null,
    nama varchar(100) not null,
    id_kategori varchar(36) not null,
    status varchar(15) not null,
    primary key (id),
    unique (code)
);

create table alat_kerja_detail(
    id varchar(36),
    id_alat_kerja varchar(100) not null,
    deskripsi varchar(100),
    primary key (id)
);

create table peminjaman (
        id varchar(36),
        id_karyawan varchar(100) not null,
        code varchar(50) not null,
        start_code varchar(50),
        end_code varchar(50),
        status_peminjaman varchar(15) not null,
        tanggal_peminjaman date,
        tanggal_pengembalian date,
        tanggal_jatuh_tempo date,
        waktu_pengambilan timestamp,
        created timestamp,
        updated timestamp,
        lewat_tanggal date,
        alasan_ditolak varchar(100),
        primary key (id),
        unique (code, start_code, end_code)
);

create table peminjaman_detail (
        id varchar(36),
        id_peminjaman varchar(100) not null,
        id_alat_kerja varchar(100) not null,
        primary key (id)
);

create table notifikasi (
        id varchar(36),
        updated timestamp,
        created timestamp,
        id_peminjaman varchar(100) not null,
        judul varchar(50) not null,
        pesan varchar(100) not null,
        primary key (id)
);

ALTER TABLE  alat_kerja
    ADD CONSTRAINT fkco4sbxv9cj2oevm6cdpq76ffb FOREIGN KEY (id_kategori) REFERENCES kategori(id);

ALTER TABLE  alat_kerja_detail
    ADD CONSTRAINT fkco4sbxv9cj2oevm6cdpq76ffc FOREIGN KEY (id_alat_kerja) REFERENCES alat_kerja(id);

ALTER TABLE  peminjaman
    ADD CONSTRAINT fkco4sbxv9cj2oevm6cdpq76mm9 FOREIGN KEY (id_karyawan) REFERENCES karyawan(id);

ALTER TABLE  peminjaman_detail
    ADD CONSTRAINT fkco4sbxv9cj2oevm6cdpq7mk8y FOREIGN KEY (id_peminjaman) REFERENCES peminjaman(id);

ALTER TABLE  peminjaman_detail
    ADD CONSTRAINT fkco4sbxv9cj2oevm6cdpq76ffa FOREIGN KEY (id_alat_kerja) REFERENCES alat_kerja(id);

ALTER TABLE  notifikasi
    ADD CONSTRAINT fkco4sbxv9cj2oevm6rdpq76ffa FOREIGN KEY (id_peminjaman) REFERENCES peminjaman(id);