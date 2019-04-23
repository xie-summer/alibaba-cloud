package com.springframework.auth.api.impl;

import com.springframework.auth.api.ZoneService;
import com.springframework.auth.api.domain.dto.ZoneDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author summer
 * 2018/11/20
 */
@RestController
public class ZoneServiceImpl implements ZoneService {

    @Override
    public Long create(@RequestBody ZoneDTO dto) {
        return 0L;
    }

    @Override
    public Integer update(@RequestBody ZoneDTO dto) {
        return 0;
    }

    @Override
    public ZoneDTO list(@RequestBody ZoneDTO dto, @RequestParam(value = "pageindex") int pageIndex, @RequestParam(value = "pagesize") int pageSize) {
        return new ZoneDTO();
    }

    @Override
    public Integer deleteByPrimaryKey(@RequestParam(value = "id") Long id) {
        return 0;
    }

    @Override
    public Integer batchDeleteByPrimaryKey(@RequestBody List<Long> ids) {
        return 0;
    }

}
