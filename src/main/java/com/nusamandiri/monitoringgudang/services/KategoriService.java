package com.nusamandiri.monitoringgudang.services;

import com.nusamandiri.monitoringgudang.dao.KategoriDao;
import com.nusamandiri.monitoringgudang.dto.CommonSearchDto;
import com.nusamandiri.monitoringgudang.entity.Kategori;
import com.nusamandiri.monitoringgudang.helper.ValidateHelper;
import com.nusamandiri.monitoringgudang.specification.SpecificationBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;

/**
 * @author galang
 */
@Slf4j
@Service
public class KategoriService {

    @Autowired
    private KategoriDao kategoriDao;

    public Page<Kategori> getKategoriPage(CommonSearchDto params, Pageable pageable) {
        SpecificationBuilder<Kategori> builder = new SpecificationBuilder<>();
        if(StringUtils.hasText(params.getValue())) builder.with("nama","like", params.getValue());
        return kategoriDao.findAll(builder.build(),pageable);
    }

    public Optional<Kategori> findById(String id) {
        if (StringUtils.hasText(id)) {
            return kategoriDao.findById(id);
        }
        return Optional.empty();
    }

    public Iterable<Kategori> getKategoriIterable() {
        return kategoriDao.findAll();
    }

    public void validateKategori(Kategori kategori, BindingResult bindingResult) {
        Kategori k = kategoriDao.findByNamaIgnoreCase(kategori.getNama());
        if (!ValidateHelper.validateEntity(k, kategori.getId())) {
            bindingResult.addError(new FieldError("kategori", "nama", "Nama kategori sudah digunakan"));
        }
    }

    public void save(Kategori kategori) throws Exception {
        try {
            kategoriDao.save(kategori);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Gagal menyimpan");
        }
    }

    public void delete(String id) throws Exception {
        try {
            kategoriDao.deleteById(id);
        }catch (Exception e){
            log.error("ERROR DELETING KATEGORI ========================================= [{}]",e);
            throw new Exception("Gagal menghapus data : "+e.getMessage(),e);
        }
    }
}
