package com.nusamandiri.monitoringgudang.services;

import com.nusamandiri.monitoringgudang.dao.AlatKerjaDao;
import com.nusamandiri.monitoringgudang.dto.CommonSearchDto;
import com.nusamandiri.monitoringgudang.entity.AlatKerja;
import com.nusamandiri.monitoringgudang.helper.ValidateHelper;
import com.nusamandiri.monitoringgudang.specification.SpecificationBuilder;
import com.nusamandiri.monitoringgudang.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Optional;

/**
 * @author galang
 */
@Service
@Slf4j
public class AlatKerjaService {

    @Autowired
    private AlatKerjaDao alatKerjaDao;

    public Page<AlatKerja> getAlatKerjaPage(CommonSearchDto params, Pageable pageable) {
        SpecificationBuilder<AlatKerja> builder = new SpecificationBuilder<>();
        if (StringUtils.hasText(params.getValue())) builder.with("code/nama", "like", params.getValue());
        if(StringUtils.hasText(params.getType())) builder.with("kategori-id","equal", params.getType());
        if(StringUtils.hasText(Constant.StatusAlatKerja.NOT_USED.toString())) builder.with("status","equal", Constant.StatusAlatKerja.NOT_USED);
        return alatKerjaDao.findAll(builder.build(), pageable);
    }

    public Optional<AlatKerja> findById(String id) {
        if (StringUtils.hasText(id)) {
            return alatKerjaDao.findById(id);
        }
        return Optional.empty();
    }

    public void validateAlatKerja(AlatKerja alatKerja, BindingResult bindingResult) {
        AlatKerja k = alatKerjaDao.findByCode(alatKerja.getCode());
        if (!ValidateHelper.validateEntity(k, alatKerja.getId())) {
            bindingResult.addError(new FieldError("alatKerja", "code", "Code barang sudah digunakan"));
        }
    }

    public void save(AlatKerja alatKerja) throws Exception {
        try {
            alatKerjaDao.save(alatKerja);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Gagal menyimpan");
        }
    }

    public void delete(String id) throws Exception {
        try {
            alatKerjaDao.deleteById(id);
        }catch (Exception e){
            log.error("ERROR DELETING ALAT KERJA ========================================= [{}]",e);
            throw new Exception("Gagal menghapus data : "+e.getMessage(),e);
        }
    }
}
