package com.nusamandiri.monitoringgudang.dto.selections;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author galang
 */
@Data
public class Select2Dto {

    private List<Select2Result> results = new ArrayList<>();
    private Select2Pagination pagination;

    public Select2Dto(Page results) {
        results.getContent().forEach(r -> this.getResults().add(new Select2Result(r)));
        this.setPagination(new Select2Pagination(!results.isLast()));
    }
}
