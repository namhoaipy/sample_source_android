package nam.com.rsa_demo.rsa;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import nam.com.rsa_demo.util.PermissionController;

public class GenerateKeys {
    private static GenerateKeys generateKeys;
    private static final String keyPairType = "RSA";
    private KeyPairGenerator keyPairGenerator;
    private KeyPair pair;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public static GenerateKeys getInstance(int key) throws NoSuchAlgorithmException {
        if (generateKeys == null)
            generateKeys = new GenerateKeys(key);
        return generateKeys;
    }

    public GenerateKeys(int keys) throws NoSuchAlgorithmException {
        this.keyPairGenerator = KeyPairGenerator.getInstance(keyPairType);
        this.keyPairGenerator.initialize(keys);
    }

    public void createKeys() {
        this.pair = this.keyPairGenerator.generateKeyPair();
        this.privateKey = this.pair.getPrivate();
        this.publicKey = this.pair.getPublic();
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void generateKeysToFile(String path, Context context,int request_code,byte[]bytes) throws Exception {
        if (PermissionController.CHECK_WRITE_READ_STORAGE_PERMISSION(context,request_code)){
            File file = new File(path);
            file.createNewFile();
            FileOutputStream fileOutputStream =
                    new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();
        }
    }

}
