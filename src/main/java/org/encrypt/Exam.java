package org.encrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Exam {
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";// 默认的加密算法

    public static byte[] initSecretKey(String kst) {

        // 返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        }
        // 初始化此密钥生成器，使其具有确定的密钥大小
        // AES 要求密钥长度为 128
        if (null != kst && kst.length() > 0) {
            kg.init(128, new SecureRandom(kst.getBytes()));
        }else {
            kg.init(128);
        }
        // 生成一个密钥
        SecretKey secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }

    public static byte[] p7(byte[] dataBytes, int blockSize) {
        int plaintextLength = dataBytes.length;
        if (plaintextLength % blockSize != 0) {
            plaintextLength = plaintextLength
                    + (blockSize - (plaintextLength % blockSize));
        }
        byte[] plaintext = new byte[plaintextLength];
        System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
        return plaintext;
    }

    private static Key toKey2(byte[] key, int size) {
        // 生成密钥
        return new SecretKeySpec(p7(key, size), KEY_ALGORITHM);
    }


    public static byte[] encrypt(String content, byte[] key) throws Exception {
        // 实例化
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        Key k = toKey2(key, cipher.getBlockSize());
        // 使用密钥初始化，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, k);
        // 执行操作
        return cipher.doFinal(content.getBytes("utf-8"));
    }

    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 实例化
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        Key k = toKey2(key, cipher.getBlockSize());
        // 使用密钥初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, k);
        // 执行操作
        return cipher.doFinal(data);
    }

    private static String showByteArray(byte[] data) {
        if (null == data) {
            return null;
        }
        StringBuilder sb = new StringBuilder("{");
        for (byte b : data) {
            sb.append(b).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        byte[] key = initSecretKey("abcdefghijklmnop");//128  16位
        String data = "jk_fgju_nfb_加密数据";
        System.out.println("加密前数据: byte[]:" + showByteArray(data.getBytes()));
        byte[] encryptData2 = encrypt(data, key);// 数据加密
        System.out.println("加密后数据: byte2[]:" + showByteArray(encryptData2));
        // System.out.println("加密后数据: hexStr:"+Hex.encodeHexStr(encryptData));
        byte[] decryptData2 = decrypt(encryptData2, key);// 数据解密
        System.out.println("解密后数据: byte2[]:" + showByteArray(decryptData2));
        System.out.println("解密后数据: string2:" + new String(decryptData2, "utf-8"));
    }

}
