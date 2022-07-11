package com.nusamandiri.monitoringgudang.dto.selections;

import lombok.Data;

/**
 * @author galang
 */
@Data
public class Select2Pagination {
    private boolean more;

    public Select2Pagination(boolean more) {
        this.more = more;
    }
}
