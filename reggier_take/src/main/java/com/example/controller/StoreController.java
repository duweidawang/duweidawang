package com.example.controller;

import com.example.common.ResultCodeEnum;
import com.example.common.SystemException;
import com.example.entity.ResponseResult;
import com.example.entity.vo.PageVO;
import com.example.entity.vo.StoreVO;
import com.example.service.StoreService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zn
 * @Version 1.0
 */
@RestController
@RequestMapping("store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @GetMapping("/page")
    public ResponseResult<PageVO<StoreVO>> searchStoresByPage(int pageNum, int pageSize) {
        return ResponseResult.success(storeService.searchStore(pageNum, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseResult<StoreVO> searchStore(@PathVariable("id") Long id) {
        if (id == null) {
            throw new SystemException(ResultCodeEnum.SYSTEM_ERROR);
        }
        return ResponseResult.success(storeService.searchStoreById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseResult removeStore(@PathVariable("id") Long id) {
        storeService.deleteStore(id);
        return ResponseResult.success();
    }

    @PutMapping
    public ResponseResult updateStore(@RequestBody StoreVO storeVO) {
        storeService.updateStore(storeVO);
        return ResponseResult.success();
    }

    @PostMapping
    public ResponseResult saveStore(@RequestBody StoreVO storeVO) {
        storeService.saveStore(storeVO);
        return ResponseResult.success();
    }
}
