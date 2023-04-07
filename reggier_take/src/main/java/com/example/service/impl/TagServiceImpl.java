package com.example.service.impl;

import com.example.dao.StoreTagDao;
import com.example.dao.TagDao;
import com.example.entity.StoreTag;
import com.example.entity.Tag;
import com.example.entity.vo.TagVO;
import com.example.service.TagService;
import com.example.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author zn
 * @Version 1.0
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;
    @Autowired
    private StoreTagDao storeTagDao;

    @Override
    public List<TagVO> searchTagByStoreId(long storeId) {
        List<StoreTag> storeTagList = storeTagDao.listByTagId(storeId);
        List<Long> tagIdList = storeTagList.stream()
                .map(storeTag -> storeTag.getTagId())
                .collect(Collectors.toList());
        List<Tag> tagList = tagDao.listByIds(tagIdList);
        List<TagVO> tagVOList = BeanCopyUtils.copyBeanList(tagList, TagVO.class);
        return tagVOList;
    }

    @Override
    public void saveTag(Tag tag) {
        tagDao.insert(tag);
    }

    @Override
    public void deleteTag(long id) {
        tagDao.deleteById(id);
    }

    @Override
    public void updateTag(Tag tag) {
        tagDao.updateById(tag);
    }
}
