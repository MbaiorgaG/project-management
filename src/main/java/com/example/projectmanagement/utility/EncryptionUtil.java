package com.example.projectmanagement.utility;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptionUtil {



    public static String getSHA512(String stringToHash, String   salt){
        System.out.print("stringToHash:"+stringToHash);
        String generatedSHA = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            if (salt != null && !salt.trim().isEmpty()) {
                md.update(salt.getBytes(StandardCharsets.UTF_8));
            }

            byte[] bytes = md.digest(stringToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedSHA = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedSHA;
    }
    public static String encrypt(String data){
        try
        {
            data = data.replace("**","");
            String key = "1234567812345678";
            String iv = "1234567812345678";
            byte[] encrypted1 = data.getBytes();

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            byte[] ivBytes = new byte[16];
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);

            byte[] cipherText = cipher.doFinal(data.getBytes());
            return Base64.getEncoder()
                    .encodeToString(cipherText);
//            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String encrypt2(String message) throws Exception{
        String key = "1234567812345678";
        byte[] encval=null;
        SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        String iv = "1234567812345678";
        byte[] ivBytes = iv.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE,skey,new IvParameterSpec(ivBytes));   //iv here
        encval=cipher.doFinal(message.getBytes());
        String encryptedValue = Base64.getEncoder().encodeToString(encval);
        return encryptedValue;

    }
}
