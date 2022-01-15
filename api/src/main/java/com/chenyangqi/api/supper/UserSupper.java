package com.chenyangqi.api.supper;

import com.chenyangqi.domain.exception.ConditionException;
import com.chenyangqi.server.utils.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class UserSupper {

    /**
     * 获取当前用户id
     */
    public Long getCurrentUserId() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String token;
        if (requestAttributes != null) {
            token = requestAttributes.getRequest().getHeader("token");
        } else {
            throw new ConditionException("token为空！");
        }
        Long userId = TokenUtil.verifyToken(token);
        if (userId < 0) {
            throw new ConditionException("非法用户！");
        }
        return userId;
    }
}
