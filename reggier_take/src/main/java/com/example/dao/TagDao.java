package com.example.dao;

import com.example.entity.Tag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagDao {
    List<Tag> listByIds(@Param("list") List<Long> tagIdList);

    @Delete("delete from tag where id = #{id}")
    int deleteById(long id);

    @Insert("insert into tag values(null, #{tag.id}, #{tag.name}, #{tag.remark}, #{tag.createTime}, #{tag.updateTime} )")
    int insert(Tag tag);

    int updateById(Tag tag);
}
