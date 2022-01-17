package com.chenyangqi.server;

import com.chenyangqi.dao.UserFollowingDao;
import com.chenyangqi.domain.FollowingGroup;
import com.chenyangqi.domain.User;
import com.chenyangqi.domain.UserFollowing;
import com.chenyangqi.domain.constant.UserConstant;
import com.chenyangqi.domain.exception.ConditionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserFollowingService {
    @Autowired
    private UserFollowingDao userFollowingDao;

    @Autowired
    private FollowingGroupService followingGroupService;

    @Autowired
    private UserService userService;


    public void addUserFollowings(UserFollowing userFollowing) {
        Long groupId = userFollowing.getGroupId();
        if (groupId == null) {
            FollowingGroup followingGroup = followingGroupService.getByType(UserConstant.FOLLOWING_GROUP_DEFAULT_TYPE);
            userFollowing.setGroupId(followingGroup.getId());
        } else {
            FollowingGroup followingGroup = followingGroupService.getById(groupId);
            if (followingGroup == null) {
                throw new ConditionException("关注分组不存在");
            }
        }
        Long followingId = userFollowing.getFollowingId();
        User user = userService.getUserById(followingId);
        if(user==null){
            throw new ConditionException("关注的用户不存在");
        }
        userFollowingDao.deleteUserFollowing(userFollowing.getUserId(),followingId);
        userFollowing.setCreateTime(new Date());
        userFollowingDao.addUserFollowing(userFollowing);
    }
}
