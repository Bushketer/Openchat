import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Utils {
    public static SecretKey generateKey() {
        try {
            KeyGenerator keygenerator = KeyGenerator.getInstance("AES");
            keygenerator.init(128);
            return keygenerator.generateKey();
        } catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Failed to generate AES key", e);
        }
    }


    public static IvParameterSpec generateIv() {
        byte[] initializationVector = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(initializationVector);
        return new IvParameterSpec(initializationVector);
    }

    public static byte[] encrypt(String input, SecretKey key, IvParameterSpec iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            return cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e){
            throw new RuntimeException("Failed to encrypt text", e);
        }
    }
    public static String decrypt(byte[] cipherText, SecretKey key, IvParameterSpec iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] plainText = cipher.doFinal(cipherText);
            return new String(plainText);
        } catch (Exception e){
            throw new RuntimeException("Failed to decrypt text", e);
        }
    }

    public static String base64_encode(byte[] bytes){
        String encoded = Base64.getEncoder().encodeToString(bytes);
        return encoded;
    }
    public static byte[] base64_decode(String encoded){
        byte[] decoded = Base64.getDecoder().decode(encoded);
        return decoded;
    }

    public static String keyToString(SecretKey secretKey) {
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    public static SecretKey stringToKey(String keyString) {
        byte[] decodedKey = Base64.getDecoder().decode(keyString);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

}
