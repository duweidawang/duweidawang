package com.example.service.impl;

import com.example.dao.StoreDao;
import com.example.dao.StoreImgDao;
import com.example.dao.StoreTagDao;
import com.example.entity.Store;
import com.example.entity.StoreImg;
import com.example.entity.StoreTag;
import com.example.entity.vo.PageVO;
import com.example.entity.vo.StoreVO;
import com.example.entity.vo.TagVO;
import com.example.service.StoreService;
import com.example.service.TagService;
import com.example.utils.BeanCopyUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author zn
 * @Version 1.0
 */
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreDao storeDao;
    @Autowired
    private TagService tagService;
    @Autowired
    private StoreTagDao storeTagDao;
    @Autowired
    private StoreImgDao storeImgDao;


    @Override
    public PageVO<StoreVO> searchStoreByPage(int pageNum, int pageSize) {
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        //分页查询店铺
        PageInfo<Store> storePageInfo = new PageInfo<>(storeDao.page(pageNum, pageSize));
        PageVO<StoreVO> page = new PageVO<>();
        page.setTotal(storePageInfo.getTotal());
        page.setRecords(BeanCopyUtils.copyBeanList(storePageInfo.getList(), StoreVO.class));
        page.getRecords().stream().forEach(store -> {
            //查询店铺对应标签
            List<TagVO> tagVOList = tagService.searchTagByStoreId(store.getId());
            store.setTagList(tagVOList);
            //查询店铺对应图片
            List<StoreImg> storeImgList = storeImgDao.getByStoreId(store.getId());
            List<String> imgUrlList = storeImgList.stream().map(StoreImg::getImgUrl).collect(Collectors.toList());
            store.setImgUrlList(imgUrlList);
        });

        return page;
    }

    @Override
    public StoreVO searchStoreById(long id) {
        Store store = storeDao.getById(id);
        List<StoreImg> storeImgList = storeImgDao.getByStoreId(id);
        StoreVO storeVO = BeanCopyUtils.copyBean(store, StoreVO.class);
        List<String> imgUrlList = storeImgList.stream().map(storeImg -> storeImg.getImgUrl()).collect(Collectors.toList());
        storeVO.setImgUrlList(imgUrlList);
        List<TagVO> tagVOS = tagService.searchTagByStoreId(storeVO.getId());
        storeVO.setTagList(tagVOS);
        return storeVO;
    }

    @Override
    @Transactional
    public void deleteStore(long id) {
        //删除商铺
        storeDao.delete(id);
        //删除商铺标签
        storeTagDao.delete(id);
        //删除店铺图片标签
        storeImgDao.deleteByStoreId(id);
    }

    @Override
    @Transactional
    public void saveStore(StoreVO storeVO) {
        //添加图片
        Store store = BeanCopyUtils.copyBean(storeVO, Store.class);
        storeDao.insert(store);
        if (!ObjectUtils.isEmpty(storeVO.getTagList())) {
            storeVO.getTagList().stream()
                    .forEach(tagVO -> {
                        if (tagVO.getId() != null) {
                            storeTagDao.insert(new StoreTag(tagVO.getId(), store.getId()));
                        }
                    });
        }
        //添加店铺图片
        List<String> imgUrlList = storeVO.getImgUrlList();
        for (String imgUrl : imgUrlList) {
            storeImgDao.insert(store.getId(), imgUrl);
        }
    }

    @Override
    @Transactional
    public void updateStore(StoreVO storeVO) {
        Store store = BeanCopyUtils.copyBean(storeVO, Store.class);
        storeDao.updateById(store);
        List<TagVO> tagList = storeVO.getTagList();
        //修改标签
        if (!ObjectUtils.isEmpty(tagList)) {
            storeTagDao.delete(storeVO.getId());
            tagList.stream()
                    .forEach(tagVO -> {
                        if (tagVO.getId() != null) {
                            storeTagDao.insert(new StoreTag(tagVO.getId(), storeVO.getId()));
                        }
                    });
        }
        //修改店铺图片
        List<String> imgUrlList = storeVO.getImgUrlList();
        if (!imgUrlList.isEmpty()) {
            storeImgDao.deleteByStoreId(storeVO.getId());
            for (String imgUrl : imgUrlList) {
                storeImgDao.insert(storeVO.getId(), imgUrl);
            }
        }
    }
}
