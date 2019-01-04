package com.example.core.CipherUtil;

import com.example.core.Result;

/**
 * API传输加解密相关接口
 */
public interface EncryptOpenService {

    /**
     * 生成RSA公私钥
     *
     * @return
     */
    Result<RSAResponse> getRSA();

    /**
     * 获得加解密用的密钥
     *
     * @param request
     * @return
     */
    Result<KeyResponse> getKey(KeyRequest request) throws Exception;
}
