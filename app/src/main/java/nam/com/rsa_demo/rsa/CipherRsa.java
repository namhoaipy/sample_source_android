package nam.com.rsa_demo.rsa;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class CipherRsa {
    public static byte[] CiperEncrypted(PublicKey key, String value) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA","BC");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] bytes = cipher.doFinal(value.getBytes());
        return bytes;
    }

    public static String CiperDecrypted(PrivateKey key, byte[] encryptedCiper) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA","BC");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] bytes = cipher.doFinal(encryptedCiper);
        return new String(bytes);
    }
}
