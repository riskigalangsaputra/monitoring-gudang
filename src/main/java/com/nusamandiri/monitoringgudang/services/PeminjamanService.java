package com.nusamandiri.monitoringgudang.services;

import com.nusamandiri.monitoringgudang.dao.*;
import com.nusamandiri.monitoringgudang.dto.*;
import com.nusamandiri.monitoringgudang.entity.Karyawan;
import com.nusamandiri.monitoringgudang.entity.Notifikasi;
import com.nusamandiri.monitoringgudang.entity.Peminjaman;
import com.nusamandiri.monitoringgudang.entity.PeminjamanDetail;
import com.nusamandiri.monitoringgudang.entity.security.User;
import com.nusamandiri.monitoringgudang.specification.SpecificationBuilder;
import com.nusamandiri.monitoringgudang.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author galang
 */
@Slf4j
@Service
@Transient
public class PeminjamanService {

    @Autowired
    private PeminjamanDao peminjamanDao;

    @Autowired
    private PeminjamanDetailDao peminjamanDetailDao;

    @Autowired
    private KaryawanDao karyawanDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AlatKerjaDao alatKerjaDao;

    @Autowired
    private NotifikasiDao notifikasiDao;

    public List<GrafikDto> getDataGrafikGroupByStatus() {
        return peminjamanDao.countPeminjaman();
    }

    public long countStatusPeminjaman() {
        return peminjamanDao.countByStatusPeminjaman(Constant.StatusPeminjaman.WAITING);
    }

    public List<Last5DataPeminjamanDto> findByLast5Data() {
        return peminjamanDao.findByLast5Data();
    }

    public Page<Peminjaman> getPeminjamanPage(CommonSearchDto params, Pageable pageable, String username) {

        Page<Peminjaman> data = null;
        User user = userDao.findByUsername(username).orElse(null);
        SpecificationBuilder<Peminjaman> builder = new SpecificationBuilder<>();
        if (StringUtils.hasText(params.getValue())) builder.with("code", "like", params.getValue());
        if ("SUPER_ADMIN".equals(user.getRole().getName())) {
            data = peminjamanDao.findAll(builder.build(), pageable);
        } else if ("PEGAWAI".equals(user.getRole().getName())) {
            if (StringUtils.hasText(user.getUsername())) builder.with("karyawan-email", "equal", user.getUsername());
            data = peminjamanDao.findAll(builder.build(), pageable);
        }
        return data;
    }

