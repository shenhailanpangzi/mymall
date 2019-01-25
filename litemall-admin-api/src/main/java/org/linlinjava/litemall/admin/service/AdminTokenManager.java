package org.linlinjava.litemall.admin.service;

import org.linlinjava.litemall.admin.dao.AdminToken;
import org.linlinjava.litemall.core.util.CharUtil;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class AdminTokenManager {
    private static Map<String, AdminToken> tokenMap = new HashMap<>();
    private static Map<Integer, AdminToken> idMap = new HashMap<>();

    public static Integer getUserId(String token) {
        //判断token是否存在
        AdminToken userToken = tokenMap.get(token);
        if (userToken == null) {
            return null;
        }
        //判断token是否过期
        if (userToken.getExpireTime().isBefore(LocalDateTime.now())) {
            tokenMap.remove(token);
            idMap.remove(userToken.getUserId());
            return null;
        }

        return userToken.getUserId();
    }

    /**
     * 生成token
     * @param id
     * @return
     */
    public static AdminToken generateToken(Integer id) {
        AdminToken userToken = null;

//        userToken = idMap.get(id);
//        if(userToken != null) {
//            tokenMap.remove(userToken.getToken());
//            idMap.remove(id);
//        }

        String token = CharUtil.getRandomString(32);
        while (tokenMap.containsKey(token)) {
            token = CharUtil.getRandomString(32);
        }

        LocalDateTime update = LocalDateTime.now();
        LocalDateTime expire = update.plusDays(1);

        userToken = new AdminToken();
        userToken.setToken(token);
        userToken.setUpdateTime(update);
        userToken.setExpireTime(expire);
        userToken.setUserId(id);
        tokenMap.put(token, userToken);
        idMap.put(id, userToken);

        return userToken;
    }
}
