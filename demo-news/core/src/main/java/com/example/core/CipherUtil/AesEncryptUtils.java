package com.example.core.CipherUtil;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class AesEncryptUtils {

    private static final String KEY = "d7585fde114abcda";
    private static final String ALGORITHMSTR = "AES";

    /* 加密方法 */
    public static String base64Encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    public static byte[] aesEncryptToBytes(String content, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
        kgen.init(128, new SecureRandom(decryptKey.getBytes()));//根据钥匙初始化出128位的key生产者
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回null
        SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥

        Cipher cipher = Cipher.getInstance("AES");//创建密码器
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher.doFinal(content.getBytes("utf-8"));
    }

    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    /* 解密方法 */
    public static byte[] base64Decode(String base64Code) throws Exception {
        return Base64.decodeBase64(base64Code);
    }

    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
        kgen.init(128, new SecureRandom(decryptKey.getBytes()));//根据钥匙初始化出128位的key生产者
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回null
        SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥

        Cipher cipher = Cipher.getInstance("AES");//创建密码器
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }

    public static void main(String[] args) throws Exception {
        String content = "{name:\"lynn\",id:1}";
        System.out.println("加密前：" + content);

        String encrypt = aesEncrypt(content, KEY);
        System.out.println(encrypt.length() + ":加密后：" + encrypt);

        String decrypt = aesDecrypt(encrypt, KEY);
        System.out.println("解密后：" + decrypt);
    }
}
