package ua.omelchenko.cinema.model.logic;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * md5-Hashing
 */
public class PasswordHash {
    public static String encryption(String pass){
        return DigestUtils.md5Hex(pass).toUpperCase();
    }
}
