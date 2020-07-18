package com.community.hululuuuu.myComponent;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageableDefault {

    public static Pageable setPageable(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize());
        return pageable;
    }

}
