package com.example.service;

import com.example.entity.Store;
import com.example.entity.vo.PageVO;
import com.example.entity.vo.StoreVO;

public interface StoreService {

    PageVO<StoreVO> searchStore(int pageNum, int pageSize);

    StoreVO searchStoreById(long id);

    void deleteStore(long id);

    void saveStore(StoreVO storeVO);

    void updateStore(StoreVO storeVO);

}
