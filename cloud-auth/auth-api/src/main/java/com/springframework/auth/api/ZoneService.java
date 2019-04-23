package com.springframework.auth.api;

import com.springframework.auth.api.domain.dto.ZoneDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author summer
 * 2018/11/20
 */
@Api(value = "测试接口",tags = "测试接口")
@RequestMapping("/zoneservice")
public interface ZoneService {

    @PostMapping("/create")
    @ApiOperation(value = "创建",notes = "创建")
    Long create(@RequestBody ZoneDTO dto);

    @PostMapping("/update")
    @ApiOperation(value = "更新",notes = "更新")
    Integer update(@RequestBody ZoneDTO dto);

    @PostMapping(value = "/list")
    @ApiOperation(value = "分页查询",notes = "分页查询")
    ZoneDTO list(@RequestBody ZoneDTO dto,
                 @RequestParam(value = "pageindex") int pageIndex,
                 @RequestParam(value = "pagesize") int pageSize);

    @GetMapping("/delete/by/primarykey")
    @ApiOperation(value = "删除",notes = "删除")
    Integer deleteByPrimaryKey(@RequestParam(value = "id") Long id);

    @PostMapping(value = {"/batch/delete/by/primarykey"})
    @ApiOperation(value = "批量删除",notes = "批量删除")
    Integer batchDeleteByPrimaryKey(@RequestBody List<Long> ids);

}

