package com.chenyangqi.dao;

import com.chenyangqi.domain.FollowingGroup;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowingGroupDao {

    FollowingGroup getByType(String type);

    FollowingGroup getById(Long id);
}
