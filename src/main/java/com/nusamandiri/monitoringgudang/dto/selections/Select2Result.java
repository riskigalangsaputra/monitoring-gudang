package com.nusamandiri.monitoringgudang.dto.selections;

import com.nusamandiri.monitoringgudang.entity.AlatKerja;
import com.nusamandiri.monitoringgudang.entity.Kategori;
import lombok.Data;

/**
 * @author galang
 */
@Data
public class Select2Result {

    private String id;
    private String text;

    public Select2Result(Object obj) {

        if(obj instanceof Kategori){
            Kategori kategori = (Kategori) obj;
            this.setId(kategori.getId());
            this.setText(kategori.getNama());
        }

        if(obj instanceof AlatKerja){
            AlatKerja alatKerja = (AlatKerja) obj;
            this.setId(alatKerja.getId());
            this.setText(alatKerja.getNama());
        }
    }

}
