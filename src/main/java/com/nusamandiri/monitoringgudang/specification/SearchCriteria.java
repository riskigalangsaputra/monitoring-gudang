package com.nusamandiri.monitoringgudang.specification;

import lombok.Data;

/**
 * @author galang
 */
@Data
public class SearchCriteria {

    private String key;
    private String operation;
    private Object value;

    public SearchCriteria(String key, String operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }
}
