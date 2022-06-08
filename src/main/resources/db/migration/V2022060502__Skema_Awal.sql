create table pegawai(
    id varchar(36),
    created_by varchar(255),
    updated_by varchar(255),
    updated timestamp,
    created timestamp,
    nipp varchar(100) not null,
    nama varchar(100) not null,
    jenis_kelamin varchar(10) not null,
    tanggal_lahir date,
    telepon varchar(15) not null,
    email varchar(30) not null,
    alamat varchar(100),
    primary key (id),
    unique (nipp, telepon, email)
);

create table kategori(
    id varchar(36),
    created_by varchar(255),
    updated_by varchar(255),
    updated timestamp,
    created timestamp,
    nama varchar(100) not null,
    deskripsi varchar(100),
    primary key (id),
    unique (nama)
);

create table barang(
    id varchar(36),
    created_by varchar(255),
    updated_by varchar(255),
    updated timestamp,
    created timestamp,
    code varchar(100) not null,
    nama varchar(100) not null,
    id_kategori varchar(36) not null,
    barcode varchar(255) not null,
    qty integer not null,
    primary key (id),
    unique (code, barcode)
);

create table barang_detail(
    id varchar(36),
    id_barang varchar(100) not null,
    deskripsi varchar(100),
    primary key (id)
);

ALTER TABLE  barang
    ADD CONSTRAINT fkco4sbxv9cj2oevm6cdpq76ffb FOREIGN KEY (id_kategori) REFERENCES kategori(id);

ALTER TABLE  barang_detail
    ADD CONSTRAINT fkco4sbxv9cj2oevm6cdpq76ffc FOREIGN KEY (id_barang) REFERENCES barang(id);