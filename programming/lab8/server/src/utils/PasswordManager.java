package utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordManager {
    public String hashPassword(String password){
        try{
            MessageDigest mg = MessageDigest.getInstance("SHA-256");
            byte[] pswd = mg.digest(password.getBytes(StandardCharsets.UTF_8));
            BigInteger bi = new BigInteger(1, pswd);
            return bi.toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