    public void createPeminjaman(PeminjamanDto dto, String username) throws Exception {
        Peminjaman peminjaman = new Peminjaman();
        Karyawan karyawan = karyawanDao.findByEmail(username).orElse(null);

        SpecificationBuilder<Peminjaman> builder = new SpecificationBuilder<>();
        if (StringUtils.hasText(username)) builder.with("karyawan-email", "equal", username);
        for (Peminjaman p : peminjamanDao.findAll(builder.build())) {
            if (Constant.StatusPeminjaman.WAITING.equals(p.getStatusPeminjaman()) ||
                    Constant.StatusPeminjaman.APPROVED.equals(p.getStatusPeminjaman())) {
                throw new Exception("Tidak dapat melakukan permohonan !");
            }
        }

        try {
            peminjaman.setKaryawan(karyawan);
            peminjaman.setCode("TRX-" + getRandomNumberString());
            peminjaman.setStatusPeminjaman(Constant.StatusPeminjaman.WAITING);
            peminjamanDao.save(peminjaman);

            List<PeminjamanDetail> peminjamanDetails = new ArrayList<>();

            for (PeminjamanDetail detail : dto.getDetails()) {
                PeminjamanDetail peminjamanDetail = new PeminjamanDetail();
                peminjamanDetail.setPeminjaman(peminjaman);
                peminjamanDetail.setAlatKerja(detail.getAlatKerja());
                peminjamanDetails.add(peminjamanDetail);
            }
            peminjamanDetailDao.saveAll(peminjamanDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void approve(String id) throws Exception {
        try {
            Peminjaman peminjaman = peminjamanDao.findById(id).orElse(null);
            if (peminjaman != null) {
                peminjaman.setStatusPeminjaman(Constant.StatusPeminjaman.APPROVED);
                peminjaman.setStartCode(getRandomNumberString());
                peminjaman.setWaktuPengambilan(LocalDateTime.now().plusMinutes(45));
                peminjamanDao.save(peminjaman);

                peminjaman.getPeminjamanDetails().forEach(detail -> {
                    detail.getAlatKerja().setStatus(Constant.StatusAlatKerja.USED);
                    alatKerjaDao.save(detail.getAlatKerja());
                });

                Notifikasi notifikasi = new Notifikasi();
                notifikasi.setPeminjaman(peminjaman);
                notifikasi.setJudul("Permohonan telah di setujui");
                notifikasi.setPesan("Segera ke gudang sebelum 1 jam dari sekarang !");
                notifikasiDao.save(notifikasi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rejected(TolakDto dto) {
        Peminjaman peminjaman = peminjamanDao.findById(dto.getId()).orElse(null);
        if (peminjaman != null) {
            peminjaman.setStatusPeminjaman(Constant.StatusPeminjaman.REJECTED);
            peminjaman.setAlasanDitolak(dto.getAlasanPenolakan());
            peminjamanDao.save(peminjaman);

            Notifikasi notifikasi = new Notifikasi();
            notifikasi.setPeminjaman(peminjaman);
            notifikasi.setJudul("Permohonan di tolak");
            notifikasi.setPesan(peminjaman.getAlasanDitolak());
            notifikasiDao.save(notifikasi);
        }
    }

    public void onGoingProsess(OnGoingDto dto) throws Exception {
        log.info("==== ID : {}",dto.getId());
        log.info("==== START CODE : {}",dto.getStartCode());
        Peminjaman p = peminjamanDao.findByStartCodeAndId(dto.getStartCode(), dto.getId()).orElse(null);
        if (p == null) {
            throw new Exception("Start Code salah !");
        }
        LocalDate today = LocalDate.now();
        if (today.compareTo(dto.getTanggalJatuhTempo()) > 0 || today.compareTo(dto.getTanggalJatuhTempo()) == 0) {
            throw new Exception("Tanggal minimal lebih satu hari dari tanggal sekarang !");
        }

        try {
            p.setTanggalJatuhTempo(dto.getTanggalJatuhTempo());
            p.setTanggalPeminjaman(LocalDate.now());
            p.setStatusPeminjaman(Constant.StatusPeminjaman.ON_GOING);
            p.setEndCode(getRandomNumberString());
            peminjamanDao.save(p);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doneProsess(DoneDto dto) throws Exception {
        Peminjaman p = peminjamanDao.findByEndCodeAndId(dto.getEndCode(), dto.getId()).orElse(null);
        if (p == null) {
            throw new Exception("End Code salah !");
        }
        try {
            p.setTanggalPengembalian(LocalDate.now());
            p.setStatusPeminjaman(Constant.StatusPeminjaman.DONE);
            peminjamanDao.save(p);

            for (PeminjamanDetail detail : p.getPeminjamanDetails()) {
                detail.getAlatKerja().setStatus(Constant.StatusAlatKerja.NOT_USED);
                alatKerjaDao.save(detail.getAlatKerja());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "5 * * * * *", zone = "Asia/Jakarta") // berjalan tiap 5 menit
    private void scheduleSetExpired() {
        log.info("<<<< START JOB EXPIRED >>>>");
        try {
            SpecificationBuilder<Peminjaman> builder = new SpecificationBuilder<>();
            if (StringUtils.hasText(Constant.StatusPeminjaman.APPROVED.toString())) builder.with("statusPeminjaman", "equal", Constant.StatusPeminjaman.APPROVED);
            peminjamanDao.findAll(builder.build()).forEach(p -> {
                if (LocalDateTime.now().compareTo(p.getWaktuPengambilan()) > 0) {
                    p.setStatusPeminjaman(Constant.StatusPeminjaman.EXPIRED);
                    peminjamanDao.save(p);

                    for (PeminjamanDetail detail : p.getPeminjamanDetails()) {
                        detail.getAlatKerja().setStatus(Constant.StatusAlatKerja.NOT_USED);
                        alatKerjaDao.save(detail.getAlatKerja());
                    }

                    Notifikasi notifikasi = new Notifikasi();
                    notifikasi.setPeminjaman(p);
                    notifikasi.setJudul("Stataus Peminjaman");
                    notifikasi.setPesan("Waktu pengambilan barang telah lewat");
                    notifikasiDao.save(notifikasi);
                }
            });
            log.info("<<<< END JOB EXPIRED >>>>");
        } catch (Exception e) {
            log.info("<<<< ERROR : {}", e.getMessage());
            e.printStackTrace();
        }
    }

    public OnGoingDto setOnGoingDto(String id) {
        OnGoingDto onGoingDto = new OnGoingDto();
        Peminjaman peminjaman = getPeminjamanById(id);
        onGoingDto.setId(peminjaman.getId());
        return onGoingDto;
    }

    public DoneDto setDoneDto(String id) {
        DoneDto doneDto = new DoneDto();
        Peminjaman peminjaman = getPeminjamanById(id);
        doneDto.setId(peminjaman.getId());
        return doneDto;
    }

    public TolakDto setTolakDto(String id) {
        TolakDto tolakDto = new TolakDto();
        Peminjaman peminjaman = getPeminjamanById(id);
        tolakDto.setId(peminjaman.getId());
        return tolakDto;
    }

    public Peminjaman getPeminjamanById(String id) {
        return peminjamanDao.findById(id).orElse(null);
    }

    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
}
