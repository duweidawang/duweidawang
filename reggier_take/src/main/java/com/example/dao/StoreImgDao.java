package com.example.dao;

import com.example.entity.StoreImg;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StoreImgDao {

    @Select("select * from store_img where store_id = #{storeId}")
    List<StoreImg> getByStoreId(long storeId);

    @Insert("Insert into store_img values(null, #{storeId}, #{imgUrl})")
    int insert(@Param("storeId") long storeId, @Param("imgUrl") String imgUrl);

    @Delete("delete from store_img where store_id = #{storeId}")
    int deleteByStoreId(long storeId);

}
