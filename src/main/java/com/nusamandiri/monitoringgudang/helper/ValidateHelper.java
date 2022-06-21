package com.nusamandiri.monitoringgudang.helper;

import com.nusamandiri.monitoringgudang.entity.BaseEntity;

/**
 * @author galang
 */
public class ValidateHelper {

    public static Boolean validateEntity(BaseEntity baseEntity, String id){
        return baseEntity == null || baseEntity.getId().equalsIgnoreCase(id);
    }

}
