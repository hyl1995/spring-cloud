package com.example.core.model;

import org.apache.commons.codec.digest.DigestUtils;

public abstract class BaseService {

    /**
     * 密码加密算法
     * @param password
     * @return
     */
    protected String encryptPassword(String password){
        return DigestUtils.sha1(password).toString();
    }

    /**
     * 生成API鉴权的Token
     * @param mobile
     * @param password
     * @return
     */
    protected String getToken(String mobile,String password){
        return DigestUtils.sha1(mobile+password).toString();
    }
}
