package com.gzzdsg.happylife.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 编码工具了
 */
public class EncodeUtils {

    /**
     * 将输入的字符串进行sha1编码
     *
     * @param input 输入字符串
     * @return 编码后的内容
     */
    public static String encodeSha1(String input) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] encodedHash = digest.digest(input.getBytes());

            // Convert byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            System.out.println("SHA-1 hash: " + hexString.toString());
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-1 algorithm not available.");
        }
        return null;
    }

}
