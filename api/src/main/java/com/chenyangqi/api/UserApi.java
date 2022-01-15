package com.chenyangqi.api;

import com.chenyangqi.domain.JsonResponse;
import com.chenyangqi.domain.User;
import com.chenyangqi.server.UserService;
import com.chenyangqi.server.utils.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApi {

    @Autowired
    private UserService userService;

    /**
     * 获取rsa公钥
     */
    @GetMapping
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
    public JsonResponse<String> login(@RequestBody User user) {
        String token = userService.login(user);
        return new JsonResponse<>(token);
    }

}