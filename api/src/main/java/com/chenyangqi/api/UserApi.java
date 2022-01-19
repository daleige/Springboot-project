package com.chenyangqi.api;

import com.alibaba.fastjson.JSONObject;
import com.chenyangqi.api.supper.UserSupper;
import com.chenyangqi.domain.JsonResponse;
import com.chenyangqi.domain.PageResult;
import com.chenyangqi.domain.User;
import com.chenyangqi.domain.UserInfo;
import com.chenyangqi.server.UserFollowingService;
import com.chenyangqi.server.UserService;
import com.chenyangqi.server.utils.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserApi {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSupper userSupper;

    @Autowired
    private UserFollowingService userFollowingService;

    /**
     * 获取用户信息
     */
    @GetMapping("/get-user")
    public JsonResponse<User> getUserInfo() {
        Long userId = userSupper.getCurrentUserId();
        User user = userService.getUserInfo(userId);
        return new JsonResponse<>(user);
    }

    /**
     * 获取rsa公钥
     */
    @GetMapping("/rsa-pks")
    public JsonResponse<String> getRsaPublicKey() {
        String rsaPublicKey = RSAUtil.getPublicKeyStr();
        return new JsonResponse<>(rsaPublicKey);
    }

    /**
     * 注册接口
     */
    @PostMapping("/users")
    public JsonResponse<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return JsonResponse.success();
    }

    /**
     * 登录接口
     */
    @PostMapping("/user-tokens")
    public JsonResponse<String> login(@RequestBody User user) throws Exception {
        String token = userService.login(user);
        return new JsonResponse<>(token);
    }

    @PutMapping("/users")
    public JsonResponse<String> updateUsers(@RequestBody User user) {
        Long userId = userSupper.getCurrentUserId();
        user.setId(userId);
        userService.updateUsers(user);
        return JsonResponse.success();
    }

    /**
     * 更新用户信息
     */
    @PutMapping("/user-infos")
    public JsonResponse<String> updateUserInfos(@RequestBody UserInfo userInfo) {
        Long userId = userSupper.getCurrentUserId();
        userInfo.setUserId(userId);
        userService.updateUserInfos(userInfo);
        return JsonResponse.success();
    }

    @GetMapping("/user-infos")
    public JsonResponse<PageResult<UserInfo>> pageListUserInfos(@RequestParam Integer no,
                                                                @RequestParam Integer size,
                                                                @RequestParam String nick) {
        Long userId = userSupper.getCurrentUserId();
        JSONObject params = new JSONObject();
        params.put("no", no);
        params.put("size", size);
        params.put("nick", nick);
        params.put("userId", userId);
        PageResult<UserInfo> result = userService.pageListUserInfos(params);
        if (result.getTotal() > 0) {
            List<UserInfo> checkUserInfoList = userFollowingService.checkFollowingStatus(result.getList(), userId);
        }
    }
}
