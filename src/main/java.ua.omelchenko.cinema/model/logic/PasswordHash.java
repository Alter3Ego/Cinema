package model.logic;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordHash {
    public static String encryption(String pass){
        return DigestUtils.md5Hex(pass).toUpperCase();
    }
}