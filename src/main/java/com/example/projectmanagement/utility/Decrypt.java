package com.example.projectmanagement.utility;

import com.example.projectmanagement.dto.utils.CheckFieldAndValuesDTO;
import com.example.projectmanagement.exceptions.ProjectManagementException;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;
import java.util.stream.Collectors;

public class Decrypt {

    private Decrypt() {
    }

    public static Map<String , String > decryptRequestParam(Map<String, String> requestParams){
        return requestParams.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, stringStringEntry -> decryptData(stringStringEntry.getValue())));
    }


    public static void validatedCheckSum(Map<String, String> requestParams){
        if(requestParams.get("orderedFieldsName")!= null && requestParams.get("orderedFieldValues")!=null){
            final String[] orderedFieldNames = decryptData(requestParams.get("orderedFieldNames")).split("\\|");
            StringBuilder concatednatedValue = new StringBuilder();
            for(String orderedFieldName: orderedFieldNames){
                concatednatedValue.append(requestParams.get(orderedFieldName));
            }

            final String hashedFieldValues = toSha256(concatednatedValue.toString());
            if(!hashedFieldValues.equalsIgnoreCase(requestParams.get("orderedFieldValues"))){
                throw new ProjectManagementException("Input data tampered with");
            }
        }
    }

    public static void validateCheckSum(CheckFieldAndValuesDTO dto){
        String formFields = dto.getOrderedFieldNames();
        String formValues = dto.getOrderedFieldValues();

        if(StringUtils.isNotBlank(formFields) && StringUtils.isNotBlank(formValues) ){
            final String[] orderedFieldNames = decryptData(formFields).split("\\|");
            StringBuilder concatenatedFieldValue = new StringBuilder();
            for (String orderedFieldName : orderedFieldNames) {
                try {
                    if(!dto.getClass().getSuperclass().equals(CheckFieldAndValuesDTO.class)) {

                        Field field = dto.getClass().getSuperclass().getDeclaredField(orderedFieldName);
                        final Object obj = field.get(dto);
                        concatenatedFieldValue.append(obj);
                    }else {
                        Field field = dto.getClass().getDeclaredField(orderedFieldName);
                        final Object obj = field.get(dto);
                        concatenatedFieldValue.append(obj);
                    }
                } catch (IllegalArgumentException | IllegalAccessException |NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
            final String hashedFieldValues = toSha256(concatenatedFieldValue.toString());
            if (!hashedFieldValues.equalsIgnoreCase(formValues)) {
                throw new ProjectManagementException("Input data tampered with");
            }
        }
    }


    public static String decryptData(String data){
        try{
            final boolean decryptable = data.startsWith("**");
            if(decryptable){
                data = data.replace("**", "");
                String key = "1234567812345678";
                String iv = "1234567812345678";

                Base64.Decoder decoder = Base64.getDecoder();
                byte[] encryptedData = decoder.decode(data);

                Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
                SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
                IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());

                cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);

                byte[] original = cipher.doFinal(encryptedData);
                String orignalString = new String(original);
                return orignalString.trim();
            }

        }catch (Exception exception){
            //Deal with the exception here
            exception.printStackTrace();
        }
        return data;
    }

    private static String toSha256(String input) {
            MessageDigest messageDigest = null;
            try {
                messageDigest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException exception) {
                // Implement Exception handling here
                exception.printStackTrace();
            }

            byte[] result = messageDigest.digest(input.getBytes());
            StringBuilder stringBuffer = new StringBuilder();
            for (int i = 0; i < result.length; i++) {
                stringBuffer.append(Integer.toString((result[i] & 255) + 256, 16).substring(1));
            }

        return stringBuffer.toString();
    }

}
