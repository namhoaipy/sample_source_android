package nam.com.rsa_demo.util;

import android.util.Base64;

public class Base64Controller {

    public static String ENCODE(byte[] bytes) {
        return Base64.encodeToString(bytes,Base64.DEFAULT);
    }

    public static byte[] DECODE(String base64) {
        return Base64.decode(base64,Base64.DEFAULT);
    }
}
