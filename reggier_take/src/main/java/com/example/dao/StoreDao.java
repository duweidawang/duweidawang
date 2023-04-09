package com.example.dao;

import com.example.entity.Store;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StoreDao {

    @Select("select * from store")
    List<Store> list();

    List<Store> page(int pageNum, int pageSize);

    @Select("select * from store where id = #{id}")
    Store getById(long id);

    @Delete("delete from store where id = #{id}")
    int delete(long id);

    int updateById(Store store);

    int insert(Store store);
}
