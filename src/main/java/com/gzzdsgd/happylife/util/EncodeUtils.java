package com.gzzdsgd.happylife.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncodeUtils {

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
