package com.example.dao;

import com.example.entity.StoreTag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StoreTagDao {
    @Select("select  * from store_tag where store_id = #{storeId}")
    List<StoreTag> listByTagId(@Param("storeId") long storeId);

    @Insert("insert into store_tag values(#{tagId}, #{storeId})")
    int insert(StoreTag storeTag);

    @Delete("delete from store_tag where store_id = #{storeId}")
    int delete(long storeId);

}
