package com.chenyangqi.dao;

import com.chenyangqi.domain.UserFollowing;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserFollowingDao {

    void deleteUserFollowing(Long userId, Long followingId);

    void addUserFollowing(UserFollowing userFollowing);
}
