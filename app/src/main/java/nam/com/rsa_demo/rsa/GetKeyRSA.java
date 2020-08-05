package nam.com.rsa_demo.rsa;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


import static nam.com.rsa_demo.util.Entity.PRIVATE_FILE;
import static nam.com.rsa_demo.util.Entity.PUBLIC_FILE;

@RequiresApi(api = Build.VERSION_CODES.O)
public class GetKeyRSA {
    public static PublicKey publicKey(Context context) throws Exception {
        byte[] bytes = Files.readAllBytes(new File(PUBLIC_FILE).toPath());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePublic(spec);
    }

    public static PrivateKey privateKey(Context context) throws Exception {
        byte[] bytes = Files.readAllBytes(new File(PRIVATE_FILE).toPath());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePrivate(spec);
    }
}
