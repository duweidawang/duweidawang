package com.example.service;

import com.example.entity.Tag;
import com.example.entity.vo.TagVO;

import java.util.List;

public interface TagService {
    List<TagVO> searchTagByStoreId(long storeId);

    void saveTag(Tag tag);

    void deleteTag(long id);

    void updateTag(Tag tag);
}
