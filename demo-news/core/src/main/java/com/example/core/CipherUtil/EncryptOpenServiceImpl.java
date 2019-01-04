package com.example.core.CipherUtil;

import com.example.core.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource(value = "classpath:application-core-dev.yml")
public class EncryptOpenServiceImpl implements EncryptOpenService {

    @Value("${rsa.publicKey}")
    private String publicKey;
    @Value("${rsa.privateKey}")
    private String privateKey;
    @Value("${api.encrypt.key}")
    private String key;

    @Override
    public Result<RSAResponse> getRSA() {
        RSAResponse response = RSAResponse.options()
                .setServerPublicKey(publicKey)
                .build();
        return new Result<>(true, response);
    }

    @Override
    public Result<KeyResponse> getKey(KeyRequest request) throws Exception {
        String clientPublicKey = RSAUtils.privateDecrypt(request.getClientEncryptPublicKey(), RSAUtils.getPrivateKey(privateKey));
        String encryptKey = RSAUtils.publicEncrypt(key, RSAUtils.getPublicKey(clientPublicKey));
        KeyResponse response = KeyResponse.options()
                .setKey(encryptKey)
                .build();
        return new Result<>(true, response);
    }
}
