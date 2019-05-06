package com.springframework.auth.api;

import com.springframework.auth.api.domain.dto.OauthClientDetailsDTO;
import com.springframework.domain.base.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author summer
 * 2018/11/20
 */
@Api(value = "授权认证基础数据接口", tags = "授权认证基础数据接口")
@RequestMapping("/v1/oauth/security")
public interface OauthSecurityServiceRemote {

    @PostMapping(value = {"/create", "/create/oauthClientDetail"})
    @ApiOperation(value = "创建oauth client detail", notes = "创建oauth client detail")
    RestResult<Integer> createOauthClientDetails(@RequestBody OauthClientDetailsDTO dto);

    @PatchMapping("/update")
    @ApiOperation(value = "更新", notes = "更新")
    RestResult<Integer> updateOauthClientDetails(@RequestBody OauthClientDetailsDTO dto);

    @PostMapping(value = "/list")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    RestResult<OauthClientDetailsDTO> list(@RequestBody OauthClientDetailsDTO dto,
                                           @RequestParam(value = "index") int pageIndex,
                                           @RequestParam(value = "size") int pageSize);

    @DeleteMapping("/delete/by/primarykey")
    @ApiOperation(value = "删除", notes = "删除")
    RestResult<Integer> deleteByPrimaryKey(@RequestParam(value = "id") Long id);

    @DeleteMapping(value = {"/batch/delete/by/primarykey"})
    @ApiOperation(value = "批量删除", notes = "批量删除")
    RestResult<Integer> batchDeleteByPrimaryKey(@RequestBody List<Long> ids);

}

